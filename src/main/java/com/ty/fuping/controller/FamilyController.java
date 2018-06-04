package com.ty.fuping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.FamilyMember;
import com.ty.fuping.entity.Village;
import com.ty.fuping.entity.WorkingAndLivingCondition;
import com.ty.fuping.service.FamilyMemberService;
import com.ty.fuping.service.FamilyService;
import com.ty.fuping.service.WorkingAndLivingConditionService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ty on 2017/12/16.
 */
@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyService;
    @Autowired
    private FamilyMemberService familyMemberService;
    @Autowired
    private WorkingAndLivingConditionService workingAndLivingConditionService;

    //通过村id获取贫困户
    @RequestMapping("/getfamiliesbyvillageid")
    public JSONArray getFamiliesByVillageId(@RequestParam("villageId") Integer villageId) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        Iterator<Family> it;
        Family family;

        List<Family> familyList = familyService.getFamilyByVillageId(villageId);
        it = familyList.iterator();
        StringBuffer buffer;
        while (it.hasNext()) {
            family = it.next();
            jsonObject = new JSONObject();
            jsonObject.put("familyId", family.getFamilyId());
            jsonObject.put("name", family.getFamilyOwner().getName());
            jsonObject.put("povertyGrade", family.getPovertyGrade());
            /**
             * 添加主要致贫原因
             */
            buffer = new StringBuffer("");
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
            jsonObject.put("mainReason", buffer);
            jsonObject.put("membersNumber", family.getFamilyMembers().size());
            jsonObject.put("location", "重庆市-" +
                    family.getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    family.getVillage().getTown().getTownName() +
                    "-" +
                    family.getVillage().getVillageName());
            if (family.getPovertyState() == null)
                jsonObject.put("povertyState", null);
            else if (family.getPovertyState() == 1)
                jsonObject.put("povertyState", "未脱贫");
            else if (family.getPovertyState() == 2)
                jsonObject.put("povertyState", "预脱贫");
            else if (family.getPovertyState() == 3)
                jsonObject.put("povertyState", "已脱贫");
            else
                jsonObject.put("povertyState", null);
            jsonObject.put("hasHelpPolicy", family.getHasHelpPolicy());
            jsonObject.put("backToPoverty", family.getBackToPoverty());

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    //新增贫困户
    @Transactional
    @RequestMapping("/addfamily")
    public Family addFamily(Family family, FamilyMember familyMember, @RequestParam("villageId") Integer villageId) {
        familyMember.setRelationsWithFamilyOwner("户主");
        familyMember.setFamily(family);
        family.setFamilyMembers(new LinkedList<>());
        Village village = new Village();
        village.setVillageId(villageId);
        family.setVillage(village);
        family = familyService.addFamily(family);
        familyMember = familyMemberService.addFamilyMember(familyMember);
        family.getFamilyMembers().add(familyMember);
        family.setFamilyOwner(familyMember);
        //为贫困户添加空白的生产生活条件
        WorkingAndLivingCondition workingAndLivingCondition = new WorkingAndLivingCondition();
        workingAndLivingCondition.setFamily(family);
        workingAndLivingConditionService.addWALCondition(workingAndLivingCondition);
        return family;
    }

    //修改贫困户
    @Transactional
    @RequestMapping("/updatefamily")
    public Family updateFamily(Family family, FamilyMember familyMember, @RequestParam("villageId") Integer villageId) {
        //删除旧的户主
        Family oldFamily = familyService.getFamilyById(family.getFamilyId());
        familyMemberService.deleteFamilyMemberById(oldFamily.getFamilyOwner().getFimilyMenberId());

        familyMember.setRelationsWithFamilyOwner("户主");
        familyMember.setFamily(family);
        family.setFamilyMembers(new LinkedList<>());
        Village village = new Village();
        village.setVillageId(villageId);
        family.setVillage(village);
        family = familyService.addFamily(family);
        familyMember = familyMemberService.addFamilyMember(familyMember);
        family.getFamilyMembers().add(familyMember);
        family.setFamilyOwner(familyMember);
        return family;
    }

    //高级搜索贫困户
    @Transactional
    @RequestMapping("/searchfamily")
    public JSONArray searchFamily(Family family,
                                  FamilyMember familyMember,
                                  @RequestParam(name = "villageId", required = false) Integer villageId)
            throws ParseException, JsonProcessingException {
        if (family == null)
            family = new Family();
        if (familyMember != null)
            family.setFamilyOwner(familyMember);
        if (villageId != null) {
            Village village = new Village();
            village.setVillageId(villageId);
            family.setVillage(village);
        }

        List<Family> familyList = familyService.searchFamily(family);
        ObjectMapper mapper = new ObjectMapper();
        Iterator<Family> iterator = familyList.iterator();
        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        String data;
        StringBuffer buffer;

        while (iterator.hasNext()) {
            family = iterator.next();
            data = mapper.writeValueAsString(family);
            jsonObject = (JSONObject) jsonParser.parse(data);
            jsonObject.put("name", family.getFamilyOwner().getName());
            jsonObject.put("IDCard", family.getFamilyOwner().getIDCard());

            buffer = new StringBuffer("");
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
            jsonObject.put("mainReason", buffer);
            jsonObject.put("membersNumber", family.getFamilyMembers().size());
            jsonObject.put("location", "重庆市-" +
                    family.getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    family.getVillage().getTown().getTownName() +
                    "-" +
                    family.getVillage().getVillageName());

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    //删除贫困户
    @RequestMapping("/deletefamily")
    public void deleteFamily(@RequestParam("familyId") Integer familyId) {
        familyService.deleteFamily(familyId);
    }


}
