package com.ty.fuping.controller;

import com.ty.fuping.entity.AssessmentandAssessmenter;
import com.ty.fuping.entity.Assessmenter;
import com.ty.fuping.entity.Town;
import com.ty.fuping.service.AssessmentPlanService;
import com.ty.fuping.service.AssessmenterService;
import com.ty.fuping.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ty on 2017/11/11.
 */
@RestController
public class AssessmenterController {

    @Autowired
    private AssessmenterService assessmenterService;
    @Autowired
    private TownService townService;

    @RequestMapping("/addassessmenter")
    //添加考核者
    public Assessmenter addAssessmenter(Assessmenter assessmenter, @RequestParam("townId") Integer townId) {
        Town town = new Town();
        town.setTownId(townId);
        assessmenter.setTown(town);
        return assessmenterService.addAssessmenter(assessmenter);
    }

    //修改考核者
    @RequestMapping("/updateassessmenter")
    public Assessmenter updateAssessmentObject(Assessmenter assessmenter, @RequestParam("townId") Integer townId){
        Town town=townService.getTownById(townId);
        assessmenter.setTown(town);
        return assessmenterService.addAssessmenter(assessmenter);
    }

    //通过镇获取考核者
    @RequestMapping("/getassessmenterbytown")
    public List<Assessmenter> getAssessmenterByTown(@RequestParam("townId") Integer townId) {
        return assessmenterService.getAssessmenterByTown(townId);
    }

    //删除考核者
    @RequestMapping("/deleteassessmenter")
    public void deleteAssessmenter(@RequestParam("assessmenterId") Integer assessmenterId){
        assessmenterService.deleteAssessmenterById(assessmenterId);
    }
}
