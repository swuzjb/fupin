package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by ty on 2017/11/1.
 * 考核者
 */
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Assessmenter {

    @Id
    @GeneratedValue
    //考核者Id
    private Integer assessmenterId;

    //考核者姓名
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "town_id")
    //考核者地址
    private Town town;

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

    public Integer getAssessmenterId() {
        return assessmenterId;
    }

    public void setAssessmenterId(Integer assessmenterId) {
        this.assessmenterId = assessmenterId;
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
