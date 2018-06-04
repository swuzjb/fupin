package com.ty.fuping.controller;

import com.ty.fuping.entity.AssistanceEffect;
import com.ty.fuping.entity.AssistanceMeasure.DrinkingSafety;
import com.ty.fuping.entity.AssistanceMeasure.HouseRenovation;
import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.FamilyMember;
import com.ty.fuping.repository.FamilyRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2018/1/15.
 * 帮扶成效管理
 */
@RestController
public class AssistanceEffectManagementController {

    @Autowired
    private FamilyRepository familyRepository;

    @RequestMapping("assistanceeffectmanagement")
    public JSONObject assistanceEffectManagement(@RequestParam(value = "villageId", required = false) Integer villageId,
                                                 @RequestParam(value = "townId", required = false) Integer townId,
                                                 @RequestParam(value = "districtId", required = false) Integer districtId) {

        List<Family> familyList;

        JSONObject jsonObject = new JSONObject();
        JSONObject subItems = null;
        JSONArray contentArray = new JSONArray();
        Iterator<Family> familyIterator;

        DecimalFormat decimalFormat = new DecimalFormat(".00"); //小数格式转换类

        int totalFamilyNum = 0;//总户数
        int totalPeopleNum = 0;//总人数
        int reachStandardFamily = 0;//达标户数
        int reachStandardPeople = 0;//达标人数
        int notReachStandardFamily = 0;//未达标户数
        int notReachStandardPeople = 0;//未达标人数

        if (villageId != null) {
            familyList = familyRepository.findAllByVillage_VillageId(villageId);
        } else if (townId != null) {
            familyList = familyRepository.findAllByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            familyList = familyRepository.findAllByVillage_Town_District_DistrictId(districtId);
        } else {
            familyList = familyRepository.findAll();
        }

        totalFamilyNum = familyList.size();//得出总户数

        familyIterator = familyList.iterator();
        Family family;
        double incomePoint = 0;//收入得分
        //遍历地址下的所有贫困户
        while (familyIterator.hasNext()) {
            family = familyIterator.next();
            totalPeopleNum += family.getFamilyMembers().size();//计算总人数
            subItems = new JSONObject();
            subItems.put("familyId", family.getFamilyId()); //家庭Id
            subItems.put("townId", family.getVillage().getTown().getTownId()); //镇Id
            subItems.put("familyOwnerName", family.getFamilyOwner().getName()); //户主姓名
            subItems.put("povertyGrade", family.getPovertyGrade()); //贫困户属性
            /**
             * 添加主要致贫原因
             */
            StringBuffer buffer = new StringBuffer("");
            if (family.getOfIll() != null && family.getOfIll())
                buffer.append("因病,");
            if (family.getOfSchool() != null && family.getOfSchool())
                buffer.append("因学,");
            if (family.getOfDisaster() != null && family.getOfDisaster())
                buffer.append("因灾,");
            if (family.getOfDisability() != null && family.getOfDisability())
                buffer.append("因残,");
            if (family.getLackSoil() != null && family.getLackSoil())
                buffer.append("缺土,");
            if (family.getLackWater() != null && family.getLackWater())
                buffer.append("缺水,");
            if (family.getLackTechnology() != null && family.getLackTechnology())
                buffer.append("缺技术,");
            if (family.getLackLabor() != null && family.getLackLabor())
                buffer.append("缺劳动力,");
            if (family.getLackMoney() != null && family.getLackMoney())
                buffer.append("缺资金,");
            if (family.getLackTraffic() != null && family.getLackTraffic())
                buffer.append("交通条件落后,");
            if (family.getLackNatureDevelopment() != null && family.getLackNatureDevelopment())
                buffer.append("自然发展动力不足,");
            if (family.getOthers() != null && family.getOthers())
                buffer.append("其他,");
            if (!buffer.equals("") && buffer.equals(" "))
                buffer.delete(buffer.length() - 1, buffer.length());//删除末尾的逗号
            subItems.put("mainReason", buffer);
            //添加地址
            subItems.put("location", "重庆市-" +
                    family.getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    family.getVillage().getTown().getTownName() +
                    "-" +
                    family.getVillage().getVillageName());
            incomePoint = 0;
            //经营性收入
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
                if (latest.getOperatingIncome() != null)
                    incomePoint += latest.getOperatingIncome();
                if (latest.getPropertyIncome() != null)
                    incomePoint += latest.getPropertyIncome();
                if (latest.getTransferIncome() != null)
                    incomePoint += latest.getTransferIncome();
                if (latest.getWageIncome() != null)
                    incomePoint += latest.getWageIncome();
                incomePoint /= family.getFamilyMembers().size(); //除以家庭人口数
                if (incomePoint < 3500)
                    incomePoint = 0;
                else if (incomePoint >= 3500)
                    incomePoint = 60 + 5 * ((incomePoint - 3500) / 500);
                if (incomePoint > 100)
                    incomePoint = 100;
            }
            //增加达标不达标信息
            if (incomePoint < 60) {
                notReachStandardFamily++;
                notReachStandardPeople += family.getFamilyMembers().size();
                subItems.put("colorOfNotReachStandardFamily", "#FFADAD");
            } else if (incomePoint < 80) {
                reachStandardFamily++;
                reachStandardPeople += family.getFamilyMembers().size();
                subItems.put("colorOfNotReachStandardFamily", "#FAE0E3");
            } else {
                reachStandardFamily++;
                reachStandardPeople += family.getFamilyMembers().size();
                subItems.put("colorOfNotReachStandardFamily", null);
            }
            subItems.put("incomePoint", incomePoint); //添加得分
            if (incomePoint < 60)
                subItems.put("colorOfIncomePoint", "#FF8700");
            else
                subItems.put("colorOfIncomePoint", "#2EA50A");
            //找到最后一张表然后判断危房等级
            boolean haveSafeHouse = false;
            if (family.getHouseRenovations() != null) {
                List<HouseRenovation> houseRenovationList = family.getHouseRenovations();
                Iterator<HouseRenovation> houseRenovationIterator = houseRenovationList.iterator();
                HouseRenovation houseRenovation, latest = null;
                while (houseRenovationIterator.hasNext()) {
                    houseRenovation = houseRenovationIterator.next();
                    if (latest == null)
                        latest = houseRenovation;
                    else if (latest.getFillInTime().before(houseRenovation.getFillInTime()))
                        latest = houseRenovation;
                }
                //if (latest != null && (latest.getHouseLevel().equals("A") || latest.getHouseLevel().equals("B")))
                if (latest != null && (latest.getHouseLevel().equals("AB级"))) //此处作了改动 1.18
                    haveSafeHouse = true;
                else
                    haveSafeHouse = false;
            }
            subItems.put("haveSafeHouse", haveSafeHouse);
            if (haveSafeHouse)
                subItems.put("colorOfHaveSafeHouse", "#2EA50A");
            else
                subItems.put("colorOfHaveSafeHouse", "#FF8700");
            //是否有安全饮水
            boolean haveSafeWater = false;
            if (family.getDrinkingSafeties() != null) {
                List<DrinkingSafety> drinkingSafetyList = family.getDrinkingSafeties();
                Iterator<DrinkingSafety> drinkingSafetyIterator = drinkingSafetyList.iterator();
                DrinkingSafety drinkingSafety, latest = null;
                while (drinkingSafetyIterator.hasNext()) {
                    drinkingSafety = drinkingSafetyIterator.next();
                    if (latest == null)
                        latest = drinkingSafety;
                    else if (latest.getFillInTime().before(drinkingSafety.getFillInTime()))
                        latest = drinkingSafety;
                }
                if (latest != null && latest.getSafeDrinkingWaterNow())
                    haveSafeWater = true;
                else
                    haveSafeWater = false;
            }
            subItems.put("haveSafeWater", haveSafeWater);
            if (haveSafeWater)
                subItems.put("colorOfHaveSafeWater", "#2EA50A");
            else
                subItems.put("colorOfHaveSafeWater", "#FF8700");
            //是否无因贫辍学学生
            boolean noDropoutStudents = false;
            if (family.getDropoutFigures() != null && family.getDropoutFigures() == 0)
                noDropoutStudents = true;
            else
                noDropoutStudents = false;
            subItems.put("noDropoutStudents", noDropoutStudents);
            if (noDropoutStudents)
                subItems.put("colorOfNoDropoutStudents", "#2EA50A");
            else
                subItems.put("colorOfNoDropoutStudents", "#FF8700");
            //是否参加新型农村合作医疗&&是否参加城乡居民基本养老保险
            boolean joinMedicalCooperation = true;//新型农村合作医疗
            boolean joinEndowmentInsurance = true;//城乡居民基本养老保险
            if (family.getFamilyMembers() != null) {
                List<FamilyMember> familyMemberList = family.getFamilyMembers();
                Iterator<FamilyMember> familyMemberIterator = familyMemberList.iterator();
                FamilyMember familyMember;
                while (familyMemberIterator.hasNext()) {
                    familyMember = familyMemberIterator.next();
                    if (familyMember.getJoinMedicalCooperation() != null && familyMember.getJoinMedicalCooperation() == false) {
                        joinMedicalCooperation = false;
                    }
                    if (familyMember.getJoinEndowmentInsurance() != null && familyMember.getJoinEndowmentInsurance() == false)
                        joinEndowmentInsurance = false;
                }
            }
            subItems.put("joinMedicalCooperation", joinMedicalCooperation);
            if (joinMedicalCooperation)
                subItems.put("colorOfJoinMedicalCooperation", "#2EA50A");
            else
                subItems.put("colorOfJoinMedicalCooperation", "#FF8700");
            subItems.put("joinEndowmentInsurance", joinEndowmentInsurance);
            if (joinEndowmentInsurance)
                subItems.put("colorOfJoinEndowmentInsurance", "#2EA50A");
            else
                subItems.put("colorOfJoinEndowmentInsurance", "#FF8700");
            //两不愁三保障
            //if(haveSafeHouse&&haveSafeWater&&haveSafeWater&&joinMedicalCooperation&&joinEndowmentInsurance)
            if (haveSafeHouse && haveSafeWater && noDropoutStudents && joinMedicalCooperation && joinEndowmentInsurance) { //此处作了改动 1.18
                subItems.put("totally", true);
                subItems.put("colorOfTotally", "#2EA50A");
            } else {
                subItems.put("totally", false);
                subItems.put("colorOfTotally", "red");
            }

            contentArray.add(subItems);
        }

        jsonObject.put("content", contentArray);
        jsonObject.put("totalFamilyNum", totalFamilyNum);
        jsonObject.put("totalPeopleNum", totalPeopleNum);
        jsonObject.put("reachStandardFamily", reachStandardFamily);
        jsonObject.put("reachStandardPeople", reachStandardPeople);
        jsonObject.put("notReachStandardFamily", notReachStandardFamily);
        jsonObject.put("notReachStandardPeople", notReachStandardPeople);
        jsonObject.put("rateOfReachStandardFamily", decimalFormat.format((float) (reachStandardFamily * 100) / totalFamilyNum) + "%");
        jsonObject.put("rateOfNotReachStandardFamily", decimalFormat.format((float) (notReachStandardFamily * 100) / totalFamilyNum) + "%");

        return jsonObject;
    }

}
