package com.ty.fuping.entity.AssistanceMeasure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Family;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/22.
 * 饮水安全
 */
@Entity
public class DrinkingSafety {
    @Id
    @GeneratedValue
    private Integer drinkingSafetyId;
    //所属贫困户
    @JsonIgnore
    @ManyToOne(cascade = { CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //上报季度
    private String quarter;
    /**
     * 扶贫现状
     */
    //供水方式
    private String waterSupplyMode;
    //是否自来水入户
    private Boolean tapWaterUser;
    //饮水是否安全
    private Boolean safeDrinkingWater;
    //解决年份
    private String solveTime;
    //扶贫措施
    private String assistanceWay;
    //计划解决年份
    private String planSolveTime;
    /**
     * 扶贫成效
     */
    //供水方式
    private String waterSupplyModeNow;
    //是否自来水入户
    private Boolean tapWaterUserNow;
    //是否稳定解决饮水安全
    private Boolean safeDrinkingWaterNow;
    //备注
    private String remark;
    //帮扶责任人
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //填表时间
    private Date fillInTime;

    public Integer getDrinkingSafetyId() {
        return drinkingSafetyId;
    }

    public void setDrinkingSafetyId(Integer drinkingSafetyId) {
        this.drinkingSafetyId = drinkingSafetyId;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getWaterSupplyMode() {
        return waterSupplyMode;
    }

    public void setWaterSupplyMode(String waterSupplyMode) {
        this.waterSupplyMode = waterSupplyMode;
    }

    public Boolean getTapWaterUser() {
        return tapWaterUser;
    }

    public void setTapWaterUser(Boolean tapWaterUser) {
        this.tapWaterUser = tapWaterUser;
    }

    public Boolean getSafeDrinkingWater() {
        return safeDrinkingWater;
    }

    public void setSafeDrinkingWater(Boolean safeDrinkingWater) {
        this.safeDrinkingWater = safeDrinkingWater;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(String solveTime) {
        this.solveTime = solveTime;
    }

    public String getAssistanceWay() {
        return assistanceWay;
    }

    public void setAssistanceWay(String assistanceWay) {
        this.assistanceWay = assistanceWay;
    }

    public String getPlanSolveTime() {
        return planSolveTime;
    }

    public void setPlanSolveTime(String planSolveTime) {
        this.planSolveTime = planSolveTime;
    }

    public String getWaterSupplyModeNow() {
        return waterSupplyModeNow;
    }

    public void setWaterSupplyModeNow(String waterSupplyModeNow) {
        this.waterSupplyModeNow = waterSupplyModeNow;
    }

    public Boolean getTapWaterUserNow() {
        return tapWaterUserNow;
    }

    public void setTapWaterUserNow(Boolean tapWaterUserNow) {
        this.tapWaterUserNow = tapWaterUserNow;
    }

    public Boolean getSafeDrinkingWaterNow() {
        return safeDrinkingWaterNow;
    }

    public void setSafeDrinkingWaterNow(Boolean safeDrinkingWaterNow) {
        this.safeDrinkingWaterNow = safeDrinkingWaterNow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AssessmentObject getAssessmentObject() {
        return assessmentObject;
    }

    public void setAssessmentObject(AssessmentObject assessmentObject) {
        this.assessmentObject = assessmentObject;
    }

    public Date getFillInTime() {
        return fillInTime;
    }

    public void setFillInTime(Date fillInTime) {
        this.fillInTime = fillInTime;
    }
}
