package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by ty on 2017/11/1.
 * 打分
 */
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Point {

    @Id
    @GeneratedValue
    private Integer pointId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "assessmentObject_id")
    //考核对象
    private AssessmentObject assessmentObject;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "assessmentIndex_id")
    //所属考核指标
    private AssessmentIndex assessmentIndex;

    //打分
    private Double point;

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public AssessmentObject getAssessmentObject() {
        return assessmentObject;
    }

    public void setAssessmentObject(AssessmentObject assessmentObject) {
        this.assessmentObject = assessmentObject;
    }

    public AssessmentIndex getAssessmentIndex() {
        return assessmentIndex;
    }

    public void setAssessmentIndex(AssessmentIndex assessmentIndex) {
        this.assessmentIndex = assessmentIndex;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }
}
