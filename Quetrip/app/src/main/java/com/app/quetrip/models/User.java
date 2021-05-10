package com.app.quetrip.models;

import com.google.android.gms.maps.model.LatLng;

public class User {
    int idx = 0;
    String first_name = "";
    String last_name = "";
    String name = "";
    String email = "";
    String password = "";
    String phone_number = "";
    String picture_url = "";
    String address = "";
    LatLng latLng = null;
    String registered_time = "";
    String auth_status = "";
    String status = "";

    public User(){

    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setAuth_status(String auth_status) {
        this.auth_status = auth_status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuth_status() {
        return auth_status;
    }

    public String getStatus() {
        return status;
    }

    public void setRegistered_time(String registered_time) {
        this.registered_time = registered_time;
    }

    public String getRegistered_time() {
        return registered_time;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getIdx() {
        return idx;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
