package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class QuetripAddress {
    int id = 0;
    int userId = 0;
    String address = "";
    LatLng latLng = null;

    public QuetripAddress(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
