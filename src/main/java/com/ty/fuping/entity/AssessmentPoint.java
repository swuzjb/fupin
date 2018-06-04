package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by ty on 2017/11/2.
 */
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class AssessmentPoint {
    @Id
    @GeneratedValue
    private Integer assessmentPointId;

    @ManyToOne()
    @JoinColumn(name = "assessmentObject_id")
    //考核对象
    private AssessmentObject assessmentObject;

    //得分
    private Double point;

    //考核次数
    private Integer times;

    public AssessmentPoint() {
        this.point=new Double(0);
        this.times=0;
    }

    public Integer getAssessmentPointId() {
        return assessmentPointId;
    }

    public void setAssessmentPointId(Integer assessmentPointId) {
        this.assessmentPointId = assessmentPointId;
    }

    public AssessmentObject getAssessmentObject() {
        return assessmentObject;
    }

    public void setAssessmentObject(AssessmentObject assessmentObject) {
        this.assessmentObject = assessmentObject;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
