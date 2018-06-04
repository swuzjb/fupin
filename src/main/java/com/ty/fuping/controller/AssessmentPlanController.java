package com.ty.fuping.controller;

import com.ty.fuping.entity.*;
import com.ty.fuping.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/2.
 */
@RestController
public class AssessmentPlanController {

    @Autowired
    private AssessmentPlanService assessmentPlanService;
    @Autowired
    private AssessmenterService assessmenterService;
    @Autowired
    private AssessmentObjectService assessmentObjectService;
    @Autowired
    private AssessmentIndexService assessmentIndexService;
    @Autowired
    private TownService townService;


    @RequestMapping("/getplanbyobject")
    //通过考核对象获取考核计划
    public List<AssessmentPlan> getPlanByObject(@RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        AssessmentObject assessmentObject = new AssessmentObject();
        assessmentObject.setAssessmentObjectId(assessmentObjectId);
        List<AssessmentPlan> assessmentPlanList = assessmentPlanService.getPlanByObject(assessmentObject);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONArray assessmenters, assessmentersId;
        List<Assessmenter> assessmenterList;
        Iterator<AssessmentPlan> it = assessmentPlanList.iterator();
        Iterator tempIt;
        AssessmentPlan assessmentPlan;
        while (it.hasNext()) {
            assessmentPlan = it.next();
            jsonObject = new JSONObject();
            jsonObject.put("assessmentPlanId", assessmentPlan.getAssessmentPlanId());
            jsonObject.put("title", assessmentPlan.getTitle());
            jsonObject.put("totalPoint", assessmentPlan.getTotalPoint());
            jsonObject.put("isOn", assessmentPlan.isOn());
            jsonObject.put("strIsOn", assessmentPlan.getStrIsOn());
            jsonObject.put("start", assessmentPlan.getStart());
            jsonObject.put("end", assessmentPlan.getEnd());
            jsonObject.put("content", assessmentPlan.getContent());
            jsonObject.put("numOfAssessmentIndex", assessmentIndexService.getAssessmentIndexbyPlan(assessmentPlan).size());

            //添加考核者的姓名和ID
            assessmenterList = assessmenterService.getAssessmenterByPlanId(assessmentPlan.getAssessmentPlanId());
            assessmenters = new JSONArray();
            assessmentersId = new JSONArray();
            tempIt = assessmenterList.iterator();
            while (tempIt.hasNext()) {
                Assessmenter assessmenter = (Assessmenter) tempIt.next();
                assessmenters.add(assessmenter.getName());
                assessmentersId.add(assessmenter.getAssessmenterId());
            }
            jsonObject.put("assessmenters", assessmenters);
            jsonObject.put("assessmentersId", assessmentersId);

            jsonArray.add(jsonObject);
        }
        return jsonArray;

    }

    @RequestMapping("/getplanbyid")
    //通过id过的考核计划详情
    public JSONObject getPlanById(@RequestParam("assessmentPlanId") Integer assessmentPlanId) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1;
        JSONArray jsonArray2;
        Iterator it;
        Assessmenter assessmenter;
        AssessmentObject assessmentObject;
        AssessmentPlan assessmentPlan = assessmentPlanService.getAssessmentPlanbyId(assessmentPlanId);
        List<Assessmenter> assessmenterList = assessmenterService.getAssessmenterByPlanId(assessmentPlanId);
        List<AssessmentObject> assessmentObjectList = assessmentObjectService.getAssessmenterByPlanId(assessmentPlanId);
        it = assessmenterList.iterator();
        jsonArray1 = new JSONArray();
        jsonArray2 = new JSONArray();
        while (it.hasNext()) {
            assessmenter = (Assessmenter) it.next();
            jsonArray1.add(assessmenter.getName());
            jsonArray2.add(assessmenter.getAssessmenterId());
        }
        jsonObject.put("assessmenters", jsonArray1);
        jsonObject.put("assessmentersId", jsonArray2);

        it = assessmentObjectList.iterator();
        jsonArray1 = new JSONArray();
        jsonArray2 = new JSONArray();
        while (it.hasNext()) {
            assessmentObject = (AssessmentObject) it.next();
            jsonArray1.add(assessmentObject.getName());
            jsonArray2.add(assessmentObject.getAssessmentObjectId());
        }
        jsonObject.put("assessmentObjects", jsonArray1);
        jsonObject.put("assessmentObjectsId", jsonArray2);

        jsonObject.put("assessmentPlanId", assessmentPlan.getAssessmentPlanId());
        jsonObject.put("title", assessmentPlan.getTitle());
        jsonObject.put("totalPoint", assessmentPlan.getTotalPoint());
        jsonObject.put("strIsOn", assessmentPlan.getStrIsOn());
        jsonObject.put("start", assessmentPlan.getStart());
        jsonObject.put("end", assessmentPlan.getEnd());
        jsonObject.put("content", assessmentPlan.getContent());

        return jsonObject;
    }

    @RequestMapping("/getplanbyaddress")
    //通过地址获取考核对象
    public JSONArray getPlanByAddress(@RequestParam("townId") Integer townId) {
        List<AssessmentPlan> assessmentPlans = assessmentPlanService.getPlanByAddress(townId);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONArray assessmenters, assessmentersId, assessmentObjects, assessmentObjectsId;
        List<AssessmentObject> assessmentObjectList;
        List<Assessmenter> assessmenterList;
        Iterator<AssessmentPlan> it = assessmentPlans.iterator();
        Iterator tempIt;
        AssessmentPlan assessmentPlan;
        while (it.hasNext()) {
            assessmentPlan = it.next();
            jsonObject = new JSONObject();
            jsonObject.put("assessmentPlanId", assessmentPlan.getAssessmentPlanId());
            jsonObject.put("title", assessmentPlan.getTitle());
            jsonObject.put("totalPoint", assessmentPlan.getTotalPoint());
            jsonObject.put("isOn", assessmentPlan.isOn());
            jsonObject.put("strIsOn", assessmentPlan.getStrIsOn());
            jsonObject.put("start", assessmentPlan.getStart());
            jsonObject.put("end", assessmentPlan.getEnd());
            jsonObject.put("content", assessmentPlan.getContent());

            //添加考核者的姓名和ID
            assessmenterList = assessmenterService.getAssessmenterByPlanId(assessmentPlan.getAssessmentPlanId());
            assessmenters = new JSONArray();
            assessmentersId = new JSONArray();
            tempIt = assessmenterList.iterator();
            while (tempIt.hasNext()) {
                Assessmenter assessmenter = (Assessmenter) tempIt.next();
                assessmenters.add(assessmenter.getName());
                assessmentersId.add(assessmenter.getAssessmenterId());
            }
            jsonObject.put("assessmenters", assessmenters);
            jsonObject.put("assessmentersId", assessmentersId);

            //添加考核对象姓名和ID
            assessmentObjectList = assessmentObjectService.getAssessmenterByPlanId(assessmentPlan.getAssessmentPlanId());
            assessmentObjects = new JSONArray();
            assessmentObjectsId = new JSONArray();
            tempIt = assessmentObjectList.iterator();
            while (tempIt.hasNext()) {
                AssessmentObject assessmentObject = (AssessmentObject) tempIt.next();
                assessmentObjects.add(assessmentObject.getName());
                assessmentObjectsId.add(assessmentObject.getAssessmentObjectId());
            }
            jsonObject.put("assessmentObjects", assessmentObjects);
            jsonObject.put("assessmentObjectsId", assessmentObjectsId);

            jsonArray.add(jsonObject);
        }
        return jsonArray;

    }

    @RequestMapping("/geteffectiveplanbyaddress")
    //通过地址获取考核对象(通过考核对象拆分 有效的 ison=true)
    public JSONArray getEffectivePlanByAddress(@RequestParam("townId") Integer townId) {
        List<AssessmentPlan> assessmentPlans = assessmentPlanService.getPlanByAddress(townId);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONArray assessmenters, assessmentersId, assessmentObjects, assessmentObjectsId;
        List<AssessmentObject> assessmentObjectList;
        List<Assessmenter> assessmenterList;
        Iterator<AssessmentPlan> it = assessmentPlans.iterator();
        Iterator tempIt;
        AssessmentPlan assessmentPlan;
        while (it.hasNext()) {
            assessmentPlan = it.next();
            if (assessmentPlan.isOn() == false)
                continue;
            jsonObject = new JSONObject();
            jsonObject.put("assessmentPlanId", assessmentPlan.getAssessmentPlanId());
            jsonObject.put("title", assessmentPlan.getTitle());
            jsonObject.put("totalPoint", assessmentPlan.getTotalPoint());
            jsonObject.put("isOn", assessmentPlan.isOn());
            jsonObject.put("strIsOn", assessmentPlan.getStrIsOn());
            jsonObject.put("start", assessmentPlan.getStart());
            jsonObject.put("end", assessmentPlan.getEnd());
            jsonObject.put("content", assessmentPlan.getContent());

            //添加考核者的姓名和ID
            assessmenterList = assessmenterService.getAssessmenterByPlanId(assessmentPlan.getAssessmentPlanId());
            assessmenters = new JSONArray();
            assessmentersId = new JSONArray();
            tempIt = assessmenterList.iterator();
            while (tempIt.hasNext()) {
                Assessmenter assessmenter = (Assessmenter) tempIt.next();
                assessmenters.add(assessmenter.getName());
                assessmentersId.add(assessmenter.getAssessmenterId());
            }
            jsonObject.put("assessmenters", assessmenters);
            jsonObject.put("assessmentersId", assessmentersId);

            //逐一添加考核对象姓名和ID
            assessmentObjectList = assessmentObjectService.getAssessmenterByPlanId(assessmentPlan.getAssessmentPlanId());
            assessmentObjects = new JSONArray();
            assessmentObjectsId = new JSONArray();
            tempIt = assessmentObjectList.iterator();
            while (tempIt.hasNext()) {
                AssessmentObject assessmentObject = (AssessmentObject) tempIt.next();

                jsonObject.put("assessmentObject", assessmentObject.getName());
                jsonObject.put("assessmentObjectId", assessmentObject.getAssessmentObjectId());
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;

    }

    @RequestMapping("/getplanall")
    //获取所有考核计划
    public List<AssessmentPlan> getPlanAll() {
        return assessmentPlanService.getAssessmentPlanAll();
    }

    @RequestMapping("/addplan")
    //新增考核计划
    public AssessmentPlan addAssessmentPlan(@RequestParam("townId") Integer townId, AssessmentPlan assessmentPlan, @RequestParam("objects") String assessmentObject, @RequestParam("assessmenters") String assessmenter) {
        Town town = new Town();
        town.setTownId(townId);
        assessmentPlan.setTown(town);
        if (assessmentPlan.isOn() == true)
            assessmentPlan.setStrIsOn("开启");
        else
            assessmentPlan.setStrIsOn("关闭");
        String[] objects = assessmentObject.split(",");
        String[] assessmenters = assessmenter.split(",");
        AssessmentObject assessmentObject1;
        Assessmenter assessmenter1;
        List<AssessmentObject> assessmentObjectList = new ArrayList<>();
        List<Assessmenter> assessmenterList = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            assessmentObject1 = new AssessmentObject();
            assessmentObject1.setAssessmentObjectId(Integer.parseInt(objects[i]));
            assessmentObjectList.add(assessmentObject1);
        }
        for (int i = 0; i < assessmenters.length; i++) {
            assessmenter1 = new Assessmenter();
            assessmenter1.setAssessmenterId(Integer.parseInt(assessmenters[i]));
            assessmenterList.add(assessmenter1);
        }
        return assessmentPlanService.addAssessmentPlan(assessmentPlan, assessmenterList, assessmentObjectList);
    }

    @RequestMapping("/updateplan")
    //修改考核计划
    public AssessmentPlan updateAssessmentPlan(@RequestParam("townId") Integer townId, AssessmentPlan assessmentPlan, @RequestParam("objects") String assessmentObject, @RequestParam("assessmenters") String assessmenter) {
        Town town = townService.getTownById(townId);
        assessmentPlan.setTown(town);

        if (assessmentPlan.isOn() == true)
            assessmentPlan.setStrIsOn("开启");
        else
            assessmentPlan.setStrIsOn("关闭");
        String[] objects = assessmentObject.split(",");
        String[] assessmenters = assessmenter.split(",");
        AssessmentObject assessmentObject1;
        Assessmenter assessmenter1;
        List<AssessmentObject> assessmentObjectList = new ArrayList<>();
        List<Assessmenter> assessmenterList = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            assessmentObjectList.add(assessmentObjectService.getAssessmentObjectById(Integer.parseInt(objects[i])));
        }
        for (int i = 0; i < assessmenters.length; i++) {
            assessmenterList.add(assessmenterService.getAssessmenterById(Integer.parseInt(assessmenters[i])));
        }
        return assessmentPlanService.updateAssessmentPlan(assessmentPlan, assessmenterList, assessmentObjectList);
    }

    @Transactional
    @RequestMapping("/deleteassessmentplan")
    //删除考核计划
    public void deleteAssessmentPlan(@RequestParam("assessmentPlanId") Integer assessmentPlanId) {
        AssessmentPlan assessmentPlan = new AssessmentPlan();
        assessmentPlan.setAssessmentPlanId(assessmentPlanId);

        List<AssessmentIndex> assessmentIndexList = assessmentIndexService.getAssessmentIndexbyPlanId(assessmentPlanId);
        Iterator<AssessmentIndex> it = assessmentIndexList.iterator();
        AssessmentIndex assessmentIndex;
        while (it.hasNext()) {
            assessmentIndex = it.next();
            assessmentIndexService.deleteAssessmentIndex(assessmentIndex);
        }
        assessmentPlanService.deleteAssessmentPlan(assessmentPlan);
    }
}
