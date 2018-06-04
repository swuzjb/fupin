package com.ty.fuping.repository;

import com.ty.fuping.entity.Assessmenter;
import com.ty.fuping.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
public interface AssessmenterRepository extends JpaRepository<Assessmenter, Integer>, JpaSpecificationExecutor<Assessmenter> {

    List<Assessmenter> getAllByTown(Town town);

    @Query(value = "delete from assessmenter where assessmenter_id=?1 ", nativeQuery = true)
    @Modifying
        //通过id删除
    Integer deleteAssessmenterByAssessmenterId(Integer assessmenterId);

    //通过用户名查找
    Assessmenter findByAccount(String userName);
}
