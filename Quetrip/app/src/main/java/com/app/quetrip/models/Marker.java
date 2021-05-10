package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Marker {
    int id = 0;
    double x = 0.0d;
    double y = 0.0d;
    String title = "";
    LatLng latLng = null;
    String picture_url = "";
    int imageRes = 0;

    public Marker(int idx, double coordX, double coordY, String ttl, int imageRes){
        this.id = idx;
        this.x = coordX;
        this.y = coordY;
        this.title = ttl;
        this.imageRes = imageRes;
    }

    public Marker(){}

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getTitle() {
        return title;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
