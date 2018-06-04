package com.ty.fuping.repository;

import com.ty.fuping.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ty on 2017/12/12.
 */
public interface FamilyRepository extends JpaRepository<Family, Integer>, JpaSpecificationExecutor<Family> {

    //通过村Id获取贫困户
    List<Family> findAllByVillage_VillageId(Integer villageId);

    //通过镇Id获取贫困户
    List<Family> findAllByVillage_Town_TownId(Integer townId);

    //通过区Id获取贫困户
    List<Family> findAllByVillage_Town_District_DistrictId(Integer districtId);

    //通过Id获取贫困户
    Family findByFamilyId(Integer familyId);

    //通过Id删除贫困户
    // @Query(value = "delete from family where family_id=?1 ", nativeQuery = true)
    @Modifying
    Integer deleteAllByFamilyId(Integer familyId);

    //某村家庭数目
    int countByVillage_VillageId(Integer villageId);

    //某镇家庭数目
    int countByVillage_Town_TownId(Integer townId);

    //某区家庭数目
    int countByVillage_Town_District_DistrictId(Integer districtId);

}
