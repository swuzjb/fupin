package com.ty.fuping.service;

import com.ty.fuping.entity.AssessmentIndex;
import com.ty.fuping.entity.AssessmentPlan;
import com.ty.fuping.entity.AssessmentPoint;
import com.ty.fuping.entity.Point;
import com.ty.fuping.repository.AssessmentIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
@Service
public class AssessmentIndexService {

    @Autowired
    private AssessmentIndexRepository assessmentIndexRepository;
    @Autowired
    private AssessmentPointService assessmentPointService;
    @Autowired
    private PointService pointService;

    @Transactional
    //添加考核指标
    public AssessmentIndex addAssessmentIndex(AssessmentPlan assessmentPlan, AssessmentIndex assessmentIndex){
        assessmentIndex.setAssessmentPlan(assessmentPlan);
        return assessmentIndexRepository.save(assessmentIndex);
    }

    @Transactional
    //删除考核指标
    public void deleteAssessmentIndex(AssessmentIndex assessmentIndex){
        Point point;
        AssessmentPoint assessmentPoint;
        List<Point> pointList = pointService.getPointByIndexId(assessmentIndex.getAssessmentIndexId());
        Iterator<Point> it = pointList.iterator();
        while (it.hasNext()) {
            point = it.next();
            //在考核得分表里面减分
            assessmentPoint=assessmentPointService.getAssessmentPointByObject(point.getAssessmentObject());
            assessmentPoint.setPoint(assessmentPoint.getPoint()-point.getPoint());
            assessmentPointService.saveAssessmentPoint(assessmentPoint);
        }
        pointService.deletePointByAssessmentIndex(assessmentIndex);//删除打分详细

        assessmentIndexRepository.deleteAllByAssessmentIndexId(assessmentIndex.getAssessmentIndexId());
    }

    @Transactional
    //删除某个考核计划的考核指标
    public void deleteAssessmentIndexByPlan(AssessmentPlan assessmentPlan){
        Iterator it=assessmentIndexRepository.findAllByAssessmentPlan(assessmentPlan).iterator();
        while (it.hasNext()){
            deleteAssessmentIndex((AssessmentIndex)it.next());
        }
    }

    //通过考核计划获取其指标
    public List<AssessmentIndex> getAssessmentIndexbyPlan(AssessmentPlan assessmentPlan){
        return assessmentIndexRepository.findAllByAssessmentPlan(assessmentPlan);
    }

    //通过考核计划Id获取其指标
    public List<AssessmentIndex> getAssessmentIndexbyPlanId(Integer assessmentPlanId){
        return assessmentIndexRepository.findAllByAssessmentPlan_AssessmentPlanId(assessmentPlanId);
    }

    //通过Id获取考核指标
    public AssessmentIndex getAssessmentIndexbyId(Integer assessmentIndexId){
        return assessmentIndexRepository.getOne(assessmentIndexId);
    }
}
