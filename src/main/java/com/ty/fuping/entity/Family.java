package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.fuping.entity.AssistanceMeasure.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by ty on 2017/12/11.
 * 户主
 */
@Entity
public class Family {
    //家庭Id
    @Id
    @GeneratedValue
    private Integer familyId;
    //户主
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "familyOwnerId")
    FamilyMember familyOwner;
    //家庭成员
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<FamilyMember> familyMembers;
    //生产生活条件
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "workingAndLivingConditionId")
    private WorkingAndLivingCondition workingAndLivingCondition;
    //易地搬迁
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<Removal> removals;
    //帮扶责任人
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<Assistance> assistances;
    //饮水安全
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<DrinkingSafety> drinkingSafeties;
    //金融扶贫
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<FinancialAssistance> financialAssistances;
    //危房改造
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "family")
    List<HouseRenovation> houseRenovations;
    //劳动力培训
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<LaborTraining> laborTrainings;
    //富民产业
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<ProsperousIndustry> prosperousIndustries;
    //社会援助
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<SocialAssistance> socialAssistances;
    //专项扶贫
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<SpecialPovertyAlleviation> specialPovertyAlleviations;
    //帮扶成效
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<AssistanceEffect> assistanceEffects;
    //走访记录
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "family")
    List<VisitRecord> visitRecords;
    //家庭人口
    private Integer familyPopulation;
    //联系电话
    private String phoneNumber;
    //开户银行
    private String bank;
    //银行卡号
    private String bankNumber;
    //所在村
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "villageId")
    private Village village;
    //因贫辍学人数
    private Integer dropoutFigures;
    //计划脱贫年份
    private Integer overcomeYear;
    //贫困户属性 1为一般贫困，2为低保贫困，3为五保贫困
    private Integer povertyGrade;
    //是否返贫
    private Boolean isBackToPoverty;
    //贫困状态 1为未脱贫，2为预脱贫，3为已脱贫
    private Integer povertyState;
    //有无帮扶政策
    private Boolean hasHelpPolicy;
    //是否军烈
    private Boolean isMartyr;
    //是否独生子女
    private Boolean isOnlyChild;
    //是否双女户
    private Boolean isDoubleDaughters;
    //是否有住房
    private Boolean hasHouse;
    //录入时间
    private Date enterTime;
    /**
     * 帮扶政策
     */
    //产业发展
    private Boolean industryDevelopment;
    //转移就业
    private Boolean changeJob;
    //助学帮置
    private Boolean helpSchool;
    //社会保障
    private Boolean socialInsurance;
    //易地搬迁
    private Boolean relocation;
    //特殊救助
    private Boolean specialHelp;
    /**
     * 主要致贫原因
     */
    //因病
    private Boolean ofIll;
    //因学
    private Boolean ofSchool;
    //因灾
    private Boolean ofDisaster;
    //因残
    private Boolean ofDisability;
    //缺土
    private Boolean lackSoil;
    //缺水
    private Boolean lackWater;
    //缺技术
    private Boolean lackTechnology;
    //缺劳动力
    private Boolean lackLabor;
    //缺资金
    private Boolean lackMoney;
    //交通条件落后
    private Boolean lackTraffic;
    //自然发展动力不足
    private Boolean lackNatureDevelopment;
    //其他
    private Boolean others;

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public FamilyMember getFamilyOwner() {
        return familyOwner;
    }

    public void setFamilyOwner(FamilyMember familyOwner) {
        this.familyOwner = familyOwner;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public WorkingAndLivingCondition getWorkingAndLivingCondition() {
        return workingAndLivingCondition;
    }

    public void setWorkingAndLivingCondition(WorkingAndLivingCondition workingAndLivingCondition) {
        this.workingAndLivingCondition = workingAndLivingCondition;
    }

    public List<Removal> getRemovals() {
        return removals;
    }

    public void setRemovals(List<Removal> removals) {
        this.removals = removals;
    }

    public List<Assistance> getAssistances() {
        return assistances;
    }

    public void setAssistances(List<Assistance> assistances) {
        this.assistances = assistances;
    }

    public List<DrinkingSafety> getDrinkingSafeties() {
        return drinkingSafeties;
    }

    public void setDrinkingSafeties(List<DrinkingSafety> drinkingSafeties) {
        this.drinkingSafeties = drinkingSafeties;
    }

    public List<FinancialAssistance> getFinancialAssistances() {
        return financialAssistances;
    }

    public void setFinancialAssistances(List<FinancialAssistance> financialAssistances) {
        this.financialAssistances = financialAssistances;
    }

    public List<HouseRenovation> getHouseRenovations() {
        return houseRenovations;
    }

    public void setHouseRenovations(List<HouseRenovation> houseRenovations) {
        this.houseRenovations = houseRenovations;
    }

    public List<LaborTraining> getLaborTrainings() {
        return laborTrainings;
    }

    public void setLaborTrainings(List<LaborTraining> laborTrainings) {
        this.laborTrainings = laborTrainings;
    }

    public List<ProsperousIndustry> getProsperousIndustries() {
        return prosperousIndustries;
    }

    public void setProsperousIndustries(List<ProsperousIndustry> prosperousIndustries) {
        this.prosperousIndustries = prosperousIndustries;
    }

    public List<SocialAssistance> getSocialAssistances() {
        return socialAssistances;
    }

    public void setSocialAssistances(List<SocialAssistance> socialAssistances) {
        this.socialAssistances = socialAssistances;
    }

    public List<SpecialPovertyAlleviation> getSpecialPovertyAlleviations() {
        return specialPovertyAlleviations;
    }

    public void setSpecialPovertyAlleviations(List<SpecialPovertyAlleviation> specialPovertyAlleviations) {
        this.specialPovertyAlleviations = specialPovertyAlleviations;
    }

    public List<AssistanceEffect> getAssistanceEffects() {
        return assistanceEffects;
    }

    public void setAssistanceEffects(List<AssistanceEffect> assistanceEffects) {
        this.assistanceEffects = assistanceEffects;
    }

    public List<VisitRecord> getVisitRecords() {
        return visitRecords;
    }

    public void setVisitRecords(List<VisitRecord> visitRecords) {
        this.visitRecords = visitRecords;
    }

    public Integer getFamilyPopulation() {
        return familyPopulation;
    }

    public void setFamilyPopulation(Integer familyPopulation) {
        this.familyPopulation = familyPopulation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public Integer getDropoutFigures() {
        return dropoutFigures;
    }

    public void setDropoutFigures(Integer dropoutFigures) {
        this.dropoutFigures = dropoutFigures;
    }

    public Integer getOvercomeYear() {
        return overcomeYear;
    }

    public void setOvercomeYear(Integer overcomeYear) {
        this.overcomeYear = overcomeYear;
    }

    public Integer getPovertyGrade() {
        return povertyGrade;
    }

    public void setPovertyGrade(Integer povertyGrade) {
        this.povertyGrade = povertyGrade;
    }

    public Boolean getBackToPoverty() {
        return isBackToPoverty;
    }

    public void setBackToPoverty(Boolean backToPoverty) {
        isBackToPoverty = backToPoverty;
    }

    public Integer getPovertyState() {
        return povertyState;
    }

    public void setPovertyState(Integer povertyState) {
        this.povertyState = povertyState;
    }

    public Boolean getHasHelpPolicy() {
        return hasHelpPolicy;
    }

    public void setHasHelpPolicy(Boolean hasHelpPolicy) {
        this.hasHelpPolicy = hasHelpPolicy;
    }

    public Boolean getMartyr() {
        return isMartyr;
    }

    public void setMartyr(Boolean martyr) {
        isMartyr = martyr;
    }

    public Boolean getOnlyChild() {
        return isOnlyChild;
    }

    public void setOnlyChild(Boolean onlyChild) {
        isOnlyChild = onlyChild;
    }

    public Boolean getDoubleDaughters() {
        return isDoubleDaughters;
    }

    public void setDoubleDaughters(Boolean doubleDaughters) {
        isDoubleDaughters = doubleDaughters;
    }

    public Boolean getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(Boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Boolean getIndustryDevelopment() {
        return industryDevelopment;
    }

    public void setIndustryDevelopment(Boolean industryDevelopment) {
        this.industryDevelopment = industryDevelopment;
    }

    public Boolean getChangeJob() {
        return changeJob;
    }

    public void setChangeJob(Boolean changeJob) {
        this.changeJob = changeJob;
    }

    public Boolean getHelpSchool() {
        return helpSchool;
    }

    public void setHelpSchool(Boolean helpSchool) {
        this.helpSchool = helpSchool;
    }

    public Boolean getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(Boolean socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public Boolean getRelocation() {
        return relocation;
    }

    public void setRelocation(Boolean relocation) {
        this.relocation = relocation;
    }

    public Boolean getSpecialHelp() {
        return specialHelp;
    }

    public void setSpecialHelp(Boolean specialHelp) {
        this.specialHelp = specialHelp;
    }

    public Boolean getOfIll() {
        return ofIll;
    }

    public void setOfIll(Boolean ofIll) {
        this.ofIll = ofIll;
    }

    public Boolean getOfSchool() {
        return ofSchool;
    }

    public void setOfSchool(Boolean ofSchool) {
        this.ofSchool = ofSchool;
    }

    public Boolean getOfDisaster() {
        return ofDisaster;
    }

    public void setOfDisaster(Boolean ofDisaster) {
        this.ofDisaster = ofDisaster;
    }

    public Boolean getOfDisability() {
        return ofDisability;
    }

    public void setOfDisability(Boolean ofDisability) {
        this.ofDisability = ofDisability;
    }

    public Boolean getLackSoil() {
        return lackSoil;
    }

    public void setLackSoil(Boolean lackSoil) {
        this.lackSoil = lackSoil;
    }

    public Boolean getLackWater() {
        return lackWater;
    }

    public void setLackWater(Boolean lackWater) {
        this.lackWater = lackWater;
    }

    public Boolean getLackTechnology() {
        return lackTechnology;
    }

    public void setLackTechnology(Boolean lackTechnology) {
        this.lackTechnology = lackTechnology;
    }

    public Boolean getLackLabor() {
        return lackLabor;
    }

    public void setLackLabor(Boolean lackLabor) {
        this.lackLabor = lackLabor;
    }

    public Boolean getLackMoney() {
        return lackMoney;
    }

    public void setLackMoney(Boolean lackMoney) {
        this.lackMoney = lackMoney;
    }

    public Boolean getLackTraffic() {
        return lackTraffic;
    }

    public void setLackTraffic(Boolean lackTraffic) {
        this.lackTraffic = lackTraffic;
    }

    public Boolean getLackNatureDevelopment() {
        return lackNatureDevelopment;
    }

    public void setLackNatureDevelopment(Boolean lackNatureDevelopment) {
        this.lackNatureDevelopment = lackNatureDevelopment;
    }

    public Boolean getOthers() {
        return others;
    }

    public void setOthers(Boolean others) {
        this.others = others;
    }
}
