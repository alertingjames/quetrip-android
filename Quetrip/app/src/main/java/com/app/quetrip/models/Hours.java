package com.app.quetrip.models;

import java.util.ArrayList;

public class Hours {
    int id = 0;
    int restaurant_id = 0;
    ArrayList<Integer> weekdays = new ArrayList<>();
    ArrayList<OpenTime> openTimes = new ArrayList<>();
    String status = "";

    public Hours(int id, int restaurant_id, ArrayList<Integer> weekdays, ArrayList<OpenTime> openTimes, String status) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.weekdays.addAll(weekdays);
        this.openTimes.addAll(openTimes);
        this.status = status;
    }

    public Hours(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public ArrayList<Integer> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(ArrayList<Integer> weekdays) {
        this.weekdays = weekdays;
    }

    public ArrayList<OpenTime> getOpenTimes() {
        return openTimes;
    }

    public void setOpenTimes(ArrayList<OpenTime> openTimes) {
        this.openTimes = openTimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
