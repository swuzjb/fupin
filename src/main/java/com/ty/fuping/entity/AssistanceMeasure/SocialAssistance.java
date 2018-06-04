package com.ty.fuping.entity.AssistanceMeasure;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Family;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/22.
 * 社会援助
 */
@Entity
public class SocialAssistance {
    @Id
    @GeneratedValue
    private Integer socialAssistanceId;
    //所属贫困户
    @ManyToOne(cascade = {CascadeType.REFRESH, }, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //上报季度
    private String quarter;
    /**
     * 低保政策
     */
    //享受低保人数-1
    private Integer numberOfLow1;
    //低保类型-1
    private String kindOfLow1;
    //月人均补助水平(元/人.月)-1
    private Double assistancePerMonth1;
    //享受低保月份-1
    private String monthOfLow1;
    //享受低保人数-2
    private Integer numberOfLow2;
    //低保类型-2
    private String kindOfLow2;
    //月人均补助水平(元/人.月)-2
    private Double assistancePerMonth2;
    //享受低保月份-2
    private String monthOfLow2;
    //享受低保人数-3
    private Integer numberOfLow3;
    //低保类型-3
    private String kindOfLow3;
    //月人均补助水平(元/人.月)-3
    private Double assistancePerMonth3;
    //享受低保月份-3
    private String monthOfLow3;
    /**
     * 五保供养
     */
    //五保供养人数-1
    private Integer numberOfSupport1;
    //月人均领取供养金-1
    private Double supportPerMonth1;
    //享受五保供养月份-1
    private String monthOfSupport1;
    //五保供养人数-2
    private Integer numberOfSupport2;
    //月人均领取供养金-2
    private Double supportPerMonth2;
    //享受五保供养月份-2
    private String monthOfSupport2;
    //五保供养人数-3
    private Integer numberOfSupport3;
    //月人均领取供养金-3
    private Double supportPerMonth3;
    //享受五保供养月份-3
    private String monthOfSupport3;
    /**
     * 医疗救助政策
     */
    //民政医疗救助人数
    private Integer numberOfMedicalAssistance;
    //民政医疗救助金额
    private Double medicalAssistanceMoney;
    //资助新型农村合作医疗人数(人)
    private Integer numberOfCooperativeMedicalCare;
    //资助标准(元/人.年)
    private Double fundingStandard;
    //资助新型农村合作医疗金额(元)：
    private Double cooperativeMedicalCareMoney;
    /**
     * 临时救助政策
     */
    //临时救助(人次)
    private Integer temporaryAssistance;
    //救助原因
    private String assistanceReason;
    //临时救助金额（元）
    private Double temporaryAssistanceMoney;
    /**
     * 社会救助成效
     */
    //社会救助措施总收入(元
    private Double socialAssistanceIncome;
    //家庭季低保金收入(元)
    private Double minimumLivingStandardIncome;
    //家庭季五保供养金收入(元)
    private Double fiveGuaranteesIncome;
    //家庭季医疗救助金收入(元)
    private Double medicalAssistanceIncome;
    //家庭季临时救助金收入(元)
    private Double temporaryAssistanceIncome;
    //备注
    private String remark;
    //帮扶责任人
    @ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //填表时间
    private Date fillInTime;

    public Integer getSocialAssistanceId() {
        return socialAssistanceId;
    }

    public void setSocialAssistanceId(Integer socialAssistanceId) {
        this.socialAssistanceId = socialAssistanceId;
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

    public Integer getNumberOfLow1() {
        return numberOfLow1;
    }

    public void setNumberOfLow1(Integer numberOfLow1) {
        this.numberOfLow1 = numberOfLow1;
    }

    public String getKindOfLow1() {
        return kindOfLow1;
    }

    public void setKindOfLow1(String kindOfLow1) {
        this.kindOfLow1 = kindOfLow1;
    }

    public Double getAssistancePerMonth1() {
        return assistancePerMonth1;
    }

    public void setAssistancePerMonth1(Double assistancePerMonth1) {
        this.assistancePerMonth1 = assistancePerMonth1;
    }

    public String getMonthOfLow1() {
        return monthOfLow1;
    }

    public void setMonthOfLow1(String monthOfLow1) {
        this.monthOfLow1 = monthOfLow1;
    }

    public Integer getNumberOfLow2() {
        return numberOfLow2;
    }

    public void setNumberOfLow2(Integer numberOfLow2) {
        this.numberOfLow2 = numberOfLow2;
    }

    public String getKindOfLow2() {
        return kindOfLow2;
    }

    public void setKindOfLow2(String kindOfLow2) {
        this.kindOfLow2 = kindOfLow2;
    }

    public Double getAssistancePerMonth2() {
        return assistancePerMonth2;
    }

    public void setAssistancePerMonth2(Double assistancePerMonth2) {
        this.assistancePerMonth2 = assistancePerMonth2;
    }

    public String getMonthOfLow2() {
        return monthOfLow2;
    }

    public void setMonthOfLow2(String monthOfLow2) {
        this.monthOfLow2 = monthOfLow2;
    }

    public Integer getNumberOfLow3() {
        return numberOfLow3;
    }

    public void setNumberOfLow3(Integer numberOfLow3) {
        this.numberOfLow3 = numberOfLow3;
    }

    public String getKindOfLow3() {
        return kindOfLow3;
    }

    public void setKindOfLow3(String kindOfLow3) {
        this.kindOfLow3 = kindOfLow3;
    }

    public Double getAssistancePerMonth3() {
        return assistancePerMonth3;
    }

    public void setAssistancePerMonth3(Double assistancePerMonth3) {
        this.assistancePerMonth3 = assistancePerMonth3;
    }

    public String getMonthOfLow3() {
        return monthOfLow3;
    }

    public void setMonthOfLow3(String monthOfLow3) {
        this.monthOfLow3 = monthOfLow3;
    }

    public Integer getNumberOfSupport1() {
        return numberOfSupport1;
    }

    public void setNumberOfSupport1(Integer numberOfSupport1) {
        this.numberOfSupport1 = numberOfSupport1;
    }

    public Double getSupportPerMonth1() {
        return supportPerMonth1;
    }

    public void setSupportPerMonth1(Double supportPerMonth1) {
        this.supportPerMonth1 = supportPerMonth1;
    }

    public String getMonthOfSupport1() {
        return monthOfSupport1;
    }

    public void setMonthOfSupport1(String monthOfSupport1) {
        this.monthOfSupport1 = monthOfSupport1;
    }

    public Integer getNumberOfSupport2() {
        return numberOfSupport2;
    }

    public void setNumberOfSupport2(Integer numberOfSupport2) {
        this.numberOfSupport2 = numberOfSupport2;
    }

    public Double getSupportPerMonth2() {
        return supportPerMonth2;
    }

    public void setSupportPerMonth2(Double supportPerMonth2) {
        this.supportPerMonth2 = supportPerMonth2;
    }

    public String getMonthOfSupport2() {
        return monthOfSupport2;
    }

    public void setMonthOfSupport2(String monthOfSupport2) {
        this.monthOfSupport2 = monthOfSupport2;
    }

    public Integer getNumberOfSupport3() {
        return numberOfSupport3;
    }

    public void setNumberOfSupport3(Integer numberOfSupport3) {
        this.numberOfSupport3 = numberOfSupport3;
    }

    public Double getSupportPerMonth3() {
        return supportPerMonth3;
    }

    public void setSupportPerMonth3(Double supportPerMonth3) {
        this.supportPerMonth3 = supportPerMonth3;
    }

    public String getMonthOfSupport3() {
        return monthOfSupport3;
    }

    public void setMonthOfSupport3(String monthOfSupport3) {
        this.monthOfSupport3 = monthOfSupport3;
    }

    public Integer getNumberOfMedicalAssistance() {
        return numberOfMedicalAssistance;
    }

    public void setNumberOfMedicalAssistance(Integer numberOfMedicalAssistance) {
        this.numberOfMedicalAssistance = numberOfMedicalAssistance;
    }

    public Double getMedicalAssistanceMoney() {
        return medicalAssistanceMoney;
    }

    public void setMedicalAssistanceMoney(Double medicalAssistanceMoney) {
        this.medicalAssistanceMoney = medicalAssistanceMoney;
    }

    public Integer getNumberOfCooperativeMedicalCare() {
        return numberOfCooperativeMedicalCare;
    }

    public void setNumberOfCooperativeMedicalCare(Integer numberOfCooperativeMedicalCare) {
        this.numberOfCooperativeMedicalCare = numberOfCooperativeMedicalCare;
    }

    public Double getFundingStandard() {
        return fundingStandard;
    }

    public void setFundingStandard(Double fundingStandard) {
        this.fundingStandard = fundingStandard;
    }

    public Double getCooperativeMedicalCareMoney() {
        return cooperativeMedicalCareMoney;
    }

    public void setCooperativeMedicalCareMoney(Double cooperativeMedicalCareMoney) {
        this.cooperativeMedicalCareMoney = cooperativeMedicalCareMoney;
    }

    public Integer getTemporaryAssistance() {
        return temporaryAssistance;
    }

    public void setTemporaryAssistance(Integer temporaryAssistance) {
        this.temporaryAssistance = temporaryAssistance;
    }

    public String getAssistanceReason() {
        return assistanceReason;
    }

    public void setAssistanceReason(String assistanceReason) {
        this.assistanceReason = assistanceReason;
    }

    public Double getTemporaryAssistanceMoney() {
        return temporaryAssistanceMoney;
    }

    public void setTemporaryAssistanceMoney(Double temporaryAssistanceMoney) {
        this.temporaryAssistanceMoney = temporaryAssistanceMoney;
    }

    public Double getSocialAssistanceIncome() {
        return socialAssistanceIncome;
    }

    public void setSocialAssistanceIncome(Double socialAssistanceIncome) {
        this.socialAssistanceIncome = socialAssistanceIncome;
    }

    public Double getMinimumLivingStandardIncome() {
        return minimumLivingStandardIncome;
    }

    public void setMinimumLivingStandardIncome(Double minimumLivingStandardIncome) {
        this.minimumLivingStandardIncome = minimumLivingStandardIncome;
    }

    public Double getFiveGuaranteesIncome() {
        return fiveGuaranteesIncome;
    }

    public void setFiveGuaranteesIncome(Double fiveGuaranteesIncome) {
        this.fiveGuaranteesIncome = fiveGuaranteesIncome;
    }

    public Double getMedicalAssistanceIncome() {
        return medicalAssistanceIncome;
    }

    public void setMedicalAssistanceIncome(Double medicalAssistanceIncome) {
        this.medicalAssistanceIncome = medicalAssistanceIncome;
    }

    public Double getTemporaryAssistanceIncome() {
        return temporaryAssistanceIncome;
    }

    public void setTemporaryAssistanceIncome(Double temporaryAssistanceIncome) {
        this.temporaryAssistanceIncome = temporaryAssistanceIncome;
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
