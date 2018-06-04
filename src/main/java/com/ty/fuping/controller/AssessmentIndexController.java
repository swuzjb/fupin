package com.ty.fuping.controller;

import com.ty.fuping.entity.AssessmentIndex;
import com.ty.fuping.entity.AssessmentPlan;
import com.ty.fuping.entity.AssessmentPoint;
import com.ty.fuping.entity.Point;
import com.ty.fuping.service.AssessmentIndexService;
import com.ty.fuping.service.AssessmentPlanService;
import com.ty.fuping.service.AssessmentPointService;
import com.ty.fuping.service.PointService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/3.
 */
@RestController
public class AssessmentIndexController {

    @Autowired
    private AssessmentIndexService assessmentIndexService;
    @Autowired
    private PointService pointService;
    @Autowired
    private AssessmentPointService assessmentPointService;
    @Autowired
    private AssessmentPlanService assessmentPlanService;

    @RequestMapping("/getassessmentindexbyplan")
    //获取某考核计划的考核指标
    public JSONArray getAssessmentIndexbyPlan(@RequestParam("assessmentPlanId") Integer assessmentPlanId) {
        AssessmentPlan assessmentPlan = new AssessmentPlan();
        assessmentPlan.setAssessmentPlanId(assessmentPlanId);
        List<AssessmentIndex> assessmentIndexList = assessmentIndexService.getAssessmentIndexbyPlan(assessmentPlan);
        JSONArray jsonArray = new JSONArray();
        Iterator<AssessmentIndex> iterator = assessmentIndexList.iterator();
        AssessmentIndex assessmentIndex;
        JSONObject jsonObject;
        while (iterator.hasNext()) {
            assessmentIndex = iterator.next();
            jsonObject = new JSONObject();
            jsonObject.put("assessmentIndexId", assessmentIndex.getAssessmentIndexId());
            jsonObject.put("assessmentPlanId", assessmentPlan.getAssessmentPlanId());
            jsonObject.put("assessmentPlanTitle", assessmentIndex.getAssessmentPlan().getTitle());
            jsonObject.put("indexCount", assessmentIndex.getIndexCount());
            jsonObject.put("assessmentIndexItem", assessmentIndex.getAssessmentIndexItem());
            jsonObject.put("countStandard", assessmentIndex.getCountStandard());
            jsonObject.put("remark", assessmentIndex.getRemark());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @RequestMapping("/addassessmentindex")
    //新增考核指标
    public AssessmentIndex addAssessmentIndex(@RequestParam("assessmentPlanId") Integer assessmentPlanId, AssessmentIndex assessmentIndex) {
        AssessmentPlan assessmentPlan = new AssessmentPlan();
        assessmentPlan.setAssessmentPlanId(assessmentPlanId);
        return assessmentIndexService.addAssessmentIndex(assessmentPlan, assessmentIndex);
    }

    @RequestMapping("/updateassessmentindex")
    //修改考核指标
    public AssessmentIndex updateAssessmentIndex(@RequestParam("assessmentPlanId") Integer assessmentPlanId, AssessmentIndex assessmentIndex) {
        AssessmentPlan assessmentPlan = assessmentPlanService.getAssessmentPlanbyId(assessmentPlanId);
        return assessmentIndexService.addAssessmentIndex(assessmentPlan, assessmentIndex);
    }

    @RequestMapping("/getassessmentindexbyid")
    //通过Id获取考核指标
    public JSONObject getAssessmentIndexbyId(@RequestParam("assessmentIndexId") Integer assessmentIndexId) {
        AssessmentIndex assessmentIndex = assessmentIndexService.getAssessmentIndexbyId(assessmentIndexId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("assessmentIndexId", assessmentIndex.getAssessmentIndexId());
        jsonObject.put("assessmentPlanId", assessmentIndex.getAssessmentPlan().getAssessmentPlanId());
        jsonObject.put("assessmentPlanTitle", assessmentIndex.getAssessmentPlan().getTitle());
        jsonObject.put("indexCount", assessmentIndex.getIndexCount());
        jsonObject.put("assessmentIndexItem", assessmentIndex.getAssessmentIndexItem());
        jsonObject.put("countStandard", assessmentIndex.getCountStandard());
        jsonObject.put("remark", assessmentIndex.getRemark());
        return jsonObject;
    }

    @Transactional
    @RequestMapping("/deleteassessmentindex")
    //删除考核指标
    public void deleteAssessmentIndex(@RequestParam("assessmentIndexId") Integer assessmentIndexId) {
        AssessmentIndex assessmentIndex = new AssessmentIndex();
        assessmentIndex.setAssessmentIndexId(assessmentIndexId);

        assessmentIndexService.deleteAssessmentIndex(assessmentIndex);
    }
}
