package com.ty.fuping.service;

import com.ty.fuping.entity.District;
import com.ty.fuping.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ty on 2017/11/11.
 */
@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    //获取所有地址列表
    public List<District> findDistrictAll(){
        return districtRepository.findAll();
    }

    //添加区
    public District addDistrict(District district){
        return districtRepository.save(district);
    }

}
