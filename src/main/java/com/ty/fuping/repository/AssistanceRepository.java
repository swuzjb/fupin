package com.ty.fuping.repository;

import com.ty.fuping.entity.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/12/15.
 */
public interface AssistanceRepository extends JpaRepository<Assistance,Integer> {

    //通过家庭Id获取帮扶责任人
    List<Assistance> findAllByFamily_FamilyId(Integer familyId);

    //通过家庭Id删除
    Integer deleteAllByFamily_FamilyId(Integer familyId);
}
