package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by ty on 2017/12/12.
 * 家庭成员
 */
@Entity
public class FamilyMember {

    //家庭成员Id
    @Id
    @GeneratedValue
    private Integer fimilyMenberId;
    //家庭Id
    @JsonIgnore
    @ManyToOne(cascade = { CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "familyId")
    Family family;
    //成员姓名
    private String name;
    //成员性别(1为男，2为女)
    private Integer sex;
    //成员身份证号
    private String IDCard;
    //成员年龄
    private Integer age;
    //名族
    private String ethnicity;
    //与户主的关系
    private String relationsWithFamilyOwner;
    //文化程度
    private String educationDegree;
    //政治面貌
    private String politicsStatus;
    //在校生状况
    private String atSchoolStatus;
    //健康状况
    private String healthStatus;
    //务工时长
    private String workTime;
    //劳动能力
    private String workAbility;
    //务工状态
    private String workStatus;
    //帮扶方式
    private String wayOfHelp;
    //是否现役军人
    private Boolean isSoldierNow;
    //是否参加新型医疗合作社
    private Boolean joinMedicalCooperation;
    //是否参加城乡居民养老保险
    private Boolean joinEndowmentInsurance;
    //是否参加大病医疗保险
    private Boolean joinSicknessInsurance;
    //是否享受低保
    private Boolean hasMinimumLivingStandard;

    public Integer getFimilyMenberId() {
        return fimilyMenberId;
    }

    public void setFimilyMenberId(Integer fimilyMenberId) {
        this.fimilyMenberId = fimilyMenberId;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getRelationsWithFamilyOwner() {
        return relationsWithFamilyOwner;
    }

    public void setRelationsWithFamilyOwner(String relationsWithFamilyOwner) {
        this.relationsWithFamilyOwner = relationsWithFamilyOwner;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getAtSchoolStatus() {
        return atSchoolStatus;
    }

    public void setAtSchoolStatus(String atSchoolStatus) {
        this.atSchoolStatus = atSchoolStatus;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkAbility() {
        return workAbility;
    }

    public void setWorkAbility(String workAbility) {
        this.workAbility = workAbility;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWayOfHelp() {
        return wayOfHelp;
    }

    public void setWayOfHelp(String wayOfHelp) {
        this.wayOfHelp = wayOfHelp;
    }

    public Boolean getSoldierNow() {
        return isSoldierNow;
    }

    public void setSoldierNow(Boolean soldierNow) {
        isSoldierNow = soldierNow;
    }

    public Boolean getJoinMedicalCooperation() {
        return joinMedicalCooperation;
    }

    public void setJoinMedicalCooperation(Boolean joinMedicalCooperation) {
        this.joinMedicalCooperation = joinMedicalCooperation;
    }

    public Boolean getJoinEndowmentInsurance() {
        return joinEndowmentInsurance;
    }

    public void setJoinEndowmentInsurance(Boolean joinEndowmentInsurance) {
        this.joinEndowmentInsurance = joinEndowmentInsurance;
    }

    public Boolean getJoinSicknessInsurance() {
        return joinSicknessInsurance;
    }

    public void setJoinSicknessInsurance(Boolean joinSicknessInsurance) {
        this.joinSicknessInsurance = joinSicknessInsurance;
    }

    public Boolean getHasMinimumLivingStandard() {
        return hasMinimumLivingStandard;
    }

    public void setHasMinimumLivingStandard(Boolean hasMinimumLivingStandard) {
        this.hasMinimumLivingStandard = hasMinimumLivingStandard;
    }
}
