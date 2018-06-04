package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by ty on 2017/11/1.
 * 考核-考核者
 */
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class AssessmentandAssessmenter {

    @Id
    @GeneratedValue
    private Integer assessmentandAssessmenterId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "assessmentPlan_id")
    //考核计划
    private AssessmentPlan assessmentPlan;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "assessmenter_id")
    //考核者
    private Assessmenter assessmenter;

    public Integer getAssessmentandAssessmenterId() {
        return assessmentandAssessmenterId;
    }

    public void setAssessmentandAssessmenterId(Integer assessmentandAssessmenterId) {
        this.assessmentandAssessmenterId = assessmentandAssessmenterId;
    }

    public AssessmentPlan getAssessmentPlan() {
        return assessmentPlan;
    }

    public void setAssessmentPlan(AssessmentPlan assessmentPlan) {
        this.assessmentPlan = assessmentPlan;
    }

    public Assessmenter getAssessmenter() {
        return assessmenter;
    }

    public void setAssessmenter(Assessmenter assessmenter) {
        this.assessmenter = assessmenter;
    }
}
