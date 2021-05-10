package com.app.quetrip.models;

public class TourWeekSchedule {
    int id = 0;
    int tour_id = 0;
    String week_name = "";
    long open_time = 0;
    int adults = 0;
    int seniors = 0;
    int children = 0;
    int infants = 0;
    int tourists = 0;
    String status = "";

    public TourWeekSchedule(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public void setWeek_name(String week_name) {
        this.week_name = week_name;
    }

    public void setOpen_time(long open_time) {
        this.open_time = open_time;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setSeniors(int seniors) {
        this.seniors = seniors;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public void setTourists(int tourists) {
        this.tourists = tourists;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public String getWeek_name() {
        return week_name;
    }

    public long getOpen_time() {
        return open_time;
    }

    public int getAdults() {
        return adults;
    }

    public int getSeniors() {
        return seniors;
    }

    public int getChildren() {
        return children;
    }

    public int getInfants() {
        return infants;
    }

    public int getTourists() {
        return tourists;
    }

    public String getStatus() {
        return status;
    }
}
