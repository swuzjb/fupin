package com.ty.fuping.repository;

import com.ty.fuping.entity.AssessmentPlan;
import com.ty.fuping.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
public interface AssessmentPlanRepository extends JpaRepository<AssessmentPlan,Integer> {

    List<AssessmentPlan> getAllByTown(Town town);

    //通过计划Id删除
    @Query(value = "delete from assessment_plan where assessment_plan_id=?1 ", nativeQuery = true)
    @Modifying
    Integer deleteAllByAssessmentPlanId(Integer assessmentPlanId);
}
