package com.ty.fuping.controller;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.AssessmentPoint;
import com.ty.fuping.service.AssessmentObjectService;
import com.ty.fuping.service.AssessmentPlanService;
import com.ty.fuping.service.AssessmentPointService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/3.
 */
@RestController
public class AssessmentPointController {

    @Autowired
    private AssessmentPointService assessmentPointService;
    @Autowired
    private AssessmentObjectService assessmentObjectService;
    @Autowired
    private AssessmentPlanService assessmentPlanService;
    @Value("${data.assessmentStandardPoint}")
    private Integer assessmentStandardPoint;

    @RequestMapping("/getassessmentpointlist")
    //通过地址获取考核得分列表
    public JSONArray getPointList(@RequestParam("townId") Integer townId) {
        System.out.println(assessmentStandardPoint);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        List<AssessmentObject> assessmentObjectList = assessmentObjectService.getAssessmentObjectByTownId(townId);
        List<AssessmentPoint> assessmentPointList = new ArrayList<AssessmentPoint>();
        Iterator it = assessmentObjectList.iterator();
        AssessmentObject assessmentObject;
        int times;
        while (it.hasNext()) {
            assessmentObject = (AssessmentObject) it.next();
            times = assessmentPlanService.getPlanByObject(assessmentObject).size();
            if (times == 0)
                continue;
            AssessmentPoint assessmentPoint = assessmentPointService.getAssessmentPointByObject(assessmentObject);
            if (assessmentPoint == null) {
                assessmentPoint = new AssessmentPoint();
            }
            jsonObject = new JSONObject();
            jsonObject.put("assessmentPointId", assessmentPoint.getAssessmentPointId());
            jsonObject.put("assessmentObjectId", assessmentObject.getAssessmentObjectId());
            jsonObject.put("name", assessmentObject.getName());
            jsonObject.put("objectaddress", assessmentObject.getTown().getDistrict().getDistrictName() + "-" + assessmentObject.getTown().getTownName());
            jsonObject.put("point", assessmentPoint.getPoint());
            jsonObject.put("times", times);
            if (assessmentPoint.getPoint() >= assessmentStandardPoint) {
                jsonObject.put("color", "");
                jsonObject.put("Standard", "达标");
            } else {
                jsonObject.put("color", "#F75700");
                jsonObject.put("Standard", "未达标");
            }

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
