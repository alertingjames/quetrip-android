package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Dest {
    int id = 0;
    String title = "";
    String address = "";
    String picture_url = "";
    LatLng latLng = null;

    public Dest(){}

    public Dest(String title, String address, String picture_url, LatLng latLng){
        this.title = title;
        this.address = address;
        this.picture_url = picture_url;
        this.latLng = latLng;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
