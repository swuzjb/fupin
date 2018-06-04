package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by ty on 2017/11/1.
 * 考核指标
 */
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class AssessmentIndex {

    @Id
    @GeneratedValue
    //考核指标Id
    private Integer assessmentIndexId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "assessmentPlan_id")
    //所属考核计划
    private AssessmentPlan assessmentPlan;

    //考核指标总分值
    private Double indexCount;

    //考核指标项
    private String assessmentIndexItem;

    //计分标准
    private String countStandard;

    //备注
    private String remark;

    public Integer getAssessmentIndexId() {
        return assessmentIndexId;
    }

    public void setAssessmentIndexId(Integer assessmentIndexId) {
        this.assessmentIndexId = assessmentIndexId;
    }

    public AssessmentPlan getAssessmentPlan() {
        return assessmentPlan;
    }

    public void setAssessmentPlan(AssessmentPlan assessmentPlan) {
        this.assessmentPlan = assessmentPlan;
    }

    public Double getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(Double indexCount) {
        this.indexCount = indexCount;
    }

    public String getAssessmentIndexItem() {
        return assessmentIndexItem;
    }

    public void setAssessmentIndexItem(String assessmentIndexItem) {
        this.assessmentIndexItem = assessmentIndexItem;
    }

    public String getCountStandard() {
        return countStandard;
    }

    public void setCountStandard(String countStandard) {
        this.countStandard = countStandard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
