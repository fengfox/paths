package com.ftao.paths.domain;

import java.util.List;

public class Month {
    private List<Day> days;

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
    private Month(){};

}
