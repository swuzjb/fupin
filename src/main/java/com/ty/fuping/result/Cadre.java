package com.ty.fuping.result;

import com.ty.fuping.entity.AssessmentObject;
import com.ty.fuping.entity.Assessmenter;
import com.ty.fuping.entity.Town;

/**
 * Created by ty on 2017/12/10.
 */
public class Cadre {

    //驻村干部姓名
    protected String name;

    //驻村干部地址
    private Town town;

    //驻村干部性别
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

    public Assessmenter parseAssessmenter(){
        Assessmenter assessmenter=new Assessmenter();
        assessmenter.setAccount(this.account);
        assessmenter.setDanwei(this.danwei);
        assessmenter.setDanweiaddress(this.danweiaddress);
        assessmenter.setName(this.name);
        assessmenter.setPassword(this.password);
        assessmenter.setPhoneNumber(this.phoneNumber);
        assessmenter.setRelationofdanwei(relationofdanwei);
        assessmenter.setSex(this.sex);
        assessmenter.setTown(this.town);
        assessmenter.setZhegnzhimianmao(this.zhegnzhimianmao);
        assessmenter.setZhiwu(this.zhiwu);
        return assessmenter;
    }

    public AssessmentObject parseAssessmentObject(){
        AssessmentObject assessmentObject=new AssessmentObject();
        assessmentObject.setAccount(this.account);
        assessmentObject.setDanwei(this.danwei);
        assessmentObject.setDanweiaddress(this.danweiaddress);
        assessmentObject.setName(this.name);
        assessmentObject.setPassword(this.password);
        assessmentObject.setPhoneNumber(this.phoneNumber);
        assessmentObject.setRelationofdanwei(relationofdanwei);
        assessmentObject.setSex(this.sex);
        assessmentObject.setTown(this.town);
        assessmentObject.setZhegnzhimianmao(this.zhegnzhimianmao);
        assessmentObject.setZhiwu(this.zhiwu);
        return assessmentObject;
    }
}
