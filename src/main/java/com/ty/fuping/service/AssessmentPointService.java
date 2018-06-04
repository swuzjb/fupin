package com.ty.fuping.service;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.AssessmentPoint;
import com.ty.fuping.repository.AssessmentPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
@Service
public class AssessmentPointService {

    @Autowired
    private AssessmentPointRepository assessmentPointRepository;

    //获取某人的得分
    public AssessmentPoint getAssessmentPointByObject(AssessmentObject assessmentObject){
        return assessmentPointRepository.findByAssessmentObjectOrderByPoint(assessmentObject);
    }

    //修改保存得分
    public AssessmentPoint saveAssessmentPoint(AssessmentPoint assessmentPoint){
        return assessmentPointRepository.save(assessmentPoint);
    }

}
