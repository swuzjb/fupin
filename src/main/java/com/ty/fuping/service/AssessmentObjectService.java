package com.ty.fuping.service;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.AssessmentandObject;
import com.ty.fuping.entity.Town;
import com.ty.fuping.repository.*;
import com.ty.fuping.repository.AssistanceMeasureRepository.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
@Service
public class AssessmentObjectService {
    @Autowired
    private AssessmentObjectRepository assessmentObjectRepository;
    @Autowired
    private AssessmentandObjectRepository assessmentandObjectRepository;
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
    private PointRepository pointRepository;
    @Autowired
    private AssessmentPointRepository assessmentPointRepository;

    @Transactional
    //添加被调查者
    public AssessmentObject addAssessmentObject(AssessmentObject assessmentObject) {
        return assessmentObjectRepository.save(assessmentObject);
    }

    //获取所有被调查者
    public List<AssessmentObject> getAssessmentObjectAll() {
        return assessmentObjectRepository.findAll();
    }

    //通过地址获取被调查者
    public List<AssessmentObject> getAssessmentObjectByTown(Town town) {
        return assessmentObjectRepository.findAllByTown(town);
    }

    //通过地址Id获取被调查者
    public List<AssessmentObject> getAssessmentObjectByTownId(Integer townId) {
        return assessmentObjectRepository.findAllByTown_TownId(townId);
    }

    @Transactional
    //通过考核计划Id获取考核对象
    public List<AssessmentObject> getAssessmenterByPlanId(Integer planId) {
        List<AssessmentandObject> assessmentandObjects = assessmentandObjectRepository.getAllByAssessmentPlan_AssessmentPlanId(planId);
        List<AssessmentObject> assessmentObjectList = new ArrayList<>();
        Iterator<AssessmentandObject> it = assessmentandObjects.iterator();
        while (it.hasNext()) {
            assessmentObjectList.add(it.next().getAssessmentObject());
        }
        return assessmentObjectList;
    }

    //通过Id获取考核对象
    public AssessmentObject getAssessmentObjectById(Integer id) {
        return assessmentObjectRepository.findOne(id);
    }

    //通过多条件模糊查询科技特派员
    public List<AssessmentObject> searchAssessmentObjects(AssessmentObject assessmentObject) {

        Assert.assertNotNull(assessmentObject);

        List<AssessmentObject> assessmentObjectList = assessmentObjectRepository.findAll(new Specification<AssessmentObject>() {
            @Override
            public Predicate toPredicate(Root<AssessmentObject> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (assessmentObject.getName() != null && !assessmentObject.getName().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + assessmentObject.getName() + "%"));
                }
                if (assessmentObject.getTown() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("town").get("townId"), assessmentObject.getTown().getTownId()));
                }
                if (assessmentObject.getDanweiaddress() != null && !assessmentObject.getDanweiaddress().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("danweiaddress"), "%" + assessmentObject.getDanweiaddress() + "%"));
                }
                if (assessmentObject.getPhoneNumber() != null && !assessmentObject.getPhoneNumber().equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%" + assessmentObject.getPhoneNumber() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return assessmentObjectList;
    }

    @Transactional
    //删除考核对象
    public void deleteAssessmentObjectById(Integer assessmentObjectId) {
        drinkingSafetyRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);//删除饮水安全记录
        financialAssistanceRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);//删除金融扶贫记录
        houseRenovationRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);//删除危房改造记录
        laborTrainingRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);//删除劳动力培训记录
        prosperousIndustryRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);//删除富民产业记录
        socialAssistanceRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);//删除社会援助记录
        specialPovertyAlleviationRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);//删除专项扶贫记录
        //删除所有评分(包括指标得分和计划得分)
        pointRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);
        assessmentPointRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);

        assessmentandObjectRepository.deleteAllByAssessmentObject_AssessmentObjectId(assessmentObjectId);
        assessmentObjectRepository.deleteAllByAssessmentObjectId(assessmentObjectId);
    }

    //通过用户名获取考核对象
    public AssessmentObject findByUserName(String userName) {
        return assessmentObjectRepository.findByAccount(userName);
    }
}
