package com.ty.fuping.controller;

import com.ty.fuping.entity.*;
import com.ty.fuping.repository.AssessmentandAssessmenterRepository;
import com.ty.fuping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/1.
 */
@RestController
public class PerformanceAppraisalController {

    @Autowired
    private AssessmenterService assessmenterService;
    @Autowired
    private AssessmentIndexService assessmentIndexService;
    @Autowired
    private AssessmentObjectService assessmentObjectService;
    @Autowired
    private AssessmentPlanService assessmentPlanService;
    @Autowired
    private PointService pointService;
    @Autowired
    private AssessmentPointService assessmentPointService;
    @Autowired
    private AssessmentandAssessmenterRepository assessmentandAssessmenterRepository;

    @Transactional
    @RequestMapping("/test")
    public void test() {
        System.out.println(System.getProperty("catalina.home"));
    }

    @RequestMapping("/test2")
    public Integer test2(@RequestParam("para") Integer i){
        return i;
    }


}
