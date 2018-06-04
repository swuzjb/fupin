package com.ty.fuping.repository;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
public interface AssessmentObjectRepository extends JpaRepository<AssessmentObject, Integer>, JpaSpecificationExecutor<AssessmentObject> {

    List<AssessmentObject> findAllByTown_TownId(Integer id);

    List<AssessmentObject> findAllByTown(Town town);

    //通过id删除
    Long deleteAllByAssessmentObjectId(Integer assessmentObjectId);

    //通过用户名查找
    AssessmentObject findByAccount(String userName);
}
