package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/15.
 * 帮扶责任人记录
 */
@Entity
public class Assistance {
    @Id
    @GeneratedValue
    private Integer assistanceId;
    //家庭
    //@JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "familyId")
    private Family family;
    //帮扶责任人
    //@JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //帮扶开始时间
    private Date assistanceStartTime;
    //帮扶结束时间
    private Date assistanceEndTime;

    public Integer getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Integer assistanceId) {
        this.assistanceId = assistanceId;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public AssessmentObject getAssessmentObject() {
        return assessmentObject;
    }

    public void setAssessmentObject(AssessmentObject assessmentObject) {
        this.assessmentObject = assessmentObject;
    }

    public Date getAssistanceStartTime() {
        return assistanceStartTime;
    }

    public void setAssistanceStartTime(Date assistanceStartTime) {
        this.assistanceStartTime = assistanceStartTime;
    }

    public Date getAssistanceEndTime() {
        return assistanceEndTime;
    }

    public void setAssistanceEndTime(Date assistanceEndTime) {
        this.assistanceEndTime = assistanceEndTime;
    }
}
