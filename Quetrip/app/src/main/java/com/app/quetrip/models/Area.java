package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Area {
    int id = 0;
    String city = "";
    String country = "";
    LatLng latLng = null;

    public Area(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
