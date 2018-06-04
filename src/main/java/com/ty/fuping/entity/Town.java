package com.ty.fuping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ty on 2017/11/7.
 */
@Entity
public class Town {

    @Id
    @GeneratedValue
    private Integer townId;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "districtId")
    //所属区
    private District district;

    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "town")
    private List<Village> villages;

    //镇名
    private String townName;

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Village> getVillages() {
        return villages;
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
