package com.ty.fuping.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ty on 2017/12/23.
 * 走访记录
 */
@Entity
public class VisitRecord {
    @Id
    @GeneratedValue
    private Integer visitRecordId;
    //家庭
    //@JsonIgnore
    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "familyId")
    private Family family;
    //走访时间
    private Date visitTime;
    //走访主题
    private String visitTheme;
    //寻访人
    private String visitor;
    //寻访内容
    private String visitContent;
    //寻访照片路径
    private String photoPath;

    public Integer getVisitRecordId() {
        return visitRecordId;
    }

    public void setVisitRecordId(Integer visitRecordId) {
        this.visitRecordId = visitRecordId;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTheme() {
        return visitTheme;
    }

    public void setVisitTheme(String visitTheme) {
        this.visitTheme = visitTheme;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getVisitContent() {
        return visitContent;
    }

    public void setVisitContent(String visitContent) {
        this.visitContent = visitContent;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
