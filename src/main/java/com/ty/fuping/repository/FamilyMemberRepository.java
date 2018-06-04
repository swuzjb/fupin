package com.ty.fuping.repository;

import com.ty.fuping.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/12/14.
 */
public interface FamilyMemberRepository extends JpaRepository<FamilyMember,Integer> {

    //通过家庭Id获取家庭成员们
    List<FamilyMember> findAllByFamily_FamilyId(Integer familyId);

    //通过家庭Id删除
    Integer deleteAllByFamily_FamilyId(Integer familyId);
}
