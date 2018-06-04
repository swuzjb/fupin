package com.ty.fuping.service;

import com.ty.fuping.entity.*;
import com.ty.fuping.repository.AssessmentPlanRepository;
import com.ty.fuping.repository.AssessmentandAssessmenterRepository;
import com.ty.fuping.repository.AssessmentandObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
@Service
public class AssessmentPlanService {

    @Autowired
    private AssessmentPlanRepository assessmentPlanRepository;
    @Autowired
    private AssessmentandObjectRepository assessmentandObjectRepository;
    @Autowired
    private AssessmentandAssessmenterRepository assessmentandAssessmenterRepository;

    @Transactional
    //添加考核计划
    public AssessmentPlan addAssessmentPlan(AssessmentPlan assessmentPlan, List<Assessmenter> assessmenterList, List<AssessmentObject> assessmentObjectList) {
        AssessmentPlan assessmentPlan1 = assessmentPlanRepository.save(assessmentPlan);
        AssessmentandAssessmenter assessmentandAssessmenter;
        AssessmentandObject assessmentandObject;
        Iterator it;
        it = assessmenterList.iterator();
        while (it.hasNext()) {
            assessmentandAssessmenter = new AssessmentandAssessmenter();
            assessmentandAssessmenter.setAssessmentPlan(assessmentPlan);
            assessmentandAssessmenter.setAssessmenter((Assessmenter) it.next());
            assessmentandAssessmenterRepository.save(assessmentandAssessmenter);
        }
        it = assessmentObjectList.iterator();
        while (it.hasNext()) {
            assessmentandObject = new AssessmentandObject();
            assessmentandObject.setAssessmentPlan(assessmentPlan);
            assessmentandObject.setAssessmentObject((AssessmentObject) it.next());
            assessmentandObjectRepository.save(assessmentandObject);
        }
        return assessmentPlan1;
    }

    @Transactional
    //更新考核计划
    public AssessmentPlan updateAssessmentPlan(AssessmentPlan assessmentPlan, List<Assessmenter> assessmenterList, List<AssessmentObject> assessmentObjectList) {
        AssessmentPlan assessmentPlan1 = assessmentPlanRepository.save(assessmentPlan);
       //先删除原来的
        assessmentandObjectRepository.deleteAllByAssessmentPlan_AssessmentPlanId(assessmentPlan.getAssessmentPlanId());
        assessmentandAssessmenterRepository.deleteAllByAssessmentPlan_AssessmentPlanId(assessmentPlan.getAssessmentPlanId());

        AssessmentandAssessmenter assessmentandAssessmenter;
        AssessmentandObject assessmentandObject;
        Iterator it;
        it = assessmenterList.iterator();
        while (it.hasNext()) {
            assessmentandAssessmenter = new AssessmentandAssessmenter();
            assessmentandAssessmenter.setAssessmentPlan(assessmentPlan);
            assessmentandAssessmenter.setAssessmenter((Assessmenter) it.next());
            assessmentandAssessmenterRepository.save(assessmentandAssessmenter);
        }
        it = assessmentObjectList.iterator();
        while (it.hasNext()) {
            assessmentandObject = new AssessmentandObject();
            assessmentandObject.setAssessmentPlan(assessmentPlan);
            assessmentandObject.setAssessmentObject((AssessmentObject) it.next());
            assessmentandObjectRepository.save(assessmentandObject);
        }
        return assessmentPlan1;
    }

    //获取所有考核计划
    public List<AssessmentPlan> getAssessmentPlanAll() {
        return assessmentPlanRepository.findAll();
    }

    //通过id获取某个考核计划
    public AssessmentPlan getAssessmentPlanbyId(Integer id) {
        return assessmentPlanRepository.findOne(id);
    }

    @Transactional
    //删除考核计划
    public void deleteAssessmentPlan(AssessmentPlan assessmentPlan) {
        //assessmentIndexService.deleteAssessmentIndexByPlan(assessmentPlan); //删除此计划的所有指标
        //assessmentandObjectRepository.deleteAllByAssessmentPlan(assessmentPlan);//删除此计划的所有对象
        //assessmentandAssessmenterRepository.deleteAllByAssessmentPlan(assessmentPlan);//删除此计划的所有考核者
        assessmentandAssessmenterRepository.deleteAllByAssessmentPlan_AssessmentPlanId(assessmentPlan.getAssessmentPlanId());
        assessmentandObjectRepository.deleteAllByAssessmentPlan_AssessmentPlanId(assessmentPlan.getAssessmentPlanId());
        assessmentPlanRepository.deleteAllByAssessmentPlanId(assessmentPlan.getAssessmentPlanId());
    }

    @Transactional
    //获取某个人的考核计划
    public List<AssessmentPlan> getPlanByObject(AssessmentObject assessmentObject) {
        Iterator it = assessmentandObjectRepository.getAllByAssessmentObject(assessmentObject).iterator();
        List<AssessmentPlan> assessmentPlanList = new ArrayList<AssessmentPlan>();
        while (it.hasNext()) {
            assessmentPlanList.add(((AssessmentandObject) it.next()).getAssessmentPlan());
        }
        return assessmentPlanList;
    }

    @Transactional
    //获取某个镇的考核计划
    public List<AssessmentPlan> getPlanByAddress(Integer townId) {
        Town town = new Town();
        town.setTownId(townId);
        return assessmentPlanRepository.getAllByTown(town);
    }

}
