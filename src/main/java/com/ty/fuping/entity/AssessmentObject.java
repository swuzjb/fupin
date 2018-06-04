package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ty.fuping.entity.AssistanceMeasure.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ty on 2017/11/1.
 * 考核对象
 */
@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AssessmentObject {

    @Id
    @GeneratedValue
    //考核对象Id
    private Integer assessmentObjectId;

    //考核对象姓名
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "town_id")
    //考核对象地址
    private Town town;
    //饮水安全
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "assessmentObject")
    List<DrinkingSafety> drinkingSafeties;
    //金融扶贫
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "assessmentObject")
    List<FinancialAssistance> financialAssistances;
    //危房改造
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "assessmentObject")
    List<HouseRenovation> houseRenovations;
    //劳动力培训
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "assessmentObject")
    List<LaborTraining> laborTrainings;
    //富民产业
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "assessmentObject")
    List<ProsperousIndustry> prosperousIndustries;
    //社会援助
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "assessmentObject")
    List<SocialAssistance> socialAssistances;
    //专项扶贫
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "assessmentObject")
    List<SpecialPovertyAlleviation> specialPovertyAlleviations;

    //所属打分
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.REMOVE}, mappedBy = "assessmentObject")
    List<Point> pointList;

    //性别
    private String sex;

    //政治面貌
    private String zhegnzhimianmao;

    //电话号码
    private String phoneNumber;

    //账户
    private String account;

    //密码
    private String password;

    //单位
    private String danwei;

    //单位隶属关系
    private String relationofdanwei;

    //单位地址
    private String danweiaddress;

    //职务
    private String zhiwu;

    public Integer getAssessmentObjectId() {
        return assessmentObjectId;
    }

    public void setAssessmentObjectId(Integer assessmentObjectId) {
        this.assessmentObjectId = assessmentObjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
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

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZhegnzhimianmao() {
        return zhegnzhimianmao;
    }

    public void setZhegnzhimianmao(String zhegnzhimianmao) {
        this.zhegnzhimianmao = zhegnzhimianmao;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getRelationofdanwei() {
        return relationofdanwei;
    }

    public void setRelationofdanwei(String relationofdanwei) {
        this.relationofdanwei = relationofdanwei;
    }

    public String getDanweiaddress() {
        return danweiaddress;
    }

    public void setDanweiaddress(String danweiaddress) {
        this.danweiaddress = danweiaddress;
    }

    public String getZhiwu() {
        return zhiwu;
    }

    public void setZhiwu(String zhiwu) {
        this.zhiwu = zhiwu;
    }
}
