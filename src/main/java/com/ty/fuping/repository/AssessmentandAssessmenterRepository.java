package com.ty.fuping.repository;

import com.ty.fuping.entity.AssessmentPlan;
import com.ty.fuping.entity.AssessmentandAssessmenter;
import com.ty.fuping.entity.Assessmenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
public interface AssessmentandAssessmenterRepository extends JpaRepository<AssessmentandAssessmenter,Integer> {

    //通过考核计划删除
    Long deleteAllByAssessmentPlan(AssessmentPlan assessmentPlan);

    //通过考核计划Id获取
    List<AssessmentandAssessmenter> getAllByAssessmentPlan_AssessmentPlanId(Integer assessmentPlanId);

    //通过考核计划Id删除
    @Query(value = "delete from assessmentand_assessmenter where assessment_plan_id=?1 ", nativeQuery = true)
    @Modifying
    void deleteAllByAssessmentPlan_AssessmentPlanId(Integer objectId);

    //通过考核者Id删除
    @Query(value = "delete from assessmentand_assessmenter where assessmenter_id=?1 ", nativeQuery = true)
    @Modifying
    void deleteAllByAssessmenter_AssessmenterId(Integer assessmenterId);
}
