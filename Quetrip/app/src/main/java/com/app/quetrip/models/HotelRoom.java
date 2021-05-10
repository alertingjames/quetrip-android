package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class HotelRoom {
    int id = 0;
    int hotel_id = 0;
    String hotel_name = "";
    String name = "";
    String language = "";
    String picture_url = "";
    double price = 0.0d;
    String unit = "";
    String services = ""; // Free WiFi, Free Airport Shuttle, Air Conditioning, Breakfast Included, HomeRestaurantFrag, Pet Friendly, Spa, Pool, Free Parking...
    String views = ""; // City View, Partial Ocean View, Sea View...
    String bed = "";  // 2 Tween Beds, 1 Double Beds, 1 King Bed, 1 King Bed OR 2 Twin Beds, 2 Double Beds, 1 Queen Bed
    int sleeps = 0;
    int adults = 0;
    int children = 0;
    String refundable = "";   // Non-refundable, Free cancelation
    String description = "";
    String phone_number = "";
    String address = "";
    String country = "";
    String city = "";
    String postal = "";
    LatLng latLng = null;
    double distance = 0.0d;
    long since = 0;
    String status = "";
    Hotel hotel = null;

    public HotelRoom(){}

    public HotelRoom(int id, String name, String picture_url, double price, String unit, String views, String bed, int sleeps, int adults, int children, String refundable, String description, long since, String status, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.picture_url = picture_url;
        this.price = price;
        this.unit = unit;
        this.views = views;
        this.bed = bed;
        this.sleeps = sleeps;
        this.adults = adults;
        this.children = children;
        this.refundable = refundable;
        this.description = description;
        this.since = since;
        this.status = status;
        this.hotel = hotel;
    }

    public HotelRoom(int id, int hotel_id, String hotel_name, String name, String language, String phone_number, String address, String country, String city, String postal, double lat, double lng,
                     double distance, String picture_url, double price, String unit, String services, String bed, String views, String description, int sleeps, int adults,
                     int children, String refundable){

        this.id = id;
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
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
        this.bed = bed;
        this.views = views;
        this.description = description;
        this.sleeps = sleeps;
        this.adults = adults;
        this.children = children;
        this.refundable = refundable;

    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
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

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public void setSleeps(int sleeps) {
        this.sleeps = sleeps;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setRefundable(String refundable) {
        this.refundable = refundable;
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

    public int getHotel_id() {
        return hotel_id;
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

    public String getUnit() {
        return unit;
    }

    public String getServices() {
        return services;
    }

    public String getViews() {
        return views;
    }

    public String getBed() {
        return bed;
    }

    public int getSleeps() {
        return sleeps;
    }

    public int getAdults() {
        return adults;
    }

    public int getChildren() {
        return children;
    }

    public String getRefundable() {
        return refundable;
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































