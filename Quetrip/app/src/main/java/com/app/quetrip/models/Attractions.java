package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class Attractions {
    int id = 0;
    int manager_id = 0;
    String name = "";
    String language = "";
    String address = "";
    String country = "";
    String city = "";
    String postal = "";
    LatLng latLng = null;
    String picture_url = "";
    double distance = 0.0d;
    double walking = 0.0d;
    String difficulty = "";
    String type = "";
    String departure_time = "";
    float total_time = 0.0f;
    double price = 0.0d;
    String unit = "";
    String description = "";
    String extension = "";
    float ratings = 0.0f;
    int reviews = 0;
    long since = 0;
    String status = "";

    public Attractions(){}

    public Attractions(int id, int manager_id, String name, String language, String address, String country, String city, String postal, double lat, double lng, String picture_url,
                       double distance, double walking, String difficulty, String type, String departure_time, float total_time, double price, String unit, String extension,
                       String description, int reviews, float ratings){
        this.id = id;
        this.manager_id = manager_id;
        this.name = name;
        this.language = language;
        this.address = address;
        this.country = country;
        this.city = city;
        this.postal = postal;
        this.latLng = new LatLng(lat, lng);
        this.picture_url = picture_url;
        this.distance = distance;
        this.walking = walking;
        this.difficulty = difficulty;
        this.type = type;
        this.departure_time = departure_time;
        this.total_time = total_time;
        this.price = price;
        this.unit = unit;
        this.description = description;
        this.extension = extension;
        this.reviews = reviews;
        this.ratings = ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public float getRatings() {
        return ratings;
    }

    public int getReviews() {
        return reviews;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setWalking(double walking) {
        this.walking = walking;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotal_time(float total_time) {
        this.total_time = total_time;
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

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
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

    public double getDistance() {
        return distance;
    }

    public double getWalking() {
        return walking;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

    public float getTotal_time() {
        return total_time;
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
