package com.app.quetrip.models;

public class MySchedule {
    int id = 0;
    int member_id = 0;
    int tour_id = 0;
    String picture_url = "";
    String subject = "";
    long start_time = 0;
    long end_time = 0;
    String description = "";
    String status = "";

    public MySchedule(){}

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getMember_id() {
        return member_id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public String getSubject() {
        return subject;
    }

    public long getStart_time() {
        return start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
