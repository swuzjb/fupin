package com.ty.fuping.repository;

import com.ty.fuping.entity.Removal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/12/15.
 */
public interface RemovalRepository extends JpaRepository<Removal, Integer> {

    //通过家庭Id获取搬迁记录
    List<Removal> findAllByFamily_FamilyId(Integer familyId);

    //通过家庭Id删除
    Integer deleteAllByFamily_FamilyId(Integer familyId);
}
