package com.ty.fuping.entity.AssistanceMeasure;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Family;
import com.ty.fuping.entity.FamilyMember;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/22.
 * 劳动力培训
 */
@Entity
public class LaborTraining {
    @Id
    @GeneratedValue
    private Integer laborTrainingId;
    //所属贫困户
    @ManyToOne(cascade = {CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //上报季度
    private String quarter;
    //培训人
    @ManyToOne(cascade = { CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "familyMemberId")
    private FamilyMember familyMember;
    /**
     * 培训需求
     */
    //养殖
    private Boolean cultivation;
    //种植
    private Boolean plant;
    //餐饮业
    private Boolean catering;
    //家政服务
    private Boolean housekeeping;
    //建筑服务
    private Boolean buildingService;
    //制造业
    private Boolean industry;
    //创业
    private Boolean startUp;
    //实用技术
    private Boolean operativeTechnology;
    //其他
    private Boolean others;
    /**
     * 培训措施
     */
    //就业技能培训
    private Boolean jobSkillTraining;
    //两后生培训
    private Boolean biepigeneticTraining;
    //劳务品牌服务
    private Boolean serviceBrand;
    //新型职业农民培育工程
    private Boolean vocationalTraining;
    //示范培训
    private Boolean demonstrationTraining;
    //岗位技能提升培训
    private Boolean skillUpgradingTraining;
    //创业培训
    private Boolean enterpriseTraining;
    //培训机构
    private String trainingAgency;
    //培训日期
    private Date trainingDate;
    //培训时间
    private String TrainingLength;
    //就业专项资金
    private Double employmentFund;
    //农牧专项资金
    private Double husbandryFund;
    //扶贫专项资金
    private Double povertyReliefFund;
    //其他资金
    private Double otherFund;
    /**
     * 培训成效
     */
    //取证情况
    private String certificate;
    //务工去向
    private String workDirection;
    //年务工时间
    private Double workTimeInYear;
    //务工年收入
    private Double workIncome;
    //务农年收入
    private Double farmerIncome;
    //备注
    private String remark;
    //帮扶责任人
    @ManyToOne(cascade = {CascadeType.REFRESH,}, optional = false)
    @JoinColumn(name = "assessmentObjectId")
    AssessmentObject assessmentObject;
    //填表时间
    private Date fillInTime;

    public Integer getLaborTrainingId() {
        return laborTrainingId;
    }

    public void setLaborTrainingId(Integer laborTrainingId) {
        this.laborTrainingId = laborTrainingId;
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

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public Boolean getCultivation() {
        return cultivation;
    }

    public void setCultivation(Boolean cultivation) {
        this.cultivation = cultivation;
    }

    public Boolean getPlant() {
        return plant;
    }

    public void setPlant(Boolean plant) {
        this.plant = plant;
    }

    public Boolean getCatering() {
        return catering;
    }

    public void setCatering(Boolean catering) {
        this.catering = catering;
    }

    public Boolean getHousekeeping() {
        return housekeeping;
    }

    public void setHousekeeping(Boolean housekeeping) {
        this.housekeeping = housekeeping;
    }

    public Boolean getBuildingService() {
        return buildingService;
    }

    public void setBuildingService(Boolean buildingService) {
        this.buildingService = buildingService;
    }

    public Boolean getIndustry() {
        return industry;
    }

    public void setIndustry(Boolean industry) {
        this.industry = industry;
    }

    public Boolean getStartUp() {
        return startUp;
    }

    public void setStartUp(Boolean startUp) {
        this.startUp = startUp;
    }

    public Boolean getOperativeTechnology() {
        return operativeTechnology;
    }

    public void setOperativeTechnology(Boolean operativeTechnology) {
        this.operativeTechnology = operativeTechnology;
    }

    public Boolean getOthers() {
        return others;
    }

    public void setOthers(Boolean others) {
        this.others = others;
    }

    public Boolean getJobSkillTraining() {
        return jobSkillTraining;
    }

    public void setJobSkillTraining(Boolean jobSkillTraining) {
        this.jobSkillTraining = jobSkillTraining;
    }

    public Boolean getBiepigeneticTraining() {
        return biepigeneticTraining;
    }

    public void setBiepigeneticTraining(Boolean biepigeneticTraining) {
        this.biepigeneticTraining = biepigeneticTraining;
    }

    public Boolean getServiceBrand() {
        return serviceBrand;
    }

    public void setServiceBrand(Boolean serviceBrand) {
        this.serviceBrand = serviceBrand;
    }

    public Boolean getVocationalTraining() {
        return vocationalTraining;
    }

    public void setVocationalTraining(Boolean vocationalTraining) {
        this.vocationalTraining = vocationalTraining;
    }

    public Boolean getDemonstrationTraining() {
        return demonstrationTraining;
    }

    public void setDemonstrationTraining(Boolean demonstrationTraining) {
        this.demonstrationTraining = demonstrationTraining;
    }

    public Boolean getSkillUpgradingTraining() {
        return skillUpgradingTraining;
    }

    public void setSkillUpgradingTraining(Boolean skillUpgradingTraining) {
        this.skillUpgradingTraining = skillUpgradingTraining;
    }

    public Boolean getEnterpriseTraining() {
        return enterpriseTraining;
    }

    public void setEnterpriseTraining(Boolean enterpriseTraining) {
        this.enterpriseTraining = enterpriseTraining;
    }

    public String getTrainingAgency() {
        return trainingAgency;
    }

    public void setTrainingAgency(String trainingAgency) {
        this.trainingAgency = trainingAgency;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTrainingLength() {
        return TrainingLength;
    }

    public void setTrainingLength(String trainingLength) {
        TrainingLength = trainingLength;
    }

    public Double getEmploymentFund() {
        return employmentFund;
    }

    public void setEmploymentFund(Double employmentFund) {
        this.employmentFund = employmentFund;
    }

    public Double getHusbandryFund() {
        return husbandryFund;
    }

    public void setHusbandryFund(Double husbandryFund) {
        this.husbandryFund = husbandryFund;
    }

    public Double getPovertyReliefFund() {
        return povertyReliefFund;
    }

    public void setPovertyReliefFund(Double povertyReliefFund) {
        this.povertyReliefFund = povertyReliefFund;
    }

    public Double getOtherFund() {
        return otherFund;
    }

    public void setOtherFund(Double otherFund) {
        this.otherFund = otherFund;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getWorkDirection() {
        return workDirection;
    }

    public void setWorkDirection(String workDirection) {
        this.workDirection = workDirection;
    }

    public Double getWorkTimeInYear() {
        return workTimeInYear;
    }

    public void setWorkTimeInYear(Double workTimeInYear) {
        this.workTimeInYear = workTimeInYear;
    }

    public Double getWorkIncome() {
        return workIncome;
    }

    public void setWorkIncome(Double workIncome) {
        this.workIncome = workIncome;
    }

    public Double getFarmerIncome() {
        return farmerIncome;
    }

    public void setFarmerIncome(Double farmerIncome) {
        this.farmerIncome = farmerIncome;
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
