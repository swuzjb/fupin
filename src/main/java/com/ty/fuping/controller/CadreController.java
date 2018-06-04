package com.ty.fuping.controller;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Assessmenter;
import com.ty.fuping.entity.Town;
import com.ty.fuping.result.Cadre;
import com.ty.fuping.service.AssessmentObjectService;
import com.ty.fuping.service.AssessmenterService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/12/5.
 * 干部管理
 */
@RestController
public class CadreController {
    @Autowired
    private AssessmentObjectService assessmentObjectService;
    @Autowired
    private AssessmenterService assessmenterService;
    @Autowired
    private AssessmenterController assessmenterController;
    @Autowired
    private AssessmentObjectController assessmentObjectController;

    //多条件查询考核对象
    @RequestMapping("/searchassessmentobject")
    public List<AssessmentObject> searchAssessmentObject(AssessmentObject assessmentObject, @RequestParam(value = "townId", required = false) Integer townId) {
        if (townId != null) {
            Town town = new Town();
            town.setTownId(townId);
            assessmentObject.setTown(town);
        }
        return assessmentObjectService.searchAssessmentObjects(assessmentObject);
    }

    //多条件查询考核者
    @RequestMapping("/searchassessmenter")
    public List<Assessmenter> searchAssessmentObject(Assessmenter assessmenter, @RequestParam(value = "townId", required = false) Integer townId) {
        if (townId != null) {
            Town town = new Town();
            town.setTownId(townId);
            assessmenter.setTown(town);
        }
        return assessmenterService.searchAssessmenters(assessmenter);
    }

    //多条件查询驻村干部
    @RequestMapping("/searchcadres")
    public JSONArray searchCadre(@RequestParam(value = "type", required = false) Integer type,
                                 Cadre cadre,
                                 @RequestParam(value = "townId", required = false) Integer townId) {
        Iterator iterator;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;

        if (type == null||type == 0 ) {
            AssessmentObject assessmentObject = cadre.parseAssessmentObject();
            if (townId != null) {
                Town town = new Town();
                town.setTownId(townId);
                assessmentObject.setTown(town);
            }
            List<AssessmentObject> assessmentObjectList = assessmentObjectService.searchAssessmentObjects(assessmentObject);
            iterator = assessmentObjectList.iterator();
            while (iterator.hasNext()) {
                jsonObject = new JSONObject();
                assessmentObject = (AssessmentObject) iterator.next();
                jsonObject.put("cadreId", assessmentObject.getAssessmentObjectId());
                jsonObject.put("name", assessmentObject.getName());
                jsonObject.put("townId", assessmentObject.getTown().getTownId());
                jsonObject.put("townName", assessmentObject.getTown().getTownName());
                jsonObject.put("sex", assessmentObject.getSex());
                jsonObject.put("zhegnzhimianmao", assessmentObject.getZhegnzhimianmao());
                jsonObject.put("phoneNumber", assessmentObject.getPhoneNumber());
                jsonObject.put("account", assessmentObject.getAccount());
                jsonObject.put("password", assessmentObject.getPassword());
                jsonObject.put("danwei", assessmentObject.getDanwei());
                jsonObject.put("relationofdanwei", assessmentObject.getRelationofdanwei());
                jsonObject.put("danweiaddress", assessmentObject.getDanweiaddress());
                jsonObject.put("zhiwu", assessmentObject.getZhiwu());
                jsonObject.put("type", 0);

                jsonArray.add(jsonObject);
            }
        }

        if (type == null||type == 1 ) {
            Assessmenter assessmenter = cadre.parseAssessmenter();
            if (townId != null) {
                Town town = new Town();
                town.setTownId(townId);
                assessmenter.setTown(town);
            }
            List<Assessmenter> assessmenterList = assessmenterService.searchAssessmenters(assessmenter);
            iterator = assessmenterList.iterator();
            while (iterator.hasNext()) {
                jsonObject = new JSONObject();
                assessmenter = (Assessmenter) iterator.next();
                jsonObject.put("cadreId", assessmenter.getAssessmenterId());
                jsonObject.put("name", assessmenter.getName());
                jsonObject.put("townId", assessmenter.getTown().getTownId());
                jsonObject.put("townName", assessmenter.getTown().getTownName());
                jsonObject.put("sex", assessmenter.getSex());
                jsonObject.put("zhegnzhimianmao", assessmenter.getZhegnzhimianmao());
                jsonObject.put("phoneNumber", assessmenter.getPhoneNumber());
                jsonObject.put("account", assessmenter.getAccount());
                jsonObject.put("password", assessmenter.getPassword());
                jsonObject.put("danwei", assessmenter.getDanwei());
                jsonObject.put("relationofdanwei", assessmenter.getRelationofdanwei());
                jsonObject.put("danweiaddress", assessmenter.getDanweiaddress());
                jsonObject.put("zhiwu", assessmenter.getZhiwu());
                jsonObject.put("type", 1);

                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }

    //添加驻村干部
    @RequestMapping("/addcadre")
    public JSONObject addCadre(@RequestParam(value = "type", required = true) Integer type,
                         Cadre cadre,
                         @RequestParam(value = "townId", required = true) Integer townId) {
        JSONObject jsonObject=new JSONObject();

        if(type==0){
            jsonObject.put("type",0);
            jsonObject.put("id",assessmentObjectController.addAssessmentObject(cadre.parseAssessmentObject(),townId).getAssessmentObjectId()) ;
        }else if(type==1){
            jsonObject.put("type",1);
            jsonObject.put("id",assessmenterController.addAssessmenter(cadre.parseAssessmenter(),townId).getAssessmenterId());
        }
        return jsonObject;
    }

    //通过Id获取干部详情
    @RequestMapping("getcadrebyid")
    public JSONObject getCadreById(@RequestParam("type") Integer type,@RequestParam("id") Integer id){
        JSONObject jsonObject=new JSONObject();

        if(type==0){
            AssessmentObject assessmentObject=assessmentObjectService.getAssessmentObjectById(id);
            jsonObject.put("cadreId", assessmentObject.getAssessmentObjectId());
            jsonObject.put("name", assessmentObject.getName());
            jsonObject.put("townId", assessmentObject.getTown().getTownId());
            jsonObject.put("townName", assessmentObject.getTown().getTownName());
            jsonObject.put("sex", assessmentObject.getSex());
            jsonObject.put("zhegnzhimianmao", assessmentObject.getZhegnzhimianmao());
            jsonObject.put("phoneNumber", assessmentObject.getPhoneNumber());
            jsonObject.put("account", assessmentObject.getAccount());
            jsonObject.put("password", assessmentObject.getPassword());
            jsonObject.put("danwei", assessmentObject.getDanwei());
            jsonObject.put("relationofdanwei", assessmentObject.getRelationofdanwei());
            jsonObject.put("danweiaddress", assessmentObject.getDanweiaddress());
            jsonObject.put("zhiwu", assessmentObject.getZhiwu());
            jsonObject.put("type", 0);
        }else if(type==1){
            Assessmenter assessmenter=assessmenterService.getAssessmenterById(id);
            jsonObject.put("cadreId", assessmenter.getAssessmenterId());
            jsonObject.put("name", assessmenter.getName());
            jsonObject.put("townId", assessmenter.getTown().getTownId());
            jsonObject.put("townName", assessmenter.getTown().getTownName());
            jsonObject.put("sex", assessmenter.getSex());
            jsonObject.put("zhegnzhimianmao", assessmenter.getZhegnzhimianmao());
            jsonObject.put("phoneNumber", assessmenter.getPhoneNumber());
            jsonObject.put("account", assessmenter.getAccount());
            jsonObject.put("password", assessmenter.getPassword());
            jsonObject.put("danwei", assessmenter.getDanwei());
            jsonObject.put("relationofdanwei", assessmenter.getRelationofdanwei());
            jsonObject.put("danweiaddress", assessmenter.getDanweiaddress());
            jsonObject.put("zhiwu", assessmenter.getZhiwu());
            jsonObject.put("type", 1);
        }
        return jsonObject;
    }

    //通过Id和type删除干部
    @RequestMapping("deletecadrebyid")
    public void deleteCadreById(@RequestParam("type") Integer type,@RequestParam("id") Integer id){
        if(type==0){
            assessmentObjectService.deleteAssessmentObjectById(id);
        }else if(type==1){
            assessmenterService.deleteAssessmenterById(id);
        }
    }

}
