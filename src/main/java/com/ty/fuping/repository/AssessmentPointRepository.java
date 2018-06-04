package com.ty.fuping.repository;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.AssessmentPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
public interface AssessmentPointRepository extends JpaRepository<AssessmentPoint,Integer>{

    //通过考核对象获取得分(得分高到低排序)
    AssessmentPoint findByAssessmentObjectOrderByPoint(AssessmentObject assessmentObject);

    //通过考核对象Id删除
    Long deleteAllByAssessmentObject_AssessmentObjectId(Integer assessmentObjectId);

}
