package com.ftao.paths.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="Car_Driver",joinColumns = {
            @JoinColumn(name="driver_id")},inverseJoinColumns = {
            @JoinColumn(name="car_id")})
    private Set<Car> cars;

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
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

    public Driver() {
        super();
    }
}
