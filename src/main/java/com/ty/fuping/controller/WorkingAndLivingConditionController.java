package com.ty.fuping.controller;

import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.FamilyMember;
import com.ty.fuping.entity.WorkingAndLivingCondition;
import com.ty.fuping.service.WorkingAndLivingConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ty on 2017/12/16.
 */
@RestController
public class WorkingAndLivingConditionController {

    @Autowired
    private WorkingAndLivingConditionService workingAndLivingConditionService;

    //修改生产生活条件
    @Transactional
    @RequestMapping("/updatewalcondition")
    public WorkingAndLivingCondition updateWALCondition(WorkingAndLivingCondition workingAndLivingCondition,
                                                        @RequestParam("familyId") Integer familyId){
        Family family=new Family();
        family.setFamilyId(familyId);
        workingAndLivingCondition.setFamily(family);
        return workingAndLivingConditionService.addWALCondition(workingAndLivingCondition);
    }

    //通过家庭Id获取生产生活条件
    @RequestMapping("/getwalconditionbyfamilyid")
    public WorkingAndLivingCondition getWALConditionByFamilyId(@RequestParam("familyId") Integer familyId){
        return workingAndLivingConditionService.getWALConditionByFamilyId(familyId);
    }

    //删除生产生活条件
    @RequestMapping("/deletewalconditionbyid")
    public void deleteWALConditionById(@RequestParam("WALCId") Integer WALCId){
        workingAndLivingConditionService.deleteWALConditionById(WALCId);
    }

}
