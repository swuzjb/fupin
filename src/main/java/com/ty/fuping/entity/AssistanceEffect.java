package com.ty.fuping.entity;

import javax.persistence.*;

/**
 * Created by ty on 2017/12/23.
 * 帮扶成效
 */
@Entity
public class AssistanceEffect {
    @Id
    @GeneratedValue
    private Integer assistanceEffectId;
    //家庭
    //@JsonIgnore
    @ManyToOne(cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "familyId")
    private Family family;
    //上报年份
    private String year;
    //帮扶季度
    private String quarter;
    //工资性收入(元)
    private Double wageIncome;
    //经营性收入(元)
    private Double operatingIncome;
    //财产性收入(元)
    private Double propertyIncome;
    //转移性收入(元)
    private Double transferIncome;

    public Integer getAssistanceEffectId() {
        return assistanceEffectId;
    }

    public void setAssistanceEffectId(Integer assistanceEffectId) {
        this.assistanceEffectId = assistanceEffectId;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public Double getWageIncome() {
        return wageIncome;
    }

    public void setWageIncome(Double wageIncome) {
        this.wageIncome = wageIncome;
    }

    public Double getOperatingIncome() {
        return operatingIncome;
    }

    public void setOperatingIncome(Double operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    public Double getPropertyIncome() {
        return propertyIncome;
    }

    public void setPropertyIncome(Double propertyIncome) {
        this.propertyIncome = propertyIncome;
    }

    public Double getTransferIncome() {
        return transferIncome;
    }

    public void setTransferIncome(Double transferIncome) {
        this.transferIncome = transferIncome;
    }
}
