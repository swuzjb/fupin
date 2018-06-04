package com.ty.fuping.repository;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.AssessmentPlan;
import com.ty.fuping.entity.AssessmentPoint;
import com.ty.fuping.entity.AssessmentandObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
public interface AssessmentandObjectRepository extends JpaRepository<AssessmentandObject,Integer> {

    //通过考核计划删除
    @Transactional
    Long deleteAllByAssessmentPlan(AssessmentPlan assessmentPlan);

    //获取某考核对象的考核计划
    List<AssessmentandObject>  getAllByAssessmentObject(AssessmentObject assessmentObject);

    //通过考核计划Id获取
    List<AssessmentandObject> getAllByAssessmentPlan_AssessmentPlanId(Integer assessmentPlanId);

    @Transactional
    //通过考核对象Id删除
    @Query(value = "delete from assessmentand_object where assessment_object_id=?1 ", nativeQuery = true)
    @Modifying
    void deleteAllByAssessmentObject_AssessmentObjectId(Integer objectId);

    //通过考核计划Id删除
    @Query(value = "delete from assessmentand_object where assessment_plan_id=?1 ", nativeQuery = true)
    @Modifying
    void deleteAllByAssessmentPlan_AssessmentPlanId(Integer planId);

}
