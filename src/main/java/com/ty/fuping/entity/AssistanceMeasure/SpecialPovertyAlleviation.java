package com.ty.fuping.entity.AssistanceMeasure;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Family;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/22.
 * 专项扶贫
 */
@Entity
public class SpecialPovertyAlleviation {
    @Id
    @GeneratedValue
    private Integer specialPovertyAlleviationId;
    //所属贫困户
    @ManyToOne(cascade = {CascadeType.REFRESH,}, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //上报季度
    private String quarter;
    //分类
    private String classify;
    //规模(亩)
    private Double scale;
    //补助标准(元/亩)
    private Double standardSubsidies;
    //补助资金(元)
    private Double subsidyFunds;
    //贷款金额(元)
    private Double loadAmount;
    //贷款利息(元)
    private Double loanInterest;
    //贷款应贴息金额(元)
    private Double discountAmount;
    //互助资金金额(元)
    private Double mutualFunds;
    //互助资金占用费(元)
    private Double mutualFundsOccupied;
    //帮扶责任人
    @ManyToOne(cascade = { CascadeType.REFRESH,}, optional = false)
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //填表时间
    private Date fillInTime;

    public Integer getSpecialPovertyAlleviationId() {
        return specialPovertyAlleviationId;
    }

    public void setSpecialPovertyAlleviationId(Integer specialPovertyAlleviationId) {
        this.specialPovertyAlleviationId = specialPovertyAlleviationId;
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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Double getStandardSubsidies() {
        return standardSubsidies;
    }

    public void setStandardSubsidies(Double standardSubsidies) {
        this.standardSubsidies = standardSubsidies;
    }

    public Double getSubsidyFunds() {
        return subsidyFunds;
    }

    public void setSubsidyFunds(Double subsidyFunds) {
        this.subsidyFunds = subsidyFunds;
    }

    public Double getLoadAmount() {
        return loadAmount;
    }

    public void setLoadAmount(Double loadAmount) {
        this.loadAmount = loadAmount;
    }

    public Double getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(Double loanInterest) {
        this.loanInterest = loanInterest;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getMutualFunds() {
        return mutualFunds;
    }

    public void setMutualFunds(Double mutualFunds) {
        this.mutualFunds = mutualFunds;
    }

    public Double getMutualFundsOccupied() {
        return mutualFundsOccupied;
    }

    public void setMutualFundsOccupied(Double mutualFundsOccupied) {
        this.mutualFundsOccupied = mutualFundsOccupied;
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
