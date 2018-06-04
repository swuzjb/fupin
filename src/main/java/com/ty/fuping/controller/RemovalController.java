package com.ty.fuping.controller;

import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.Removal;
import com.ty.fuping.entity.WorkingAndLivingCondition;
import com.ty.fuping.service.RemovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ty on 2017/12/16.
 * 易地搬迁
 */
@RestController
public class RemovalController {
    @Autowired
    private RemovalService removalService;

    //新增搬迁记录
    @Transactional
    @RequestMapping("/addremovel")
    public Removal addRemoval(Removal removal,@RequestParam("familyId") Integer familyId){
        Family family=new Family();
        family.setFamilyId(familyId);
        removal.setFamily(family);
        return removalService.addRemoval(removal);
    }

    //通过家庭Id获取搬迁记录
    @RequestMapping("/getremovalbyfamilyid")
    public List<Removal> getRemovalByFamilyId(@RequestParam("familyId") Integer familyId){
        return removalService.getRemovalByFamilyId(familyId);
    }

    //删除搬迁记录
    @RequestMapping("/deleteremovalbyid")
    public void deleteremovalById(@RequestParam("removalId") Integer removalId){
        removalService.deleteremovalById(removalId);
    }
}
