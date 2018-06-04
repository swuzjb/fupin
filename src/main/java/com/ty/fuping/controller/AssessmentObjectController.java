package com.ty.fuping.controller;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Town;
import com.ty.fuping.service.AssessmentObjectService;
import com.ty.fuping.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * Created by ty on 2017/11/11.
 */
@RestController
public class AssessmentObjectController {

    @Autowired
    private AssessmentObjectService assessmentObjectService;
    @Autowired
    private TownService townService;

    //添加考核对象
    @RequestMapping("/addassessmentobject")
    public AssessmentObject addAssessmentObject(AssessmentObject assessmentObject, @RequestParam("townId") Integer townId) {
        Town town = new Town();
        town.setTownId(townId);
        assessmentObject.setTown(town);
        return assessmentObjectService.addAssessmentObject(assessmentObject);
    }

    //修改考核对象
    @RequestMapping("/updateassessmentobject")
    public AssessmentObject updateAssessmentObject(AssessmentObject assessmentObject, @RequestParam("townId") Integer townId) {
        Town town = townService.getTownById(townId);
        assessmentObject.setTown(town);
        return assessmentObjectService.addAssessmentObject(assessmentObject);
    }

    //通过镇获取考核对象
    @RequestMapping("/getassessmentobjectbytown")
    public List<AssessmentObject> getAssessmentObjectByTown(@RequestParam("townId") Integer townId) {
        Town town = new Town();
        town.setTownId(townId);
        return assessmentObjectService.getAssessmentObjectByTown(town);
    }

    //删除考核对象
    @RequestMapping("/deleteassessmentobject")
    public void deleteAssessmenter(@RequestParam("assessmentObjectId") Integer assessmentOnbjectId) {
        assessmentObjectService.deleteAssessmentObjectById(assessmentOnbjectId);
    }

}
