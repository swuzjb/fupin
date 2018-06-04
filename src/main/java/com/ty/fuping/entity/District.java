package com.ty.fuping.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ty on 2017/11/7.
 */
@Entity
public class District {

    @Id
    @GeneratedValue
    private Integer districtId;

    //区名
    private String districtName;

    //所属镇
    @OneToMany(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch=FetchType.LAZY,mappedBy="district")
    private List<Town> towns;

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }
}
