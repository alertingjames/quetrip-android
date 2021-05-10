package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Guide {
    int idx = 0;
    String name = "";
    String email = "";
    String phone_number = "";
    String picture_url = "";
    String address = "";
    String city = "";
    LatLng latLng = null;
    String language = "";
    float price = 0.0f;
    String unit = "";
    String description = "";
    float ratings = 0.0f;
    String status = "";

    public Guide(int idx, String name, String email, String phone_number, String picture_url, String address, String city, String language, double lat, double lng, float price, String unit, float ratings, String description) {
        this.idx = idx;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.picture_url = picture_url;
        this.address = address;
        this.city = city;
        this.language = language;
        this.latLng = new LatLng(lat, lng);
        this.price = price;
        this.unit = unit;
        this.ratings = ratings;
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
