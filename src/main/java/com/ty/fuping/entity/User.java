package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ty on 2018/1/19.
 * 用户、权限管理
 */
@Entity
public class User {
    //用户Id
    @Id
    @GeneratedValue
    private Integer userId;
    //用户名
    private String userName;
    //密码
    private String passWord;
    //姓名
    private String name;
    //用户类型
    private String userType;
    //移动电话
    private String mobilePhone;
    //固定电话
    private String telephone;
    //单位
    private String workUnit;
    //职位
    private String position;
    //区县
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "districtId")
    private District district;
    //乡镇
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "townId")
    private Town town;
    //备注
    private String remark;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
