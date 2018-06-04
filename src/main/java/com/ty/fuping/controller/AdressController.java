package com.ty.fuping.controller;

import com.ty.fuping.entity.District;
import com.ty.fuping.entity.Town;
import com.ty.fuping.service.DistrictService;
import com.ty.fuping.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ty on 2017/11/3.
 */
@RestController
public class AdressController {

    @Autowired
    private DistrictService districtService;
    @Autowired
    private TownService townService;

    //获取所有地址列表
    @RequestMapping("/getadressall")
    public List<District> getAdressAll(){
        return districtService.findDistrictAll();
    }

    //添加区
    @RequestMapping("/adddistrict")
    public District addDistrict(District district){
        return districtService.addDistrict(district);
    }

    //添加镇
    @RequestMapping("/addtown")
    public Town addTown(@RequestParam("districtId") Integer districtId,Town town){
        District district=new District();
        district.setDistrictId(districtId);
        town.setDistrict(district);
        return townService.addTown(town);
    }

}
