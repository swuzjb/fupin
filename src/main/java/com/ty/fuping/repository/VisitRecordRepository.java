package com.ty.fuping.repository;

import com.ty.fuping.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/12/23.
 */
public interface VisitRecordRepository extends JpaRepository<VisitRecord, Integer> {

    List<VisitRecord> findAllByFamily_FamilyId(Integer familyId);
}
