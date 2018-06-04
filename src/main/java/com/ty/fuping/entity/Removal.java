package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by ty on 2017/12/14.
 * 易地搬迁记录
 */
@Entity
public class Removal {
    @Id
    @GeneratedValue
    private Integer removalId;
    //记录所属家庭
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "familyId")
    private Family family;
    //是否搬迁户
    private Boolean isRelocatedFamily;
    //搬迁方式
    private String relocatedWay;
    //迁出区
    private String moveAwayDistrict;
    //安置方式
    private String arrangeWay;
    //安置地
    private String arrangePlace;
    //搬迁可能存在的困难
    private String problemsInRelocate;

    public Integer getRemovalId() {
        return removalId;
    }

    public void setRemovalId(Integer removalId) {
        this.removalId = removalId;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Boolean getRelocatedFamily() {
        return isRelocatedFamily;
    }

    public void setRelocatedFamily(Boolean relocatedFamily) {
        isRelocatedFamily = relocatedFamily;
    }

    public String getRelocatedWay() {
        return relocatedWay;
    }

    public void setRelocatedWay(String relocatedWay) {
        this.relocatedWay = relocatedWay;
    }

    public String getMoveAwayDistrict() {
        return moveAwayDistrict;
    }

    public void setMoveAwayDistrict(String moveAwayDistrict) {
        this.moveAwayDistrict = moveAwayDistrict;
    }

    public String getArrangeWay() {
        return arrangeWay;
    }

    public void setArrangeWay(String arrangeWay) {
        this.arrangeWay = arrangeWay;
    }

    public String getArrangePlace() {
        return arrangePlace;
    }

    public void setArrangePlace(String arrangePlace) {
        this.arrangePlace = arrangePlace;
    }

    public String getProblemsInRelocate() {
        return problemsInRelocate;
    }

    public void setProblemsInRelocate(String problemsInRelocate) {
        this.problemsInRelocate = problemsInRelocate;
    }
}
