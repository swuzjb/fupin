package com.ty.fuping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.fuping.entity.AssistanceMeasure.*;
import com.ty.fuping.entity.Family;
import com.ty.fuping.repository.AssistanceMeasureRepository.*;
import com.ty.fuping.repository.FamilyRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2018/1/15.
 * 帮扶措施管理
 */
@RestController
public class AssistanceMaesureManagementController {
    @Autowired
    private DrinkingSafetyRepository drinkingSafetyRepository;
    @Autowired
    private FinancialAssistanceRepository financialAssistanceRepository;
    @Autowired
    private HouseRenovationRepository houseRenovationRepository;
    @Autowired
    private LaborTrainingRepository laborTrainingRepository;
    @Autowired
    private ProsperousIndustryRepository prosperousIndustryRepository;
    @Autowired
    private SocialAssistanceRepository socialAssistanceRepository;
    @Autowired
    private SpecialPovertyAlleviationRepository specialPovertyAlleviationRepository;
    @Autowired
    private FamilyRepository familyRepository;

    //获取饮水安全相关资料
    @Transactional
    @RequestMapping("drinkingsafety")
    public JSONObject drinkingSafety(@RequestParam(value = "villageId", required = false) Integer villageId,
                                     @RequestParam(value = "townId", required = false) Integer townId,
                                     @RequestParam(value = "districtId", required = false) Integer districtId) throws JsonProcessingException, ParseException {
        List<DrinkingSafety> drinkingSafeties = null;
        JSONArray jsonArray = new JSONArray();
        int solveSafeDrinkingWater = 0; //已稳定解决饮水安全户数
        int solveInThisYear = 0; //当前年度已解决户数
        int solveSafeDrinkingWaterAndTapWaterUser = 0; //已稳定解决饮水安全且自来水入户户数
        long familyNum = 0; //本次采集户数
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject result = new JSONObject();
        Calendar c = Calendar.getInstance();
        Iterator it;
        String content;
        if (villageId != null) {
            drinkingSafeties = drinkingSafetyRepository.findAllByFamily_Village_VillageId(villageId);
            familyNum = familyRepository.countByVillage_VillageId(villageId);
        } else if (townId != null) {
            drinkingSafeties = drinkingSafetyRepository.findAllByFamily_Village_Town_TownId(townId);
            familyNum = familyRepository.countByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            drinkingSafeties = drinkingSafetyRepository.findAllByFamily_Village_Town_District_DistrictId(districtId);
            familyNum = familyRepository.countByVillage_Town_District_DistrictId(districtId);
        } else {
            drinkingSafeties = drinkingSafetyRepository.findAll();
            familyNum = familyRepository.count();
        }
        DrinkingSafety drinkingSafety;
        it = drinkingSafeties.iterator();
        while (it.hasNext()) {
            drinkingSafety = (DrinkingSafety) it.next();

            if (drinkingSafety.getSafeDrinkingWaterNow() != null && drinkingSafety.getSafeDrinkingWaterNow()) {
                solveSafeDrinkingWater++;
                if (drinkingSafety.getTapWaterUser() != null && drinkingSafety.getTapWaterUser())
                    solveSafeDrinkingWaterAndTapWaterUser++;
            }
            if (drinkingSafety.getSolveTime() != null && drinkingSafety.getSolveTime().equals(c.get(Calendar.YEAR) + ""))
                solveInThisYear++;
            content = mapper.writeValueAsString(drinkingSafety);
            jsonObject = (JSONObject) jsonParser.parse(content);
            jsonObject.put("familyOwnerName", drinkingSafety.getFamily().getFamilyOwner().getName());
            jsonObject.put("familyOwnerIDCard", drinkingSafety.getFamily().getFamilyOwner().getIDCard());
            jsonObject.put("location", "重庆市-" +
                    drinkingSafety.getFamily().getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    drinkingSafety.getFamily().getVillage().getTown().getTownName() +
                    "-" +
                    drinkingSafety.getFamily().getVillage().getVillageName());

            jsonArray.add(jsonObject);
        }

        result.put("content", jsonArray);
        result.put("familyNum", familyNum);
        result.put("solveSafeDrinkingWater", solveSafeDrinkingWater);
        result.put("solveInThisYear", solveInThisYear);
        result.put("solveSafeDrinkingWaterAndTapWaterUser", solveSafeDrinkingWaterAndTapWaterUser);

        return result;
    }


    //获取危房改造相关信息
    @Transactional
    @RequestMapping("houserenovation")
    public JSONObject houseRenovation(@RequestParam(value = "villageId", required = false) Integer villageId,
                                      @RequestParam(value = "townId", required = false) Integer townId,
                                      @RequestParam(value = "districtId", required = false) Integer districtId) throws JsonProcessingException, ParseException {

        List<HouseRenovation> houseRenovations = null;
        JSONArray jsonArray = new JSONArray();
        int houseRenovationsnNum = 0; //危房改造户数
        double plannedTotalInvestment = 0; //危房改造投资金额
        double governmentFunds = 0; //政府补助金额
        long familyNum = 0; //本次采集户数
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject result = new JSONObject();
        Calendar c = Calendar.getInstance();
        Iterator it;
        String content;
        if (villageId != null) {
            houseRenovations = houseRenovationRepository.findAllByFamily_Village_VillageId(villageId);
            familyNum = familyRepository.countByVillage_VillageId(villageId);
        } else if (townId != null) {
            houseRenovations = houseRenovationRepository.findAllByFamily_Village_Town_TownId(townId);
            familyNum = familyRepository.countByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            houseRenovations = houseRenovationRepository.findAllByFamily_Village_Town_District_DistrictId(districtId);
            familyNum = familyRepository.countByVillage_Town_District_DistrictId(districtId);
        } else {
            houseRenovations = houseRenovationRepository.findAll();
            familyNum = familyRepository.count();
        }
        HouseRenovation houseRenovation;
        it = houseRenovations.iterator();
        while (it.hasNext()) {
            houseRenovation = (HouseRenovation) it.next();

            houseRenovationsnNum++;
            if (houseRenovation.getPlannedInvestment() != null) {
                plannedTotalInvestment += houseRenovation.getPlannedInvestment();
            }
            if (houseRenovation.getCentralFunds() != null) {
                governmentFunds += houseRenovation.getCentralFunds();
            }
            if (houseRenovation.getProvincialFunds() != null) {
                governmentFunds += houseRenovation.getProvincialFunds();
            }
            if (houseRenovation.getCountyFunds() != null) {
                governmentFunds += houseRenovation.getCountyFunds();
            }

            content = mapper.writeValueAsString(houseRenovation);
            jsonObject = (JSONObject) jsonParser.parse(content);
            jsonObject.put("familyOwnerName", houseRenovation.getFamily().getFamilyOwner().getName());
            jsonObject.put("familyOwnerIDCard", houseRenovation.getFamily().getFamilyOwner().getIDCard());
            jsonObject.put("location", "重庆市-" +
                    houseRenovation.getFamily().getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    houseRenovation.getFamily().getVillage().getTown().getTownName() +
                    "-" +
                    houseRenovation.getFamily().getVillage().getVillageName());

            jsonArray.add(jsonObject);
        }

        result.put("content", jsonArray);
        result.put("familyNum", familyNum);
        result.put("houseRenovationsnNum", houseRenovationsnNum);
        result.put("plannedTotalInvestment", plannedTotalInvestment);
        result.put("governmentFunds", governmentFunds);

        return result;
    }

    //金融扶贫相关信息
    @Transactional
    @RequestMapping("financialassistance")
    public JSONObject financialAssistance(@RequestParam(value = "villageId", required = false) Integer villageId,
                                          @RequestParam(value = "townId", required = false) Integer townId,
                                          @RequestParam(value = "districtId", required = false) Integer districtId) throws JsonProcessingException, ParseException {

        List<FinancialAssistance> financialAssistances = null;
        JSONArray jsonArray = new JSONArray();
        int loadNum = 0; //贷款户数
        double loadAmount = 0; //贷款金额
        double discountAmount = 0; //应贴息金额
        long familyNum = 0; //本次采集户数
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject result = new JSONObject();
        Calendar c = Calendar.getInstance();
        Iterator it;
        String content;
        if (villageId != null) {
            financialAssistances = financialAssistanceRepository.findAllByFamily_Village_VillageId(villageId);
            familyNum=familyRepository.countByVillage_VillageId(villageId);
        } else if (townId != null) {
            financialAssistances = financialAssistanceRepository.findAllByFamily_Village_Town_TownId(townId);
            familyNum=familyRepository.countByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            financialAssistances = financialAssistanceRepository.findAllByFamily_Village_Town_District_DistrictId(districtId);
            familyNum=familyRepository.countByVillage_Town_District_DistrictId(districtId);
        } else {
            financialAssistances = financialAssistanceRepository.findAll();
            familyNum=familyRepository.count();
        }
        FinancialAssistance financialAssistance;
        it = financialAssistances.iterator();
        while (it.hasNext()) {
            financialAssistance = (FinancialAssistance) it.next();
            loadNum++;
            if (financialAssistance.getLoadAmount() != null)
                loadAmount += financialAssistance.getLoadAmount();
            if (financialAssistance.getDiscountAmount() != null)
                discountAmount += financialAssistance.getDiscountAmount();
            content = mapper.writeValueAsString(financialAssistance);
            jsonObject = (JSONObject) jsonParser.parse(content);
            jsonObject.put("familyOwnerName", financialAssistance.getFamily().getFamilyOwner().getName());
            jsonObject.put("familyOwnerIDCard", financialAssistance.getFamily().getFamilyOwner().getIDCard());
            jsonObject.put("location", "重庆市-" +
                    financialAssistance.getFamily().getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    financialAssistance.getFamily().getVillage().getTown().getTownName() +
                    "-" +
                    financialAssistance.getFamily().getVillage().getVillageName());

            jsonArray.add(jsonObject);
        }

        result.put("content", jsonArray);
        result.put("familyNum", familyNum);
        result.put("loadNum", loadNum);
        result.put("loadAmount", loadAmount);
        result.put("discountAmount", discountAmount);

        return result;
    }

    //劳动力培训相关信息
    @Transactional
    @RequestMapping("labortraining")
    public JSONObject laborTraining(@RequestParam(value = "villageId", required = false) Integer villageId,
                                    @RequestParam(value = "townId", required = false) Integer townId,
                                    @RequestParam(value = "districtId", required = false) Integer districtId) throws JsonProcessingException, ParseException {

        List<LaborTraining> laborTrainings = null;
        JSONArray jsonArray = new JSONArray();
        int trainingNum = 0; //培训完成人次
        int trainingNeededNum = 0; //有培训总需求人数
        int Fund = 0; //资金投入
        long familyNum = 0; //本次采集户数
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject result = new JSONObject();
        Calendar c = Calendar.getInstance();
        Iterator it;
        String content;
        if (villageId != null) {
            laborTrainings = laborTrainingRepository.findAllByFamily_Village_VillageId(villageId);
            familyNum=familyRepository.countByVillage_VillageId(villageId);
        } else if (townId != null) {
            laborTrainings = laborTrainingRepository.findAllByFamily_Village_Town_TownId(townId);
            familyNum=familyRepository.countByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            laborTrainings = laborTrainingRepository.findAllByFamily_Village_Town_District_DistrictId(districtId);
            familyNum=familyRepository.countByVillage_Town_District_DistrictId(districtId);
        } else {
            laborTrainings = laborTrainingRepository.findAll();
            familyNum=familyRepository.count();
        }
        LaborTraining laborTraining;
        it = laborTrainings.iterator();
        while (it.hasNext()) {
            laborTraining = (LaborTraining) it.next();
            trainingNum++;
            if ((laborTraining.getCultivation() != null && laborTraining.getCultivation())
                    || (laborTraining.getPlant() != null && laborTraining.getPlant())
                    || (laborTraining.getCatering() != null && laborTraining.getCatering())
                    || (laborTraining.getHousekeeping() != null && laborTraining.getHousekeeping())
                    || (laborTraining.getBuildingService() != null && laborTraining.getBuildingService())
                    || (laborTraining.getIndustry() != null && laborTraining.getIndustry())
                    || (laborTraining.getStartUp() != null && laborTraining.getStartUp())
                    || (laborTraining.getOperativeTechnology() != null && laborTraining.getOperativeTechnology())
                    || (laborTraining.getOthers() != null && laborTraining.getOthers()))
                trainingNeededNum++;
            if (laborTraining.getEmploymentFund() != null)
                Fund += laborTraining.getEmploymentFund();
            if (laborTraining.getHusbandryFund() != null)
                Fund += laborTraining.getHusbandryFund();
            if (laborTraining.getPovertyReliefFund() != null)
                Fund += laborTraining.getPovertyReliefFund();
            if (laborTraining.getOtherFund() != null)
                Fund += laborTraining.getOtherFund();

            content = mapper.writeValueAsString(laborTraining);
            jsonObject = (JSONObject) jsonParser.parse(content);
            jsonObject.put("familyOwnerName", laborTraining.getFamily().getFamilyOwner().getName());
            jsonObject.put("familyOwnerIDCard", laborTraining.getFamily().getFamilyOwner().getIDCard());
            jsonObject.put("location", "重庆市-" +
                    laborTraining.getFamily().getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    laborTraining.getFamily().getVillage().getTown().getTownName() +
                    "-" +
                    laborTraining.getFamily().getVillage().getVillageName());

            jsonArray.add(jsonObject);
        }

        result.put("content", jsonArray);
        result.put("familyNum", familyNum);
        result.put("trainingNum", trainingNum);
        result.put("trainingNeededNum", trainingNeededNum);
        result.put("Fund", Fund);

        return result;
    }


    //富民产业相关信息\
    @Transactional
    @RequestMapping("prosperousindustry")
    public JSONObject prosperousIndustry(@RequestParam(value = "villageId", required = false) Integer villageId,
                                         @RequestParam(value = "townId", required = false) Integer townId,
                                         @RequestParam(value = "districtId", required = false) Integer districtId) throws JsonProcessingException, ParseException {

        List<ProsperousIndustry> prosperousIndustries = null;
        JSONArray jsonArray = new JSONArray();
        long familyNum = 0;//总户数
        int prosperousIndustryNum = 0; //富民产业受益户数
        double totalIndustryTotalIncome = 0; //富民产业总收入
        double workTotalIncome = 0; //劳务经济收入
        double loanTotalAmount = 0; //农村金融贷款总额
        double discountTotalAmount = 0;//农村金融应贴息金额
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject result = new JSONObject();
        Calendar c = Calendar.getInstance();
        Iterator it;
        String content;
        if (villageId != null) {
            prosperousIndustries = prosperousIndustryRepository.findAllByFamily_Village_VillageId(villageId);
            familyNum=familyRepository.countByVillage_VillageId(villageId);
        } else if (townId != null) {
            prosperousIndustries = prosperousIndustryRepository.findAllByFamily_Village_Town_TownId(townId);
            familyNum=familyRepository.countByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            prosperousIndustries = prosperousIndustryRepository.findAllByFamily_Village_Town_District_DistrictId(districtId);
            familyNum=familyRepository.countByVillage_Town_District_DistrictId(districtId);
        } else {
            prosperousIndustries = prosperousIndustryRepository.findAll();
            familyNum=familyRepository.count();
        }
        ProsperousIndustry prosperousIndustry;
        it = prosperousIndustries.iterator();
        while (it.hasNext()) {
            prosperousIndustry = (ProsperousIndustry) it.next();
            prosperousIndustryNum++;
            if (prosperousIndustry.getTotalIndustryIncome() != null)
                totalIndustryTotalIncome += prosperousIndustry.getTotalIndustryIncome();
            if (prosperousIndustry.getWorkIncome() != null)
                workTotalIncome += prosperousIndustry.getWorkIncome();
            if (prosperousIndustry.getLoanAmount() != null)
                loanTotalAmount += prosperousIndustry.getLoanAmount();
            if (prosperousIndustry.getDiscountAmount() != null)
                discountTotalAmount += prosperousIndustry.getDiscountAmount();


            content = mapper.writeValueAsString(prosperousIndustry);
            jsonObject = (JSONObject) jsonParser.parse(content);
            jsonObject.put("familyOwnerName", prosperousIndustry.getFamily().getFamilyOwner().getName());
            jsonObject.put("familyOwnerIDCard", prosperousIndustry.getFamily().getFamilyOwner().getIDCard());
            jsonObject.put("location", "重庆市-" +
                    prosperousIndustry.getFamily().getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    prosperousIndustry.getFamily().getVillage().getTown().getTownName() +
                    "-" +
                    prosperousIndustry.getFamily().getVillage().getVillageName());

            jsonArray.add(jsonObject);
        }

        result.put("content", jsonArray);
        result.put("familyNum", familyNum);
        result.put("prosperousIndustryNum", prosperousIndustryNum);
        result.put("totalIndustryTotalIncome", totalIndustryTotalIncome);
        result.put("workTotalIncome", workTotalIncome);
        result.put("loanTotalAmount", loanTotalAmount);
        result.put("discountTotalAmount", discountTotalAmount);

        return result;
    }


    //社会援助相关信息
    @Transactional
    @RequestMapping("socialassistance")
    public JSONObject socialAssistance(@RequestParam(value = "villageId", required = false) Integer villageId,
                                       @RequestParam(value = "townId", required = false) Integer townId,
                                       @RequestParam(value = "districtId", required = false) Integer districtId) throws JsonProcessingException, ParseException {

        List<SocialAssistance> socialAssistances = null;
        JSONArray jsonArray = new JSONArray();
        long familyNum = 0;//总户数
        int socialAssistanceTotalIncome = 0; //社会救助资金投入
        double minimumLivingStandardTotalIncome = 0; //农村低保金额
        double fiveGuaranteesTotalIncome = 0; //五保供养金额
        double medicalAssistanceTotalIncome = 0; //医疗救助金额
        double temporaryAssistanceTotalIncome = 0;//临时救助金额
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject result = new JSONObject();
        Calendar c = Calendar.getInstance();
        Iterator it;
        String content;
        if (villageId != null) {
            socialAssistances = socialAssistanceRepository.findAllByFamily_Village_VillageId(villageId);
            familyNum=familyRepository.countByVillage_VillageId(villageId);
        } else if (townId != null) {
            socialAssistances = socialAssistanceRepository.findAllByFamily_Village_Town_TownId(townId);
            familyNum=familyRepository.countByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            socialAssistances = socialAssistanceRepository.findAllByFamily_Village_Town_District_DistrictId(districtId);
            familyNum=familyRepository.countByVillage_Town_District_DistrictId(districtId);
        } else {
            socialAssistances = socialAssistanceRepository.findAll();
            familyNum=familyRepository.count();
        }
        SocialAssistance socialAssistance;
        it = socialAssistances.iterator();
        while (it.hasNext()) {
            socialAssistance = (SocialAssistance) it.next();

            if (socialAssistance.getSocialAssistanceIncome() != null)
                socialAssistanceTotalIncome += socialAssistance.getSocialAssistanceIncome();
            if (socialAssistance.getMinimumLivingStandardIncome() != null)
                minimumLivingStandardTotalIncome += socialAssistance.getMinimumLivingStandardIncome();
            if (socialAssistance.getFiveGuaranteesIncome() != null)
                fiveGuaranteesTotalIncome += socialAssistance.getFiveGuaranteesIncome();
            if (socialAssistance.getMedicalAssistanceIncome() != null)
                medicalAssistanceTotalIncome += socialAssistance.getMedicalAssistanceIncome();
            if (socialAssistance.getTemporaryAssistanceIncome() != null)
                temporaryAssistanceTotalIncome += socialAssistance.getTemporaryAssistanceIncome();


            content = mapper.writeValueAsString(socialAssistance);
            jsonObject = (JSONObject) jsonParser.parse(content);
            jsonObject.put("familyOwnerName", socialAssistance.getFamily().getFamilyOwner().getName());
            jsonObject.put("familyOwnerIDCard", socialAssistance.getFamily().getFamilyOwner().getIDCard());
            jsonObject.put("location", "重庆市-" +
                    socialAssistance.getFamily().getVillage().getTown().getDistrict().getDistrictName() +
                    "-" +
                    socialAssistance.getFamily().getVillage().getTown().getTownName() +
                    "-" +
                    socialAssistance.getFamily().getVillage().getVillageName());

            jsonArray.add(jsonObject);
        }

        result.put("content", jsonArray);
        result.put("familyNum", familyNum);
        result.put("socialAssistanceTotalIncome", socialAssistanceTotalIncome);
        result.put("minimumLivingStandardTotalIncome", minimumLivingStandardTotalIncome);
        result.put("fiveGuaranteesTotalIncome", fiveGuaranteesTotalIncome);
        result.put("medicalAssistanceTotalIncome", medicalAssistanceTotalIncome);
        result.put("temporaryAssistanceTotalIncome", temporaryAssistanceTotalIncome);

        return result;
    }


    //专项扶贫相关信息
    @Transactional
    @RequestMapping("specialpovertyalleviation")
    public JSONObject specialPovertyAlleviation(@RequestParam(value = "villageId", required = false) Integer villageId,
                                                @RequestParam(value = "townId", required = false) Integer townId,
                                                @RequestParam(value = "districtId", required = false) Integer districtId) throws JsonProcessingException, ParseException {
        List<Family> familyList = null;
        JSONArray subItemsArray;
        JSONArray contentJsonArray = new JSONArray();
        int specialPovertyNum = 0; //受益户数
        double totalFunds = 0; //专项扶贫到户合计金额
        double discountTotalAmount = 0; //到户贴息金额
        long familyNum = 0; //本次采集户数
        ObjectMapper mapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        JSONObject contentObject;
        JSONObject result = new JSONObject();
        Calendar c = Calendar.getInstance();
        Iterator familyIt, it2;
        String content;
        if (villageId != null) {
            familyList = familyRepository.findAllByVillage_VillageId(villageId);
        } else if (townId != null) {
            familyList = familyRepository.findAllByVillage_Town_TownId(townId);
        } else if (districtId != null) {
            familyList = familyRepository.findAllByVillage_Town_District_DistrictId(districtId);
        } else {
            familyList = familyRepository.findAll();
        }
        familyNum = familyList.size();
        Family family;
        List<SpecialPovertyAlleviation> specialPovertyAlleviations;
        SpecialPovertyAlleviation specialPovertyAlleviation = null;
        familyIt = familyList.iterator();
        while (familyIt.hasNext()) {
            contentObject = new JSONObject();
            family = (Family) familyIt.next();
            specialPovertyAlleviations = family.getSpecialPovertyAlleviations();
            it2 = specialPovertyAlleviations.iterator();
            subItemsArray = new JSONArray();
            while (it2.hasNext()) {
                specialPovertyAlleviation = (SpecialPovertyAlleviation) it2.next();
                specialPovertyNum++;
                if (specialPovertyAlleviation.getSubsidyFunds() != null)
                    totalFunds += specialPovertyAlleviation.getSubsidyFunds();
                if (specialPovertyAlleviation.getLoadAmount() != null)
                    totalFunds += specialPovertyAlleviation.getLoadAmount();
                if (specialPovertyAlleviation.getMutualFunds() != null)
                    totalFunds += specialPovertyAlleviation.getMutualFunds();
                if (specialPovertyAlleviation.getDiscountAmount() != null)
                    discountTotalAmount += specialPovertyAlleviation.getDiscountAmount();

                content = mapper.writeValueAsString(specialPovertyAlleviation);
                jsonObject = (JSONObject) jsonParser.parse(content);
                subItemsArray.add(jsonObject);
            }
            contentObject.put("subItems", subItemsArray);

            if (family != null) {
                contentObject.put("familyOwnerName", family.getFamilyOwner().getName());
                contentObject.put("familyOwnerIDCard", family.getFamilyOwner().getIDCard());
                contentObject.put("location", "重庆市-" +
                        family.getVillage().getTown().getDistrict().getDistrictName() +
                        "-" +
                        family.getVillage().getTown().getTownName() +
                        "-" +
                        family.getVillage().getVillageName());
            }
            contentJsonArray.add(contentObject);
        }

        result.put("content", contentJsonArray);
        result.put("familyNum", familyNum);
        result.put("specialPovertyNum", specialPovertyNum);
        result.put("totalFunds", totalFunds);
        result.put("discountTotalAmount", discountTotalAmount);

        return result;
    }

}
