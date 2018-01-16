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
    private Integer tailLicense;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="Car_Driver",joinColumns = {
            @JoinColumn(name="car_id")},inverseJoinColumns = {
            @JoinColumn(name="driver_id")})
    private Set<Driver> drivers;

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
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

    public Integer getTailLicense() {
        return tailLicense;
    }

    public void setTailLicense(Integer tailLicense) {
        this.tailLicense = tailLicense;
    }
    public Car(){}
}
