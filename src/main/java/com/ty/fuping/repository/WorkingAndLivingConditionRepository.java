package com.ty.fuping.repository;

import com.ty.fuping.entity.WorkingAndLivingCondition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ty on 2017/12/14.
 */
public interface WorkingAndLivingConditionRepository extends JpaRepository<WorkingAndLivingCondition,Integer> {

    //通过家庭Id获取生产生活条件
    WorkingAndLivingCondition getByFamily_FamilyId(Integer familyId);

    //通过家庭Id删除
    Integer deleteAllByFamily_FamilyId(Integer familyId);
}
