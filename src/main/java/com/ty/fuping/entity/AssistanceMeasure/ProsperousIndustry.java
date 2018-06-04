package com.ty.fuping.entity.AssistanceMeasure;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Family;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/20.
 * 富民产业
 */
@Entity
public class ProsperousIndustry {
    @Id
    @GeneratedValue
    private Integer prosperousIndustryId;
    //所属贫困户
    @ManyToOne(cascade = {CascadeType.REFRESH, }, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //上报季度
    private String quarter;
    /**
     * 种植业
     */
    //水稻面积(亩)
    private Double riceArea;
    //红薯面积(亩)
    private Double sweetPotatoArea;
    //中药材面积(亩)
    private Double chineseMedicinalArea;
    //蔬菜、瓜类面积(亩)
    private Double vegetableArea;
    //其中设施蔬菜面积(大棚、日光温室)(亩)
    private Double greenhouseArea;
    //其它种植面积(亩)
    private Double otherArea;
    //种植业收入(元)
    private Double plantIncome;
    /**
     * 畜牧业
     */
    //牛饲养(头)
    private Integer cow;
    //年内牛出栏(头)
    private Integer cowMarket;
    //羊饲养(只)
    private Integer sheep;
    //年内羊出栏(只)
    private Integer sheepMarket;
    //猪饲养(头)
    private Integer pig;
    //年内猪出栏(头)
    private Integer pigMarket;
    //家禽饲养(只)
    private Integer poultry;
    //年内家禽出栏(只)
    private Integer poultryMarket;
    //渔养殖(亩)
    private Double fish;
    //年内渔养殖出栏(亩)
    private Double fishMarket;
    //设施圈舍(平方米)
    private Double facility;
    //畜牧业收入(元)
    private Double farmingIncome;
    /**
     * 林果业
     */
    //林果产业类型
    private String fruitForestIndustryStructure;
    //林果面积(亩)
    private Double fruitForestArea;
    //苗木类型
    private String nurseryForestType;
    //苗木面积(亩)
    private Double nurseryForestArea;
    //其它林果面积(亩)
    private Double otherForestArea;
    //林果业收入(元)
    private Double fruitForestIncome;
    /**
     * 其他富民产业
     */
    //其它富民产业收入(元)
    private Double otherIndustryIncome;
    /**
     * 土地流转
     */
    //土地流转面积(亩)
    private Double landTransferArea;
    //土地流转收入(元)
    private Double landTransferIncome;
    /**
     * 龙头企业、合作社、种养大户等带动农户收入
     */
    //龙头企业带动收入(元)
    private Double leadingEnterpriseDriveIncome;
    //合作社带动收入(元)
    private Double cooperationDriveIncome;
    //种养大户带动收入(元)
    private Double influentialFamilyDriveIncome;;
    //是否加入合作社
    private Boolean joinCooperation;
    //是否加入农业保险
    private Boolean joinAgriculturalInsurance;
    /**
     * 劳务经济
     */
    //务工人数(人)
    private Integer workersNumber;
    //务工收入(元)
    private Double workIncome;
    /**
     * 农村金融
     */
    //贷款类型
    private String loanType;
    //承贷金融机构
    private String loanInstitution;
    //贷款帐号
    private String loanAccount;
    //贷款总额(元)
    private Double loanAmount;
    //贷款发放时间
    private Date lendingTime;
    //贷款期限(月)
    private Double loanPeriod;
    //贷款还款时间
    private Date repaymentDate;
    //执行年利率(%)
    private Double annualInterest;
    //贷款利息(占用费)(元)
    private Double loanInterest;
    //应贴息金额(元)
    private Double discountAmount;
    //贷款用途
    private String loanPurpose;
    /**
     * 扶贫成效
     */
    //富民产业总收入(元)
    private Double totalIndustryIncome;
    /**
     * 备注
     */
    private String remark;
    //帮扶责任人
    @ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //填表时间
    private Date fillInTime;

    public Integer getProsperousIndustryId() {
        return prosperousIndustryId;
    }

    public void setProsperousIndustryId(Integer prosperousIndustryId) {
        this.prosperousIndustryId = prosperousIndustryId;
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

    public Double getRiceArea() {
        return riceArea;
    }

    public void setRiceArea(Double riceArea) {
        this.riceArea = riceArea;
    }

    public Double getSweetPotatoArea() {
        return sweetPotatoArea;
    }

    public void setSweetPotatoArea(Double sweetPotatoArea) {
        this.sweetPotatoArea = sweetPotatoArea;
    }

    public Double getChineseMedicinalArea() {
        return chineseMedicinalArea;
    }

    public void setChineseMedicinalArea(Double chineseMedicinalArea) {
        this.chineseMedicinalArea = chineseMedicinalArea;
    }

    public Double getVegetableArea() {
        return vegetableArea;
    }

    public void setVegetableArea(Double vegetableArea) {
        this.vegetableArea = vegetableArea;
    }

    public Double getGreenhouseArea() {
        return greenhouseArea;
    }

    public void setGreenhouseArea(Double greenhouseArea) {
        this.greenhouseArea = greenhouseArea;
    }

    public Double getOtherArea() {
        return otherArea;
    }

    public void setOtherArea(Double otherArea) {
        this.otherArea = otherArea;
    }

    public Double getPlantIncome() {
        return plantIncome;
    }

    public void setPlantIncome(Double plantIncome) {
        this.plantIncome = plantIncome;
    }

    public Integer getCow() {
        return cow;
    }

    public void setCow(Integer cow) {
        this.cow = cow;
    }

    public Integer getCowMarket() {
        return cowMarket;
    }

    public void setCowMarket(Integer cowMarket) {
        this.cowMarket = cowMarket;
    }

    public Integer getSheep() {
        return sheep;
    }

    public void setSheep(Integer sheep) {
        this.sheep = sheep;
    }

    public Integer getSheepMarket() {
        return sheepMarket;
    }

    public void setSheepMarket(Integer sheepMarket) {
        this.sheepMarket = sheepMarket;
    }

    public Integer getPig() {
        return pig;
    }

    public void setPig(Integer pig) {
        this.pig = pig;
    }

    public Integer getPigMarket() {
        return pigMarket;
    }

    public void setPigMarket(Integer pigMarket) {
        this.pigMarket = pigMarket;
    }

    public Integer getPoultry() {
        return poultry;
    }

    public void setPoultry(Integer poultry) {
        this.poultry = poultry;
    }

    public Integer getPoultryMarket() {
        return poultryMarket;
    }

    public void setPoultryMarket(Integer poultryMarket) {
        this.poultryMarket = poultryMarket;
    }

    public Double getFish() {
        return fish;
    }

    public void setFish(Double fish) {
        this.fish = fish;
    }

    public Double getFishMarket() {
        return fishMarket;
    }

    public void setFishMarket(Double fishMarket) {
        this.fishMarket = fishMarket;
    }

    public Double getFacility() {
        return facility;
    }

    public void setFacility(Double facility) {
        this.facility = facility;
    }

    public Double getFarmingIncome() {
        return farmingIncome;
    }

    public void setFarmingIncome(Double farmingIncome) {
        this.farmingIncome = farmingIncome;
    }

    public String getFruitForestIndustryStructure() {
        return fruitForestIndustryStructure;
    }

    public void setFruitForestIndustryStructure(String fruitForestIndustryStructure) {
        this.fruitForestIndustryStructure = fruitForestIndustryStructure;
    }

    public Double getFruitForestArea() {
        return fruitForestArea;
    }

    public void setFruitForestArea(Double fruitForestArea) {
        this.fruitForestArea = fruitForestArea;
    }

    public String getNurseryForestType() {
        return nurseryForestType;
    }

    public void setNurseryForestType(String nurseryForestType) {
        this.nurseryForestType = nurseryForestType;
    }

    public Double getNurseryForestArea() {
        return nurseryForestArea;
    }

    public void setNurseryForestArea(Double nurseryForestArea) {
        this.nurseryForestArea = nurseryForestArea;
    }

    public Double getOtherForestArea() {
        return otherForestArea;
    }

    public void setOtherForestArea(Double otherForestArea) {
        this.otherForestArea = otherForestArea;
    }

    public Double getFruitForestIncome() {
        return fruitForestIncome;
    }

    public void setFruitForestIncome(Double fruitForestIncome) {
        this.fruitForestIncome = fruitForestIncome;
    }

    public Double getOtherIndustryIncome() {
        return otherIndustryIncome;
    }

    public void setOtherIndustryIncome(Double otherIndustryIncome) {
        this.otherIndustryIncome = otherIndustryIncome;
    }

    public Double getLandTransferArea() {
        return landTransferArea;
    }

    public void setLandTransferArea(Double landTransferArea) {
        this.landTransferArea = landTransferArea;
    }

    public Double getLandTransferIncome() {
        return landTransferIncome;
    }

    public void setLandTransferIncome(Double landTransferIncome) {
        this.landTransferIncome = landTransferIncome;
    }

    public Double getLeadingEnterpriseDriveIncome() {
        return leadingEnterpriseDriveIncome;
    }

    public void setLeadingEnterpriseDriveIncome(Double leadingEnterpriseDriveIncome) {
        this.leadingEnterpriseDriveIncome = leadingEnterpriseDriveIncome;
    }

    public Double getCooperationDriveIncome() {
        return cooperationDriveIncome;
    }

    public void setCooperationDriveIncome(Double cooperationDriveIncome) {
        this.cooperationDriveIncome = cooperationDriveIncome;
    }

    public Double getInfluentialFamilyDriveIncome() {
        return influentialFamilyDriveIncome;
    }

    public void setInfluentialFamilyDriveIncome(Double influentialFamilyDriveIncome) {
        this.influentialFamilyDriveIncome = influentialFamilyDriveIncome;
    }

    public Boolean getJoinCooperation() {
        return joinCooperation;
    }

    public void setJoinCooperation(Boolean joinCooperation) {
        this.joinCooperation = joinCooperation;
    }

    public Boolean getJoinAgriculturalInsurance() {
        return joinAgriculturalInsurance;
    }

    public void setJoinAgriculturalInsurance(Boolean joinAgriculturalInsurance) {
        this.joinAgriculturalInsurance = joinAgriculturalInsurance;
    }

    public Integer getWorkersNumber() {
        return workersNumber;
    }

    public void setWorkersNumber(Integer workersNumber) {
        this.workersNumber = workersNumber;
    }

    public Double getWorkIncome() {
        return workIncome;
    }

    public void setWorkIncome(Double workIncome) {
        this.workIncome = workIncome;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanInstitution() {
        return loanInstitution;
    }

    public void setLoanInstitution(String loanInstitution) {
        this.loanInstitution = loanInstitution;
    }

    public String getLoanAccount() {
        return loanAccount;
    }

    public void setLoanAccount(String loanAccount) {
        this.loanAccount = loanAccount;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(Date lendingTime) {
        this.lendingTime = lendingTime;
    }

    public Double getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Double loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Double getAnnualInterest() {
        return annualInterest;
    }

    public void setAnnualInterest(Double annualInterest) {
        this.annualInterest = annualInterest;
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

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Double getTotalIndustryIncome() {
        return totalIndustryIncome;
    }

    public void setTotalIndustryIncome(Double totalIndustryIncome) {
        this.totalIndustryIncome = totalIndustryIncome;
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
