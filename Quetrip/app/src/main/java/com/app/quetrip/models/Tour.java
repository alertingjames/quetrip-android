package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Tour {
    int id = 0;
    int service_id = 0;
    String name = "";
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
    float discount = 0.0f;
    String description = "";
    String highlights = "";
    String policy = "";
    float ratings = 0.0f;
    int reviews = 0;
    long since = 0;
    String status = "";

    public Tour(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
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

    public int getService_id() {
        return service_id;
    }

    public String getName() {
        return name;
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

    public float getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    public String getHighlights() {
        return highlights;
    }

    public String getPolicy() {
        return policy;
    }

    public float getRatings() {
        return ratings;
    }

    public int getReviews() {
        return reviews;
    }

    public long getSince() {
        return since;
    }

    public String getStatus() {
        return status;
    }
}
