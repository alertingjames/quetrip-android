package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Transfer {
    int id = 0;
    int manager_id = 0;
    String name = "";
    String language = "";
    String phone_number = "";
    String address = "";
    String country = "";
    String city = "";
    String postal = "";
    LatLng latLng = null;
    double distance = 0.0d;
    String from_address = "";
    LatLng from_latLng = null;
    String to_address = "";
    LatLng to_latLng = null;
    String picture_url = "";
    float km_price = 0.0f;
    float total_price = 0.0f;
    String unit = "";
    float km_mins = 0;
    String services = "";   //
    String refundable = "";
    String description = "";
    float ratings = 0.0f;
    int reviews = 0;
    long since = 0;
    String status = "";

    public Transfer(int id, int manager_id, String name, String language, String phone_number, String address, String country, String city,
                    String postal, LatLng latLng, double distance, String from_address, LatLng from_latLng, String to_address, LatLng to_latLng,
                    String picture_url, float km_price, float total_price, String unit, float km_mins, String services, String refundable,
                    String description, float ratings, int reviews, long since, String status) {
        this.id = id;
        this.manager_id = manager_id;
        this.name = name;
        this.language = language;
        this.phone_number = phone_number;
        this.address = address;
        this.country = country;
        this.city = city;
        this.postal = postal;
        this.latLng = latLng;
        this.distance = distance;
        this.from_address = from_address;
        this.from_latLng = from_latLng;
        this.to_address = to_address;
        this.to_latLng = to_latLng;
        this.picture_url = picture_url;
        this.km_price = km_price;
        this.total_price = total_price;
        this.unit = unit;
        this.km_mins = km_mins;
        this.services = services;
        this.refundable = refundable;
        this.description = description;
        this.ratings = ratings;
        this.reviews = reviews;
        this.since = since;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getFrom_address() {
        return from_address;
    }

    public void setFrom_address(String from_address) {
        this.from_address = from_address;
    }

    public LatLng getFrom_latLng() {
        return from_latLng;
    }

    public void setFrom_latLng(LatLng from_latLng) {
        this.from_latLng = from_latLng;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

    public LatLng getTo_latLng() {
        return to_latLng;
    }

    public void setTo_latLng(LatLng to_latLng) {
        this.to_latLng = to_latLng;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public float getKm_price() {
        return km_price;
    }

    public void setKm_price(float km_price) {
        this.km_price = km_price;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getKm_mins() {
        return km_mins;
    }

    public void setKm_mins(float km_mins) {
        this.km_mins = km_mins;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getRefundable() {
        return refundable;
    }

    public void setRefundable(String refundable) {
        this.refundable = refundable;
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

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public long getSince() {
        return since;
    }

    public void setSince(long since) {
        this.since = since;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


































