package com.ty.fuping.repository;

import com.ty.fuping.entity.AssessmentIndex;
import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
public interface PointRepository extends JpaRepository<Point, Integer> {

    //通过考核对象删除
    Long deleteAllByAssessmentObject(AssessmentObject assessmentObject);

    //通过考核计划删除
    Long deleteAllByAssessmentIndex(AssessmentIndex assessmentIndex);

    //通过考核计划Id删除
    @Query(value = "delete from point where assessment_index_id=?1 ", nativeQuery = true)
    @Modifying
    Integer deleteAllByAssessmentIndex_AssessmentIndexId(Integer assessmentIndexId);

    //通过考核指标和考核对象Id获取打分
    Point findByAssessmentIndex_AssessmentIndexIdAndAssessmentObject_AssessmentObjectId(Integer assessmentIndexId, Integer assessmentObjectId);

    //通过考核指标Id获取打分
    List<Point> findAllByAssessmentIndex_AssessmentIndexId(Integer assessmentIndexId);
}
