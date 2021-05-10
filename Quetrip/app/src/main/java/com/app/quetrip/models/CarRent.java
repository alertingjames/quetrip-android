package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class CarRent {
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
    String pickups = "";
    String dropoffs = "";
    String picture_url = "";
    float weekly_price = 0.0f;
    float total_price = 0.0f;
    String unit = "";
    String services = "";   // Air conditioning, Automatic transmission, Unlimited mileage, Free cancellation, Reserve now, Pay later...
    String similar_properties = "";
    int seaters = 0;
    String color = "";
    String refundable = "";
    String description = "";
    int likes = 0;
    float ratings = 0.0f;
    int reviews = 0;
    long since = 0;
    String status = "";

    public CarRent(int id, int manager_id, String name, String language, String phone_number, String address, String country, String city, String postal,
                   double lat, double lng, double distance, String pickups, String dropoffs, String picture_url, float weekly_price, float total_price,
                   String unit, String services, String similar_properties, int seaters, String color, String refundable, String description, int likes, float ratings, int reviews,
                   long since, String status) {
        this.id = id;
        this.manager_id = manager_id;
        this.name = name;
        this.language = language;
        this.phone_number = phone_number;
        this.address = address;
        this.country = country;
        this.city = city;
        this.postal = postal;
        this.latLng = new LatLng(lat, lng);
        this.distance = distance;
        this.pickups = pickups;
        this.dropoffs = dropoffs;
        this.picture_url = picture_url;
        this.weekly_price = weekly_price;
        this.total_price = total_price;
        this.unit = unit;
        this.services = services;
        this.similar_properties = similar_properties;
        this.seaters = seaters;
        this.color = color;
        this.refundable = refundable;
        this.description = description;
        this.likes = likes;
        this.ratings = ratings;
        this.reviews = reviews;
        this.since = since;
        this.status = status;
    }

    public CarRent(){}

    public String getRefundable() {
        return refundable;
    }

    public void setRefundable(String refundable) {
        this.refundable = refundable;
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

    public String getPickups() {
        return pickups;
    }

    public void setPickups(String pickups) {
        this.pickups = pickups;
    }

    public String getDropoffs() {
        return dropoffs;
    }

    public void setDropoffs(String dropoffs) {
        this.dropoffs = dropoffs;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public float getWeekly_price() {
        return weekly_price;
    }

    public void setWeekly_price(float weekly_price) {
        this.weekly_price = weekly_price;
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

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getSimilar_properties() {
        return similar_properties;
    }

    public void setSimilar_properties(String similar_properties) {
        this.similar_properties = similar_properties;
    }

    public int getSeaters() {
        return seaters;
    }

    public void setSeaters(int seaters) {
        this.seaters = seaters;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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




































