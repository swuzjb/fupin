package com.ty.fuping.service;

import com.ty.fuping.entity.Town;
import com.ty.fuping.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ty on 2017/11/11.
 */
@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    //添加镇
    public Town addTown(Town town){
        return townRepository.save(town);
    }

    public Town getTownById(Integer townId){
        return townRepository.findOne(townId);
    }
}
