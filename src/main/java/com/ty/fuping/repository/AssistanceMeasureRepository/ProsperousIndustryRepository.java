package com.ty.fuping.repository.AssistanceMeasureRepository;

import com.ty.fuping.entity.AssistanceMeasure.ProsperousIndustry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/12/23.
 */
public interface ProsperousIndustryRepository extends JpaRepository<ProsperousIndustry, Integer> {

    //通过家庭Id获取富民产业
    List<ProsperousIndustry> findAllByFamily_FamilyId(Integer familyId);

    //通过家庭Id删除
    Integer deleteAllByFamily_FamilyId(Integer familyId);

    //通过考核对象删除
    Integer deleteAllByAssessmentObject_AssessmentObjectId(Integer AssessmentObjectId);

    //通过村Id找
    List<ProsperousIndustry> findAllByFamily_Village_VillageId(Integer villageId);

    //通过镇Id找
    List<ProsperousIndustry> findAllByFamily_Village_Town_TownId(Integer townId);

    //通过区Id找
    List<ProsperousIndustry> findAllByFamily_Village_Town_District_DistrictId(Integer districtId);
}
