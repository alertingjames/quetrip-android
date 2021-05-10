package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Hotel {
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
    String picture_url = "";
    double price = 0.0d;
    String unit = "";
    String services = ""; // Free WiFi, Free Airport Shuttle, Air Conditioning, Breakfast Included, HomeRestaurantFrag, Pet Friendly, Spa, Pool, Free Parking...
    String description = "";
    float ratings = 0.0f;
    int reviews = 0;
    long since = 0;
    String status = "";

    ArrayList<HotelRoom> roomArrayList = new ArrayList<>();

    public Hotel(){}

    public Hotel(int id, int manager_id, String name, String language, String phone_number, String address, String country, String city, String postal, double lat, double lng,
                 double distance, String picture_url, double price, String unit, String services, String description, int reviews, float ratings){

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
        this.picture_url = picture_url;
        this.price = price;
        this.unit = unit;
        this.services = services;
        this.description = description;
        this.reviews = reviews;
        this.ratings = ratings;

    }

    public ArrayList<HotelRoom> getRoomArrayList() {
        return roomArrayList;
    }

    public void setRoomArrayList(ArrayList<HotelRoom> roomArrayList) {
        this.roomArrayList.clear();
        this.roomArrayList.addAll(roomArrayList);
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
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

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setServices(String services) {
        this.services = services;
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

    public double getDistance() {
        return distance;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public double getPrice() {
        return price;
    }

    public String getServices() {
        return services;
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































