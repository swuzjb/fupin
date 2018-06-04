package com.ty.fuping.controller;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Assistance;
import com.ty.fuping.entity.Family;
import com.ty.fuping.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ty on 2017/12/17.
 * 帮扶责任人
 */
@RestController
public class AssistanceController {
    @Autowired
    private AssistanceService assistanceService;

    //添加帮扶责任人
    @RequestMapping("/addassistance")
    public Assistance addAssistance(Assistance assistance,
                                    @RequestParam("familyId") Integer familyId,
                                    @RequestParam("assessmentObjectId") Integer assessmentObjectId){
        Family family=new Family();
        family.setFamilyId(familyId);
        AssessmentObject assessmentObject=new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        assistance.setFamily(family);
        assistance.setAssessmentObject(assessmentObject);
        return assistanceService.addAssistance(assistance);
    }

    //通过家庭Id获取帮扶责任人
    @RequestMapping("/getassistancebyfamilyid")
    public List<Assistance> getAssistanceByFamilyId(@RequestParam("familyId") Integer familyId){
        return assistanceService.getAssistanceByFamilyId(familyId);
    }

    //删除搬迁记录
    @RequestMapping("/deleteassistancebyid")
    public void deleteAssistanceById(@RequestParam("assistanceId") Integer assistanceId){
        assistanceService.deleteAssistanceById(assistanceId);
    }
}
