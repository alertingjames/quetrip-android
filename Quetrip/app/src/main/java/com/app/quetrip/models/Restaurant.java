package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Restaurant {
    int id = 0;
    int manager_id = 0;
    String name = "";
    String language = "";
    String email = "";
    String phone_number = "";
    String address = "";
    String country = "";
    String city = "";
    String postal = "";
    LatLng latLng = null;
    double distance = 0.0d;
    String picture_url = "";
    float min_price = 0.0f;
    float max_price = 0.0f;
    String unit = "";
    String cuisines = "";
    String meals = "";
    String hours = "";
    String website = "";
    String description = "";
    String menus = "";
    float ratings = 0.0f;
    int reviews = 0;
    long since = 0;
    String status = "";
    ArrayList<RestaurantMenu> restaurantMenuArrayList = new ArrayList<>();

    public Restaurant(){}

    public Restaurant(int id, int manager_id, String name, String language, String email, String phone_number, String address, String country, String city, String postal,
                      double lat, double lng, double distance, String picture_url, float min_price, float max_price, String unit, String cuisines, String meals,
                      String hours, String website, String description, int reviews, float ratings, String status){
        this.id = id;
        this.manager_id = manager_id;
        this.name = name;
        this.language = language;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.country = country;
        this.city = city;
        this.postal = postal;
        this.latLng = new LatLng(lat, lng);
        this.distance = distance;
        this.picture_url = picture_url;
        this.min_price = min_price;
        this.max_price = max_price;
        this.unit = unit;
        this.cuisines = cuisines;
        this.meals = meals;
        this.hours = hours;
        this.website = website;
        this.description = description;
        this.ratings = ratings;
        this.reviews = reviews;
        this.status = status;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public void setRestaurantMenuArrayList(ArrayList<RestaurantMenu> restaurantMenuArrayList) {
        this.restaurantMenuArrayList.clear();
        this.restaurantMenuArrayList.addAll(restaurantMenuArrayList);
    }

    public ArrayList<RestaurantMenu> getRestaurantMenuArrayList() {
        return restaurantMenuArrayList;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setMin_price(float min_price) {
        this.min_price = min_price;
    }

    public void setMax_price(float max_price) {
        this.max_price = max_price;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public float getMin_price() {
        return min_price;
    }

    public float getMax_price() {
        return max_price;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getMeals() {
        return meals;
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

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public int getManager_id() {
        return manager_id;
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

    public String getPicture_url() {
        return picture_url;
    }

    public String getHours() {
        return hours;
    }

    public String getWebsite() {
        return website;
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




































