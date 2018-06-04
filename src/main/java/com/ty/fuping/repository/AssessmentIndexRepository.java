package com.ty.fuping.repository;

import com.ty.fuping.entity.AssessmentIndex;
import com.ty.fuping.entity.AssessmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
public interface AssessmentIndexRepository extends JpaRepository<AssessmentIndex,Integer> {

    List<AssessmentIndex> findAllByAssessmentPlan(AssessmentPlan assessmentPlan);

    List<AssessmentIndex> findAllByAssessmentPlan_AssessmentPlanId(Integer AssessmentPlanId);

    //通过指标Id删除
    @Query(value = "delete from assessment_index where assessment_index_id=?1 ", nativeQuery = true)
    @Modifying
    Integer deleteAllByAssessmentIndexId(Integer assessmentIndexId);
}
