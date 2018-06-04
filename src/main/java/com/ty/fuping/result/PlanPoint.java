package com.ty.fuping.result;

import java.sql.Date;

/**
 * Created by ty on 2017/11/2.
 */
public class PlanPoint {
    //此人此计划得分
    private Double pont;
    //考核计划_得分Id
    private Integer PlanPointId;
    //考核计划名称
    private String planTitle;
    //考核计划开始时间
    private Date start;
    //考核计划结束时间
    private Date end;
    //考核计划内容
    private String content;
    //考核计划状态
    private boolean isOn;

}
