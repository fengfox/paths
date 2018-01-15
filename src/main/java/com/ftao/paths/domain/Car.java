package com.ftao.paths.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private Integer tailLicense;

    public Integer getId() {
        return id;
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

    public Integer getTailLicense() {
        return tailLicense;
    }

    public void setTailLicense(Integer tailLicense) {
        this.tailLicense = tailLicense;
    }
    public Car(){}
}
