package com.ty.fuping.entity.AssistanceMeasure;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Family;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/22.
 * 金融扶贫
 */
@Entity
public class FinancialAssistance {
    @Id
    @GeneratedValue
    private Integer financialAssistanceId;
    //所属贫困户
    @ManyToOne(cascade = { CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //上报季度
    private String quarter;
    //使用方式
    private String useMode;
    /**
     * 贷款情况
     */
    //承贷机构名称
    private String loadInstitution;
    //银行账号
    private String bankAccount;
    //贷款用途
    private String useOfLoad;
    //贷款金额(元)
    private Double loadAmount;
    //贷款期限(月)
    private Double loanPeriod;
    //执行年利率(%)
    private Double annualInterest;
    //贷款起始日
    private Date lendingTime;
    //贷款结束日
    private Date repaymentDate;
    //应贴息金额(元)
    private Double discountAmount;
    //年增加收入(元)
    private Double generateIncome;
    //备注
    private String remark;
    //帮扶责任人
    @ManyToOne(cascade = {CascadeType.REFRESH,}, optional = false)
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //填表时间
    private Date fillInTime;

    public Integer getFinancialAssistanceId() {
        return financialAssistanceId;
    }

    public void setFinancialAssistanceId(Integer financialAssistanceId) {
        this.financialAssistanceId = financialAssistanceId;
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

    public String getUseMode() {
        return useMode;
    }

    public void setUseMode(String useMode) {
        this.useMode = useMode;
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

    public Double getLoadAmount() {
        return loadAmount;
    }

    public void setLoadAmount(Double loadAmount) {
        this.loadAmount = loadAmount;
    }

    public Double getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Double loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public Double getAnnualInterest() {
        return annualInterest;
    }

    public void setAnnualInterest(Double annualInterest) {
        this.annualInterest = annualInterest;
    }

    public Date getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(Date lendingTime) {
        this.lendingTime = lendingTime;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getGenerateIncome() {
        return generateIncome;
    }

    public void setGenerateIncome(Double generateIncome) {
        this.generateIncome = generateIncome;
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
