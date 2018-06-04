package com.ty.fuping.service;

import com.ty.fuping.entity.AssessmentIndex;
import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.AssessmentPoint;
import com.ty.fuping.entity.Point;
import com.ty.fuping.repository.AssessmentPointRepository;
import com.ty.fuping.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private AssessmentPointRepository assessmentPointRepository;

    @Transactional
    //给考核指标打分
    public Point point(Point point) {
        Point p = pointRepository.save(point);
        AssessmentPoint assessmentPoint = assessmentPointRepository.findByAssessmentObjectOrderByPoint(point.getAssessmentObject());
        if (assessmentPoint == null) {
            assessmentPoint = new AssessmentPoint();
            assessmentPoint.setAssessmentObject(point.getAssessmentObject());
        }
        Double inpoint = assessmentPoint.getPoint();
        inpoint = point.getPoint();
        assessmentPoint.setPoint(inpoint);
        assessmentPointRepository.save(assessmentPoint);
        return p;
    }

    @Transactional
    //删除记录by考核指标
    public void deletePointByAssessmentIndex(AssessmentIndex assessmentIndex) {
        pointRepository.deleteAllByAssessmentIndex_AssessmentIndexId(assessmentIndex.getAssessmentIndexId());
    }

    @Transactional
    //删除记录by考核对象
    public void deletePointByAssessmentObject(AssessmentObject assessmentObject) {
        pointRepository.deleteAllByAssessmentObject(assessmentObject);
    }

    @Transactional
    //通过考核指标和考核对象Id获取打分
    public Point getPointByIndexAndObject(Integer indexId, Integer objectId){
        return pointRepository.findByAssessmentIndex_AssessmentIndexIdAndAssessmentObject_AssessmentObjectId(indexId,objectId);
    }

    @Transactional
    //通过考核指标获取打分
    public List<Point> getPointByIndexId(Integer indexId){
        return pointRepository.findAllByAssessmentIndex_AssessmentIndexId(indexId);
    }

}
