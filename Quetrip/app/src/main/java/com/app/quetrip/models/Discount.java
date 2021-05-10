package com.app.quetrip.models;

public class Discount {
    int id = 0;
    int tour_id = 0;
    String picture_url = "";

    public Discount(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public int getId() {
        return id;
    }

    public int getTour_id() {
        return tour_id;
    }

    public String getPicture_url() {
        return picture_url;
    }
}
