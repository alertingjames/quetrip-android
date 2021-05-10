package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Service {
    int id = 0;
    int manager_id = 0;
    String category = "";
    String name = "";
    String subcategory = "";
    String language = "";
    String phone_number = "";
    String address = "";
    String country = "";
    String city = "";
    String postal = "";
    LatLng latLng = null;
    String picture_url = "";
    String video_url = "";
    double price = 0.0d;
    String description = "";
    long since = 0;
    String status = "";

    public Service(int id, String category, String name, String description, String picture_url, String lang){
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.picture_url = picture_url;
        this.language = lang;
    }

    public Service(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSince(long since) {
        this.since = since;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getLanguage() {
        return language;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public long getSince() {
        return since;
    }

    public String getStatus() {
        return status;
    }
}
