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

    //给考核指标打分
    @Transactional
    public Point point(Point point, Double oldPoint) {
        Point p = pointRepository.save(point);
        //查出此人当前总得分
        AssessmentPoint assessmentPoint = assessmentPointRepository.findByAssessmentObjectOrderByPoint(point.getAssessmentObject());
        if (assessmentPoint == null) {
            assessmentPoint = new AssessmentPoint();
            assessmentPoint.setAssessmentObject(point.getAssessmentObject());
        }
        //assessmentPoint中的原来得分
        Double inpoint = assessmentPoint.getPoint();
        //设置最新得分：原来的得分加上新打的分减去此考核指标原来的分
        assessmentPoint.setPoint(inpoint + point.getPoint() - oldPoint);
        assessmentPointRepository.save(assessmentPoint);
        return p;
    }


    //删除记录by考核指标
    @Transactional
    public void deletePointByAssessmentIndex(AssessmentIndex assessmentIndex) {
        pointRepository.deleteAllByAssessmentIndex_AssessmentIndexId(assessmentIndex.getAssessmentIndexId());
    }


    //删除记录by考核对象
    @Transactional
    public void deletePointByAssessmentObject(AssessmentObject assessmentObject) {
        pointRepository.deleteAllByAssessmentObject(assessmentObject);
    }


    //通过考核指标和考核对象Id获取打分
    @Transactional
    public Point getPointByIndexAndObject(Integer indexId, Integer objectId) {
        return pointRepository.findByAssessmentIndex_AssessmentIndexIdAndAssessmentObject_AssessmentObjectId(indexId, objectId);
    }

    @Transactional
    //通过考核指标获取打分
    public List<Point> getPointByIndexId(Integer indexId) {
        return pointRepository.findAllByAssessmentIndex_AssessmentIndexId(indexId);
    }

}
