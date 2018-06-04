package com.ty.fuping.controller;

import com.ty.fuping.entity.AssistanceEffect;
import com.ty.fuping.entity.Family;
import com.ty.fuping.repository.FamilyRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ty on 2018/1/17.
 * 数据分析
 */
@RestController
public class DataAnalyseController {

    @Autowired
    private FamilyRepository familyRepository;

    @RequestMapping("/citydataanalyse")
    public JSONObject dataAnalyse(@RequestParam(name = "districtId", required = false) Integer districtId) {

        boolean isDistrict = false; //是否查找某区
        if (districtId != null)
            isDistrict = true;

        JSONObject result = new JSONObject();
        JSONObject jsonObject1, jsonObject2;

        Map<String, FamilyNumInDistrict> familyNumInDistrictMap = new HashMap<>();//存储各区县(乡镇)贫困户、人数
        int alreadyOvercomePoverty = 0; //已脱贫
        int notOvercomePoverty = 0; //未脱贫
        int preOvercomePoverty = 0; //预脱贫
        Map<String, Integer> totalIncomeMap = new HashMap<>();//各区县(乡镇)总收入
        /**
         * 全市(区)致贫原因
         */
        //因病
        int ofIll = 0;
        //因学
        int ofSchool = 0;
        //因灾
        int ofDisaster = 0;
        //因残
        int ofDisability = 0;
        //缺土
        int lackSoil = 0;
        //缺水
        int lackWater = 0;
        //缺技术
        int lackTechnology = 0;
        //缺劳动力
        int lackLabor = 0;
        //缺资金
        int lackMoney = 0;
        //交通条件落后
        int lackTraffic = 0;
        //自然发展动力不足
        int lackNatureDevelopment = 0;
        //其他
        int others = 0;
        /**
         * 全市(区)帮扶措施
         */
        //饮水安全
        int drinkingSafeties = 0;
        //金融扶贫
        int financialAssistances = 0;
        //危房改造
        int houseRenovations = 0;
        //劳动力培训
        int laborTrainings = 0;
        //富民产业
        int prosperousIndustries = 0;
        //社会援助
        int socialAssistances = 0;
        //专项扶贫
        int specialPovertyAlleviations = 0;
        List<Family> familyList = null;

        if (!isDistrict)
            familyList = familyRepository.findAll();
        else
            familyList = familyRepository.findAllByVillage_Town_District_DistrictId(districtId);

        Iterator<Family> familyIterator = familyList.iterator();
        Family family;
        //循环查找市(区)所有家庭
        while (familyIterator.hasNext()) {
            family = familyIterator.next();
            //把此家庭人口加入存储各区县贫困户、人数的Map中
            String familyAdress;
            if (isDistrict)
                familyAdress = family.getVillage().getTown().getTownName();
            else
                familyAdress = family.getVillage().getTown().getDistrict().getDistrictName();
            if (familyNumInDistrictMap.get(familyAdress) == null) {
                familyNumInDistrictMap.put(familyAdress
                        , new FamilyNumInDistrict(1, family.getFamilyMembers().size()));
            } else
                familyNumInDistrictMap.put(familyAdress
                        , new FamilyNumInDistrict(familyNumInDistrictMap.get(familyAdress).familyNum + 1
                                , familyNumInDistrictMap.get(familyAdress).peopleNum + family.getFamilyMembers().size()));

            //把此家庭致贫原因加入全市(区)致贫原因
            if (family.getOfIll() != null && family.getOfIll())
                ofIll++;
            if (family.getOfSchool() != null && family.getOfSchool())
                ofSchool++;
            if (family.getOfDisaster() != null && family.getOfDisaster())
                ofDisaster++;
            if (family.getOfDisability() != null && family.getOfDisability())
                ofDisability++;
            if (family.getLackSoil() != null && family.getLackSoil())
                lackSoil++;
            if (family.getLackWater() != null && family.getLackWater())
                lackWater++;
            if (family.getLackTechnology() != null && family.getLackTechnology())
                lackTechnology++;
            if (family.getLackLabor() != null && family.getLackLabor())
                lackLabor++;
            if (family.getLackMoney() != null && family.getLackMoney())
                lackMoney++;
            if (family.getLackTraffic() != null && family.getLackTraffic())
                lackTraffic++;
            if (family.getLackNatureDevelopment() != null && family.getLackNatureDevelopment())
                lackNatureDevelopment++;
            if (family.getOthers() != null && family.getOthers())
                others++;

            //把此家庭情况汇入全市(区)脱贫未脱贫统计
            if (family.getPovertyState() != null) {
                if (family.getPovertyState() == 1)
                    notOvercomePoverty++;
                else if (family.getPovertyState() == 2)
                    preOvercomePoverty++;
                else
                    alreadyOvercomePoverty++;
            }
            //把此家庭收入加入到各区县（乡镇）贫困户总收入
            if (family.getAssistanceEffects() != null && family.getAssistanceEffects().size() != 0) {
                List<AssistanceEffect> assistanceEffectList = family.getAssistanceEffects();
                Iterator<AssistanceEffect> assistanceEffectIterator = assistanceEffectList.iterator();
                AssistanceEffect assistanceEffect, latest = null;
                while (assistanceEffectIterator.hasNext()) {
                    assistanceEffect = assistanceEffectIterator.next();
                    String[] assistanceEffectStrings = assistanceEffect.getQuarter().split("-");
                    String[] latestStrings = null;
                    if (latest != null) {
                        latestStrings = latest.getQuarter().split("-");
                    }
                    if (latest == null)
                        latest = assistanceEffect;
                    else if ((Integer.parseInt(latestStrings[0]) < Integer.parseInt(assistanceEffectStrings[0]))
                            || latestStrings[2].equals("第一季度")
                            || (latestStrings[2].equals("第二季度") && (assistanceEffectStrings[2].equals("第三季度") || assistanceEffectStrings[2].equals("第四季度")))
                            || latestStrings[2].equals("第三季度") && (assistanceEffectStrings[2].equals("第四季度")))
                        latest = assistanceEffect;
                }
                int income = 0;
                if (latest.getOperatingIncome() != null)
                    income += latest.getOperatingIncome();
                if (latest.getPropertyIncome() != null)
                    income += latest.getPropertyIncome();
                if (latest.getTransferIncome() != null)
                    income += latest.getTransferIncome();
                if (latest.getWageIncome() != null)
                    income += latest.getWageIncome();

                if (totalIncomeMap.get(familyAdress) == null) {
                    totalIncomeMap.put(familyAdress, income);
                } else
                    totalIncomeMap.put(familyAdress, totalIncomeMap.get(familyAdress) + income);
            }

            //把此家庭的帮扶措施加入全市帮扶措施统计
            if (family.getDrinkingSafeties().size() > 0)
                drinkingSafeties++;
            if (family.getFinancialAssistances().size() > 0)
                financialAssistances++;
            if (family.getHouseRenovations().size() > 0)
                houseRenovations++;
            if (family.getLaborTrainings().size() > 0)
                laborTrainings++;
            if (family.getProsperousIndustries().size() > 0)
                prosperousIndustries++;
            if (family.getSocialAssistances().size() > 0)
                socialAssistances++;
            if (family.getSpecialPovertyAlleviations().size() > 0)
                specialPovertyAlleviations++;
        }

        /**
         * 开始返回数据
         */
        //返回各区县(乡镇)贫困户、人数
        jsonObject1 = new JSONObject();
        //遍历区县集合
        for (Map.Entry<String, FamilyNumInDistrict> entry : familyNumInDistrictMap.entrySet()) {
            jsonObject2 = new JSONObject();
            jsonObject2.put("familyNum", entry.getValue().familyNum);
            jsonObject2.put("peopleNum", entry.getValue().peopleNum);

            jsonObject1.put(entry.getKey(), jsonObject2);
        }
        result.put("povertyNum", jsonObject1);

        //返回全市（区）致贫原因
        jsonObject1 = new JSONObject();
        jsonObject1.put("因病", ofIll);
        jsonObject1.put("因学", ofSchool);
        jsonObject1.put("因灾", ofDisaster);
        jsonObject1.put("因残", ofDisability);
        jsonObject1.put("缺土", lackSoil);
        jsonObject1.put("缺水", lackWater);
        jsonObject1.put("缺技术", lackTechnology);
        jsonObject1.put("缺劳动力", lackLabor);
        jsonObject1.put("缺资金", lackMoney);
        jsonObject1.put("交通条件落后", lackTraffic);
        jsonObject1.put("自然发展动力不足", lackNatureDevelopment);
        jsonObject1.put("其他", others);
        result.put("povertyReason", jsonObject1);

        //返回全市(区)脱贫未脱贫比例
        jsonObject1 = new JSONObject();
        jsonObject1.put("已脱贫", alreadyOvercomePoverty);
        jsonObject1.put("未脱贫", notOvercomePoverty);
        jsonObject1.put("预脱贫", preOvercomePoverty);
        result.put("overcomePovertyScale", jsonObject1);

        //返回各区县(乡镇)人均收入
        jsonObject1 = new JSONObject();
        //遍历区县集合
        for (Map.Entry<String, Integer> entry : totalIncomeMap.entrySet()) {
            jsonObject1.put(entry.getKey(), entry.getValue() / familyNumInDistrictMap.get(entry.getKey()).peopleNum);
        }
        result.put("averageIncome", jsonObject1);

        //返回贫困户帮扶措施
        jsonObject1 = new JSONObject();
        jsonObject1.put("富民产业", prosperousIndustries);
        jsonObject1.put("饮水安全", drinkingSafeties);
        jsonObject1.put("金融扶贫", financialAssistances);
        jsonObject1.put("危房改造", houseRenovations);
        jsonObject1.put("劳动力培训", laborTrainings);
        jsonObject1.put("社会援助", socialAssistances);
        jsonObject1.put("专项扶贫", specialPovertyAlleviations);
        result.put("assistanceMeasure", jsonObject1);

        return result;
    }

}

class FamilyNumInDistrict {
    int familyNum; //贫困户数
    int peopleNum; //贫困人口数

    public FamilyNumInDistrict() {
        this.familyNum = 0;
        this.peopleNum = 0;
    }

    public FamilyNumInDistrict(int familyNum, int peopleNum) {
        this.familyNum = familyNum;
        this.peopleNum = peopleNum;
    }

    public int getFamilyNum() {
        return familyNum;
    }

    public void setFamilyNum(int familyNum) {
        this.familyNum = familyNum;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }
}
