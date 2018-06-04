package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/11/1.
 * 考核计划表
 */
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class AssessmentPlan {

    @Id
    @GeneratedValue
    //考核计划Id
    private Integer assessmentPlanId;

    //考核标题
    private String title;

    //考核总分值
    private Double totalPoint;

    //考核状态
    private boolean isOn;

    //考核状态str
    private String strIsOn;

    //考核开始时间
    private Date start;

    //考核结束时间
    private Date end;

    //考核内容
    private String content;

    //考核计划地址
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "townId")
    private Town town;

    public Integer getAssessmentPlanId() {
        return assessmentPlanId;
    }

    public void setAssessmentPlanId(Integer assessmentPlanId) {
        this.assessmentPlanId = assessmentPlanId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getStrIsOn() {
        return strIsOn;
    }

    public void setStrIsOn(String strIsOn) {
        this.strIsOn = strIsOn;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
