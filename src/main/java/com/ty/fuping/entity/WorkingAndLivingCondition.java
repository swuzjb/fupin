package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by ty on 2017/12/14.
 * 生产生活条件
 */
@Entity
public class WorkingAndLivingCondition {
    @Id
    @GeneratedValue
    private Integer workingAndLivingConditionId;
    //所属家庭
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "familyId")
    private Family family;
    /**
     * 生产条件
     */
    //耕地面积
    private Double cultivatedArea;
    //有效灌溉面积
    private Double wateringArea;
    //林地面积
    private Double forestArea;
    //退耕还林面积
    private Double cultivatedIntoForestArea;
    //林果面积
    private Double fruitForestArea;
    //牧草地面积
    private Double grassArea;
    //水面面积
    private Double waterSurfaceArea;
    //是否通生产用电
    private Boolean hasProductElectricity;
    /**
     * 生活条件
     */
    //与主干路距离
    private Double distanceWithRoad;
    //入户路类型
    private String roadKind;
    //住房面积
    private Double houseArea;
    //是否通生活用电
    private Boolean hasLiveElectricity;
    //饮水是否困难
    private Boolean isHardToDrink;
    //饮水是否安全
    private Boolean isDrinkingWaterSafe;
    //是否危房户
    private Boolean isDangerousBuilding;
    //主要燃料类型
    private String kindOfFuel;
    //是否加入农民专业合作社
    private Boolean isJoinCooperation;
    /**
     * 收入支出
     */
    //工资性收入
    private Double wageIncome;
    //财产性收入
    private Double propertyIncome;
    //计划生育金
    private Double familyPlanningMoney;
    //五保金
    private Double fiveGuaranteed;
    //生态补偿金
    private Double ecologicalCompensation;
    //年收入（自动生成）
    private Double incomePerYear;
    //纯收入(自动生成)
    private Double netIncomePerYear;
    //是否通广播电视
    private Boolean hasTelevisionSignal;
    //医疗救助金
    private Double medicalAssistanceMoney;
    //未偿还借（贷）款
    private Double loans;
    //有无因病、因学贷款
    private Double illOrSchoolLoans;
    //生产经营性收入
    private Double productionIncome;
    //转移性收入(自动生成)
    private Double transferIncome;
    //低保金
    private Double lowHouseholdMoney;
    //养老保险金
    private Double endowmentInsurance;
    //其他转移性收入
    private Double otherTransferIncome;
    //生产性支出
    private Double productionOutput;
    //人均收入(自动生成)
    private Double perMemberIncome;
    //农资综合补贴
    private Double synthesizeSubsidy;
    //粮食直补
    private Double grainSubsidy;
    //新农合医疗保险
    private Double medicalInsurance;
    //贫困户人均生活消费支出
    private Double perMemberOutput;

    public Integer getWorkingAndLivingConditionId() {
        return workingAndLivingConditionId;
    }

    public void setWorkingAndLivingConditionId(Integer workingAndLivingConditionId) {
        this.workingAndLivingConditionId = workingAndLivingConditionId;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Double getCultivatedArea() {
        return cultivatedArea;
    }

    public void setCultivatedArea(Double cultivatedArea) {
        this.cultivatedArea = cultivatedArea;
    }

    public Double getWateringArea() {
        return wateringArea;
    }

    public void setWateringArea(Double wateringArea) {
        this.wateringArea = wateringArea;
    }

    public Double getForestArea() {
        return forestArea;
    }

    public void setForestArea(Double forestArea) {
        this.forestArea = forestArea;
    }

    public Double getCultivatedIntoForestArea() {
        return cultivatedIntoForestArea;
    }

    public void setCultivatedIntoForestArea(Double cultivatedIntoForestArea) {
        this.cultivatedIntoForestArea = cultivatedIntoForestArea;
    }

    public Double getFruitForestArea() {
        return fruitForestArea;
    }

    public void setFruitForestArea(Double fruitForestArea) {
        this.fruitForestArea = fruitForestArea;
    }

    public Double getGrassArea() {
        return grassArea;
    }

    public void setGrassArea(Double grassArea) {
        this.grassArea = grassArea;
    }

    public Double getWaterSurfaceArea() {
        return waterSurfaceArea;
    }

    public void setWaterSurfaceArea(Double waterSurfaceArea) {
        this.waterSurfaceArea = waterSurfaceArea;
    }

    public Boolean getHasProductElectricity() {
        return hasProductElectricity;
    }

    public void setHasProductElectricity(Boolean hasProductElectricity) {
        this.hasProductElectricity = hasProductElectricity;
    }

    public Double getDistanceWithRoad() {
        return distanceWithRoad;
    }

    public void setDistanceWithRoad(Double distanceWithRoad) {
        this.distanceWithRoad = distanceWithRoad;
    }

    public String getRoadKind() {
        return roadKind;
    }

    public void setRoadKind(String roadKind) {
        this.roadKind = roadKind;
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public Boolean getHasLiveElectricity() {
        return hasLiveElectricity;
    }

    public void setHasLiveElectricity(Boolean hasLiveElectricity) {
        this.hasLiveElectricity = hasLiveElectricity;
    }

    public Boolean getHardToDrink() {
        return isHardToDrink;
    }

    public void setHardToDrink(Boolean hardToDrink) {
        isHardToDrink = hardToDrink;
    }

    public Boolean getDrinkingWaterSafe() {
        return isDrinkingWaterSafe;
    }

    public void setDrinkingWaterSafe(Boolean drinkingWaterSafe) {
        isDrinkingWaterSafe = drinkingWaterSafe;
    }

    public Boolean getDangerousBuilding() {
        return isDangerousBuilding;
    }

    public void setDangerousBuilding(Boolean dangerousBuilding) {
        isDangerousBuilding = dangerousBuilding;
    }

    public String getKindOfFuel() {
        return kindOfFuel;
    }

    public void setKindOfFuel(String kindOfFuel) {
        this.kindOfFuel = kindOfFuel;
    }

    public Boolean getJoinCooperation() {
        return isJoinCooperation;
    }

    public void setJoinCooperation(Boolean joinCooperation) {
        isJoinCooperation = joinCooperation;
    }

    public Double getWageIncome() {
        return wageIncome;
    }

    public void setWageIncome(Double wageIncome) {
        this.wageIncome = wageIncome;
    }

    public Double getPropertyIncome() {
        return propertyIncome;
    }

    public void setPropertyIncome(Double propertyIncome) {
        this.propertyIncome = propertyIncome;
    }

    public Double getFamilyPlanningMoney() {
        return familyPlanningMoney;
    }

    public void setFamilyPlanningMoney(Double familyPlanningMoney) {
        this.familyPlanningMoney = familyPlanningMoney;
    }

    public Double getFiveGuaranteed() {
        return fiveGuaranteed;
    }

    public void setFiveGuaranteed(Double fiveGuaranteed) {
        this.fiveGuaranteed = fiveGuaranteed;
    }

    public Double getEcologicalCompensation() {
        return ecologicalCompensation;
    }

    public void setEcologicalCompensation(Double ecologicalCompensation) {
        this.ecologicalCompensation = ecologicalCompensation;
    }

    public Double getIncomePerYear() {
        return incomePerYear;
    }

    public void setIncomePerYear(Double incomePerYear) {
        this.incomePerYear = incomePerYear;
    }

    public Double getNetIncomePerYear() {
        return netIncomePerYear;
    }

    public void setNetIncomePerYear(Double netIncomePerYear) {
        this.netIncomePerYear = netIncomePerYear;
    }

    public Boolean getHasTelevisionSignal() {
        return hasTelevisionSignal;
    }

    public void setHasTelevisionSignal(Boolean hasTelevisionSignal) {
        this.hasTelevisionSignal = hasTelevisionSignal;
    }

    public Double getMedicalAssistanceMoney() {
        return medicalAssistanceMoney;
    }

    public void setMedicalAssistanceMoney(Double medicalAssistanceMoney) {
        this.medicalAssistanceMoney = medicalAssistanceMoney;
    }

    public Double getLoans() {
        return loans;
    }

    public void setLoans(Double loans) {
        this.loans = loans;
    }

    public Double getIllOrSchoolLoans() {
        return illOrSchoolLoans;
    }

    public void setIllOrSchoolLoans(Double illOrSchoolLoans) {
        this.illOrSchoolLoans = illOrSchoolLoans;
    }

    public Double getProductionIncome() {
        return productionIncome;
    }

    public void setProductionIncome(Double productionIncome) {
        this.productionIncome = productionIncome;
    }

    public Double getTransferIncome() {
        return transferIncome;
    }

    public void setTransferIncome(Double transferIncome) {
        this.transferIncome = transferIncome;
    }

    public Double getLowHouseholdMoney() {
        return lowHouseholdMoney;
    }

    public void setLowHouseholdMoney(Double lowHouseholdMoney) {
        this.lowHouseholdMoney = lowHouseholdMoney;
    }

    public Double getEndowmentInsurance() {
        return endowmentInsurance;
    }

    public void setEndowmentInsurance(Double endowmentInsurance) {
        this.endowmentInsurance = endowmentInsurance;
    }

    public Double getOtherTransferIncome() {
        return otherTransferIncome;
    }

    public void setOtherTransferIncome(Double otherTransferIncome) {
        this.otherTransferIncome = otherTransferIncome;
    }

    public Double getProductionOutput() {
        return productionOutput;
    }

    public void setProductionOutput(Double productionOutput) {
        this.productionOutput = productionOutput;
    }

    public Double getPerMemberIncome() {
        return perMemberIncome;
    }

    public void setPerMemberIncome(Double perMemberIncome) {
        this.perMemberIncome = perMemberIncome;
    }

    public Double getSynthesizeSubsidy() {
        return synthesizeSubsidy;
    }

    public void setSynthesizeSubsidy(Double synthesizeSubsidy) {
        this.synthesizeSubsidy = synthesizeSubsidy;
    }

    public Double getGrainSubsidy() {
        return grainSubsidy;
    }

    public void setGrainSubsidy(Double grainSubsidy) {
        this.grainSubsidy = grainSubsidy;
    }

    public Double getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Double medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public Double getPerMemberOutput() {
        return perMemberOutput;
    }

    public void setPerMemberOutput(Double perMemberOutput) {
        this.perMemberOutput = perMemberOutput;
    }
}
