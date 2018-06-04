package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by ty on 2017/11/1.
 * 考核-考核对象
 */
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class AssessmentandObject {

    @Id
    @GeneratedValue
    private Integer assessmentandObjectId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "assessmentPlan_id")
    //考核计划
    private AssessmentPlan assessmentPlan;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "assessmentObject_id")
    //考核对象
    private AssessmentObject assessmentObject;

    public Integer getAssessmentandObjectId() {
        return assessmentandObjectId;
    }

    public void setAssessmentandObjectId(Integer assessmentandObjectId) {
        this.assessmentandObjectId = assessmentandObjectId;
    }

    public AssessmentPlan getAssessmentPlan() {
        return assessmentPlan;
    }

    public void setAssessmentPlan(AssessmentPlan assessmentPlan) {
        this.assessmentPlan = assessmentPlan;
    }

    public AssessmentObject getAssessmentObject() {
        return assessmentObject;
    }

    public void setAssessmentObject(AssessmentObject assessmentObject) {
        this.assessmentObject = assessmentObject;
    }
}
