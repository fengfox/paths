package com.ftao.paths.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
