package com.ty.fuping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.AssistanceMeasure.*;
import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.FamilyMember;
import com.ty.fuping.repository.AssistanceMeasureRepository.*;
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
 * Created by ty on 2017/12/23.
 * 帮扶措施
 */
@RestController
public class AssistanceMeasureController {
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

    //新增饮水安全
    @RequestMapping("/adddrinkingsafety")
    public DrinkingSafety addDrinkingSafety(DrinkingSafety drinkingSafety,
                                            @RequestParam("familyId") Integer familyId,
                                            @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        drinkingSafety.setFamily(family);
        drinkingSafety.setAssessmentObject(assessmentObject);
        return drinkingSafetyRepository.save(drinkingSafety);
    }

    //修改饮水安全
    @Transactional
    @RequestMapping("/updatedrinkingsafety")
    public DrinkingSafety updateDrinkingSafety(DrinkingSafety drinkingSafety,
                                               @RequestParam("familyId") Integer familyId,
                                               @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        drinkingSafety.setFamily(family);
        drinkingSafety.setAssessmentObject(assessmentObject);
        return drinkingSafetyRepository.save(drinkingSafety);
    }

    //通过家庭Id获取饮水安全
    @RequestMapping("/getdrinkingsafetybyfamilyid")
    public List<DrinkingSafety> getDrinkingSafetyByFamilyId(@RequestParam("familyId") Integer familyId) {
        return drinkingSafetyRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除饮水安全
    @Transactional
    @RequestMapping("/deledrinkingsafetybyid")
    public void deleteDrinkingSafetyById(@RequestParam("drinkingSafetyId") Integer drinkingSafetyId) {
        drinkingSafetyRepository.delete(drinkingSafetyId);
    }

    //新增危房改造
    @RequestMapping("/addhouserenovation")
    public HouseRenovation addHouseRenovation(HouseRenovation houseRenovation,
                                              @RequestParam("familyId") Integer familyId,
                                              @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        houseRenovation.setFamily(family);
        houseRenovation.setAssessmentObject(assessmentObject);
        return houseRenovationRepository.save(houseRenovation);
    }

    //修改危房改造
    @Transactional
    @RequestMapping("/updatehouserenovation")
    public HouseRenovation updateHouseRenovation(HouseRenovation houseRenovation,
                                                 @RequestParam("familyId") Integer familyId,
                                                 @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        houseRenovation.setFamily(family);
        houseRenovation.setAssessmentObject(assessmentObject);
        return houseRenovationRepository.save(houseRenovation);
    }

    //通过家庭Id获取危房改造
    @RequestMapping("/gethouserenovationbyfamilyid")
    public List<HouseRenovation> getHouseRenovationByFamilyId(@RequestParam("familyId") Integer familyId) {
        return houseRenovationRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除危房改造
    @Transactional
    @RequestMapping("/deletehouserenovationbyid")
    public void deleteHouseRenovationById(@RequestParam("houseRenovationId") Integer houseRenovationId) {
        houseRenovationRepository.delete(houseRenovationId);
    }


    //新增金融扶贫
    @RequestMapping("/addfinancialassistance")
    public FinancialAssistance addSocialAssistance(FinancialAssistance financialAssistance,
                                                   @RequestParam("familyId") Integer familyId,
                                                   @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        financialAssistance.setFamily(family);
        financialAssistance.setAssessmentObject(assessmentObject);
        return financialAssistanceRepository.save(financialAssistance);
    }

    //修改金融扶贫
    @Transactional
    @RequestMapping("/updatefinancialassistance")
    public FinancialAssistance updateFinancialAssistance(FinancialAssistance financialAssistance,
                                                         @RequestParam("familyId") Integer familyId,
                                                         @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        financialAssistance.setFamily(family);
        financialAssistance.setAssessmentObject(assessmentObject);
        return financialAssistanceRepository.save(financialAssistance);
    }

    //通过家庭Id获取金融扶贫
    @RequestMapping("/getfinancialassistancebyfamilyid")
    public List<FinancialAssistance> getFinancialAssistanceByFamilyId(@RequestParam("familyId") Integer familyId) {
        return financialAssistanceRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除金融扶贫
    @Transactional
    @RequestMapping("/deletefinancialassistancebyid")
    public void deleteFinancialAssistanceById(@RequestParam("financialAssistanceId") Integer financialAssistanceId) {
        financialAssistanceRepository.delete(financialAssistanceId);
    }

    //金融扶贫信息

    //新增社会援助
    @RequestMapping("/addsocialassistance")
    public SocialAssistance addSocialAssistance(SocialAssistance socialAssistance,
                                                @RequestParam("familyId") Integer familyId,
                                                @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        socialAssistance.setFamily(family);
        socialAssistance.setAssessmentObject(assessmentObject);
        return socialAssistanceRepository.save(socialAssistance);
    }

    //修改社会援助
    @Transactional
    @RequestMapping("/updatesocialassistance")
    public SocialAssistance updateSocialAssistance(SocialAssistance socialAssistance,
                                                   @RequestParam("familyId") Integer familyId,
                                                   @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        socialAssistance.setFamily(family);
        socialAssistance.setAssessmentObject(assessmentObject);
        return socialAssistanceRepository.save(socialAssistance);
    }

    //通过家庭Id获取社会援助
    @RequestMapping("/getsocialassistancebyfamilyid")
    public List<SocialAssistance> getSocialAssistanceByFamilyId(@RequestParam("familyId") Integer familyId) {
        return socialAssistanceRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除社会援助
    @Transactional
    @RequestMapping("/deletesocialassistancebyid")
    public void deleteSocialAssistanceById(@RequestParam("socialAssistanceId") Integer socialAssistanceId) {
        socialAssistanceRepository.delete(socialAssistanceId);
    }

    //新增劳动力培训
    @RequestMapping("/addlabortraining")
    public LaborTraining addLaborTraining(LaborTraining laborTraining,
                                          @RequestParam("familyId") Integer familyId,
                                          @RequestParam("assessmentObjectId") Integer assessmentObjectId,
                                          @RequestParam("familyMemberId") Integer familyMemberId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFimilyMenberId(familyMemberId);
        laborTraining.setFamily(family);
        laborTraining.setAssessmentObject(assessmentObject);
        laborTraining.setFamilyMember(familyMember);
        return laborTrainingRepository.save(laborTraining);
    }

    //修改劳动力培训
    @Transactional
    @RequestMapping("/updatelabortraining")
    public LaborTraining updateLaborTraining(LaborTraining laborTraining,
                                             @RequestParam("familyId") Integer familyId,
                                             @RequestParam("assessmentObjectId") Integer assessmentObjectId,
                                             @RequestParam("familyMemberId") Integer familyMemberId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        FamilyMember familyMember = new FamilyMember();
        familyMember.setFimilyMenberId(familyMemberId);
        laborTraining.setFamily(family);
        laborTraining.setAssessmentObject(assessmentObject);
        laborTraining.setFamilyMember(familyMember);
        return laborTrainingRepository.save(laborTraining);
    }

    //通过家庭Id获取劳动力培训
    @RequestMapping("/getlabortrainingbyfamilyid")
    public List<LaborTraining> getLaborTrainingByFamilyId(@RequestParam("familyId") Integer familyId) {
        return laborTrainingRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除劳动力培训
    @Transactional
    @RequestMapping("/deletelabortrainingbyid")
    public void deleteLaborTrainingById(@RequestParam("laborTrainingId") Integer labortrainingId) {
        laborTrainingRepository.delete(labortrainingId);
    }

    //新增专项扶贫
    @RequestMapping("/addspecialpovertyalleviation")
    public SpecialPovertyAlleviation addSpecialPovertyAlleviation(SpecialPovertyAlleviation specialPovertyAlleviation,
                                                                  @RequestParam("familyId") Integer familyId,
                                                                  @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        specialPovertyAlleviation.setFamily(family);
        specialPovertyAlleviation.setAssessmentObject(assessmentObject);
        return specialPovertyAlleviationRepository.save(specialPovertyAlleviation);
    }

    //修改专项扶贫
    @Transactional
    @RequestMapping("/updatespecialpovertyalleviation")
    public SpecialPovertyAlleviation updateSpecialPovertyAlleviation(SpecialPovertyAlleviation specialPovertyAlleviation,
                                                                     @RequestParam("familyId") Integer familyId,
                                                                     @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        specialPovertyAlleviation.setFamily(family);
        specialPovertyAlleviation.setAssessmentObject(assessmentObject);
        return specialPovertyAlleviationRepository.save(specialPovertyAlleviation);
    }

    //通过家庭Id获取专项扶贫
    @RequestMapping("/getspecialpovertyalleviationbyfamilyid")
    public List<SpecialPovertyAlleviation> getSpecialPovertyAlleviationByFamilyId(@RequestParam("familyId") Integer familyId) {
        return specialPovertyAlleviationRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除专项扶贫
    @Transactional
    @RequestMapping("/deletespecialpovertyalleviationbyid")
    public void deleteSpecialPovertyAlleviationById(@RequestParam("specialPovertyAlleviationId") Integer specialPovertyAlleviationId) {
        specialPovertyAlleviationRepository.delete(specialPovertyAlleviationId);
    }

    //新增富民产业
    @RequestMapping("/addprosperousindustry")
    public ProsperousIndustry addProsperousIndustry(ProsperousIndustry prosperousIndustry,
                                                    @RequestParam("familyId") Integer familyId,
                                                    @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        prosperousIndustry.setFamily(family);
        prosperousIndustry.setAssessmentObject(assessmentObject);
        return prosperousIndustryRepository.save(prosperousIndustry);
    }

    //修改富民产业
    @Transactional
    @RequestMapping("/updateprosperousindustry")
    public ProsperousIndustry updateProsperousIndustry(ProsperousIndustry prosperousIndustry,
                                                       @RequestParam("familyId") Integer familyId,
                                                       @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        Family family = new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        prosperousIndustry.setFamily(family);
        prosperousIndustry.setAssessmentObject(assessmentObject);
        return prosperousIndustryRepository.save(prosperousIndustry);
    }

    //通过家庭Id获取富民产业
    @RequestMapping("/getprosperousindustrybyfamilyid")
    public List<ProsperousIndustry> getProsperousIndustryByFamilyId(@RequestParam("familyId") Integer familyId) {
        return prosperousIndustryRepository.findAllByFamily_FamilyId(familyId);
    }

    //删除富民产业
    @Transactional
    @RequestMapping("/deleteprosperousindustrybyid")
    public void deleteProsperousIndustryById(@RequestParam("prosperousIndustryId") Integer prosperousIndustryId) {
        prosperousIndustryRepository.delete(prosperousIndustryId);
    }

}
