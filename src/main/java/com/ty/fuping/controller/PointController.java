package com.ty.fuping.controller;

import com.ty.fuping.entity.*;
import com.ty.fuping.repository.PointRepository;
import com.ty.fuping.service.AssessmentIndexService;
import com.ty.fuping.service.PointService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/11/3.
 */
@RestController
public class PointController {

    @Autowired
    private PointService pointService;
    @Autowired
    private AssessmentIndexService assessmentIndexService;
    @Autowired
    private PointRepository pointRepository;

    @RequestMapping("/point")
    //评分
    public Point point(@RequestParam("objectId") Integer objectId, @RequestParam("assessmentIndexId") Integer assessmentIndexId, @RequestParam("point") Double duPoint) {
        Point point;
        //查询是否已经有这条打分记录了
        point = pointService.getPointByIndexAndObject(assessmentIndexId, objectId);
        //如果有了直接更改保存
        if (point != null) {
            Double oldPoint = point.getPoint();
            point.setPoint(duPoint);
            return pointService.point(point, oldPoint);
        }
        point = new Point();
        AssessmentObject assessmentObject = new AssessmentObject();
        AssessmentIndex assessmentIndex = assessmentIndexService.getAssessmentIndexbyId(assessmentIndexId);
        if (assessmentIndex == null)
            return null;
        assessmentObject.setAssessmentObjectId(objectId);
        point.setAssessmentIndex(assessmentIndex);
        point.setAssessmentObject(assessmentObject);
        point.setPoint(duPoint);
        return pointService.point(point, 0d);
    }

    @RequestMapping("/getassessmentindexscorebyplan")
    //通过考核计划Id获取打分详细
    public JSONArray getPointByTownId(@RequestParam("assessmentPlanId") Integer assessmentPlanId, @RequestParam("assessmentObjectId") Integer assessmentObjectId) {
        List<AssessmentIndex> assessmentIndexList = assessmentIndexService.getAssessmentIndexbyPlanId(assessmentPlanId);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        AssessmentIndex assessmentIndex;
        Point point;
        Iterator it = assessmentIndexList.iterator();
        while (it.hasNext()) {
            assessmentIndex = (AssessmentIndex) it.next();
            point = pointService.getPointByIndexAndObject(assessmentIndex.getAssessmentIndexId(), assessmentObjectId);
            if (point == null) {
                point = new Point();
                point.setPoint(new Double(0));
            }
            jsonObject = new JSONObject();
            jsonObject.put("point", point.getPoint());
            jsonObject.put("assessmentIndexId", assessmentIndex.getAssessmentIndexId());
            jsonObject.put("assessmentPlanId", assessmentIndex.getAssessmentPlan().getAssessmentPlanId());
            jsonObject.put("assessmentPlanTitle", assessmentIndex.getAssessmentPlan().getTitle());
            jsonObject.put("indexCount", assessmentIndex.getIndexCount());
            jsonObject.put("assessmentIndexItem", assessmentIndex.getAssessmentIndexItem());
            jsonObject.put("countStandard", assessmentIndex.getCountStandard());
            jsonObject.put("remark", assessmentIndex.getRemark());

            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
