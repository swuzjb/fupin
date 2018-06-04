package com.ty.fuping.repository;

import com.ty.fuping.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ty on 2017/11/11.
 */
public interface TownRepository extends JpaRepository<Town,Integer> {

    //通过区Id获取镇列表
    List<Town> findAllByDistrict_DistrictId(Integer districtId);
}

