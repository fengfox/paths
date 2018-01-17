package com.ftao.paths.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Integer id;
    //汽车名称
    private String name;
    //是否有限号
    private boolean isLimited;
    //尾号

    private String License;
    private Integer limitedDay;

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }

    public Integer getLimitedDay() {
        return limitedDay;
    }

    public void setLimitedDay(Integer limitedDay) {
        this.limitedDay = limitedDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLimited() {
        return isLimited;
    }

    public void setLimited(boolean limited) {
        isLimited = limited;
    }


    public Car(){}
}
