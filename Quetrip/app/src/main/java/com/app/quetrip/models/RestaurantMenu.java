package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class RestaurantMenu {
    int id = 0;
    int restaurant_id = 0;
    String name = "";
    String language = "";
    String picture_url = "";
    float price = 0.0f;
    String unit = "";
    String size = "";  // Small, medium, large or supersize&quest
    int quantity = 0;
    String category = "";
    String description = "";
    String email = "";
    String phone_number = "";
    String address = "";
    String country = "";
    String city = "";
    String postal = "";
    LatLng latLng = null;
    double distance = 0.0d;
    String cuisines = "";
    String meals = "";
    String hours = "";
    String website = "";
    float ratings = 0.0f;
    int reviews = 0;
    long since = 0;
    String status = "";
    Restaurant restaurant = null;

    public RestaurantMenu(){}

    public RestaurantMenu(int id, String name, String picture_url, float price, String unit, String size, int quantity, String category, String description, long since, String status, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.picture_url = picture_url;
        this.price = price;
        this.unit = unit;
        this.size = size;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
        this.since = since;
        this.status = status;
        this.restaurant = restaurant;
    }

    public RestaurantMenu(int id, Restaurant restaurant, String name, String picture_url, String category, float price, String unit, String size, int quantity, String description){
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.category = category;
        this.price = price;
        this.unit = unit;
        this.picture_url = picture_url;
        this.size = size;
        this.quantity = quantity;
        this.description = description;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getEmail() {
        return email;
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

    public String getCuisines() {
        return cuisines;
    }

    public String getMeals() {
        return meals;
    }

    public String getHours() {
        return hours;
    }

    public String getWebsite() {
        return website;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public float getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
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







































