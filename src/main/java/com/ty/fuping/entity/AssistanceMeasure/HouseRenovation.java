package com.ty.fuping.entity.AssistanceMeasure;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Family;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/22.
 * 危房改造
 */
@Entity
public class HouseRenovation {
    @Id
    @GeneratedValue
    private Integer houseRenovationId;
    //所属贫困户
    @ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //上报季度
    private String quarter;
    //有无住房
    private Boolean hasHouse;
    //建造年代
    private String buildTime;
    //住房结构
    private String houseStructure;
    //住房建筑面积（㎡）
    private Double coveredArea;
    //人均住房面积（㎡）
    private Double coveredAreaPerPeople;
    //住房危险等级
    private String houseLevel;
    /**
     * 扶贫措施
     */
    //批准列入危房改造计划年度
    private String houseRenovationTime;
    //计划投资
    private Double plannedInvestment;
    //中央资金
    private Double centralFunds;
    //省级资金
    private Double provincialFunds;
    //市县配套资金
    private Double countyFunds;
    //自筹资金(元)
    private Double selfRaisedFunds;
    //"双联"单位 社会帮扶资金（元）
    private Double duplexUnit;
    //企业、社会投资
    private Double enterpriseFunds;
    /**
     * 危房改造贷款
     */
    //贷款类型
    private String loanType;
    //承贷金融机构
    private String loadInstitution;
    //银行帐号
    private String bankAccount;
    //贷款金额
    private String useOfLoad;
    //贷款发放时间
    private Date lendingTime;
    //贷款期限（月）
    private Double loanPeriod;
    //还款时间
    private Date repaymentDate;
    //执行年利率（%）
    private Double annualInterest;
    //贷款利息
    private Double loanInterest;
    //应贴息金额（元）
    private Double discountAmount;
    /**
     * 扶贫成效
     */
    //改造后住房结构
    private String afterReformHouseStructure;
    //改造后住房面积（㎡）
    private Double  afterReformCoveredArea;
    //开工时间
    private Date startDate;
    //验收时间
    private Date endDate;
    //备注
    private String remark;
    //帮扶责任人
    @ManyToOne(cascade = { CascadeType.REFRESH,}, optional = false)
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //填表时间
    private Date fillInTime;

    public Integer getHouseRenovationId() {
        return houseRenovationId;
    }

    public void setHouseRenovationId(Integer houseRenovationId) {
        this.houseRenovationId = houseRenovationId;
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

    public Boolean getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(Boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getHouseStructure() {
        return houseStructure;
    }

    public void setHouseStructure(String houseStructure) {
        this.houseStructure = houseStructure;
    }

    public Double getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(Double coveredArea) {
        this.coveredArea = coveredArea;
    }

    public Double getCoveredAreaPerPeople() {
        return coveredAreaPerPeople;
    }

    public void setCoveredAreaPerPeople(Double coveredAreaPerPeople) {
        this.coveredAreaPerPeople = coveredAreaPerPeople;
    }

    public String getHouseLevel() {
        return houseLevel;
    }

    public void setHouseLevel(String houseLevel) {
        this.houseLevel = houseLevel;
    }

    public String getHouseRenovationTime() {
        return houseRenovationTime;
    }

    public void setHouseRenovationTime(String houseRenovationTime) {
        this.houseRenovationTime = houseRenovationTime;
    }

    public Double getPlannedInvestment() {
        return plannedInvestment;
    }

    public void setPlannedInvestment(Double plannedInvestment) {
        this.plannedInvestment = plannedInvestment;
    }

    public Double getCentralFunds() {
        return centralFunds;
    }

    public void setCentralFunds(Double centralFunds) {
        this.centralFunds = centralFunds;
    }

    public Double getProvincialFunds() {
        return provincialFunds;
    }

    public void setProvincialFunds(Double provincialFunds) {
        this.provincialFunds = provincialFunds;
    }

    public Double getCountyFunds() {
        return countyFunds;
    }

    public void setCountyFunds(Double countyFunds) {
        this.countyFunds = countyFunds;
    }

    public Double getSelfRaisedFunds() {
        return selfRaisedFunds;
    }

    public void setSelfRaisedFunds(Double selfRaisedFunds) {
        this.selfRaisedFunds = selfRaisedFunds;
    }

    public Double getDuplexUnit() {
        return duplexUnit;
    }

    public void setDuplexUnit(Double duplexUnit) {
        this.duplexUnit = duplexUnit;
    }

    public Double getEnterpriseFunds() {
        return enterpriseFunds;
    }

    public void setEnterpriseFunds(Double enterpriseFunds) {
        this.enterpriseFunds = enterpriseFunds;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoadInstitution() {
        return loadInstitution;
    }

    public void setLoadInstitution(String loadInstitution) {
        this.loadInstitution = loadInstitution;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUseOfLoad() {
        return useOfLoad;
    }

    public void setUseOfLoad(String useOfLoad) {
        this.useOfLoad = useOfLoad;
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

    public String getAfterReformHouseStructure() {
        return afterReformHouseStructure;
    }

    public void setAfterReformHouseStructure(String afterReformHouseStructure) {
        this.afterReformHouseStructure = afterReformHouseStructure;
    }

    public Double getAfterReformCoveredArea() {
        return afterReformCoveredArea;
    }

    public void setAfterReformCoveredArea(Double afterReformCoveredArea) {
        this.afterReformCoveredArea = afterReformCoveredArea;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
