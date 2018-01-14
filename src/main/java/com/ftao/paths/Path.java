package com.ftao.paths;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Path {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer length;
    private Integer type;
    private Integer ran;
    private String route;
    private String remark;
    private Integer spend;

    public Integer getSpend() {
        return spend;
    }

    public void setSpend(Integer spend) {
        this.spend = spend;
    }

    public Integer getRan() {
        return ran;
    }

    public void setRan(Integer ran) {
        this.ran = ran;
    }

    public Path(){}

    public Integer getId() {
        return id;
    }



    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
