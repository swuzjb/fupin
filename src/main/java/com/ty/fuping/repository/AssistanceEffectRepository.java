package com.ty.fuping.repository;

import com.ty.fuping.entity.AssistanceEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/12/23.
 */
public interface AssistanceEffectRepository extends JpaRepository<AssistanceEffect,Integer> {

    //通过家庭Id获取帮扶成效
    List<AssistanceEffect> findAllByFamily_FamilyId(Integer familyId);

    //通过家庭Id删除
    Integer deleteAllByFamily_FamilyId(Integer familyId);
}
