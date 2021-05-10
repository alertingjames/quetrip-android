package com.app.quetrip.models;

import java.util.ArrayList;

public class DailyPlan {
    int id = 0;
    int member_id = 0;
    int schedule_id = 0;
    long date_timestamp = 0;
    int yy = 0;
    int MM = 0;
    int dd = 0;
    long start_timestamp = 0;
    int s_yy = 0;
    int s_MM = 0;
    int s_dd = 0;
    long end_timestamp = 0;
    int e_yy = 0;
    int e_MM = 0;
    int e_dd = 0;
    String city_name = "";
    String city_picture_url = "";
    int city_imageRes = 0;
    int city_days = 0;
    String status = "";

    ArrayList<Attractions> attractionsArrayList = new ArrayList<>();
    ArrayList<HotelRoom> hotelRoomArrayList = new ArrayList<>();
    ArrayList<Hotel> hotelArrayList = new ArrayList<>();
    ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();
    ArrayList<Guide> guideArrayList = new ArrayList<>();
    ArrayList<CarRent> carRentArrayList = new ArrayList<>();
    ArrayList<Equipment> equipmentArrayList = new ArrayList<>();
    ArrayList<Transfer> transferArrayList = new ArrayList<>();


    public DailyPlan(){}

    public ArrayList<Hotel> getHotelArrayList() {
        return hotelArrayList;
    }

    public void setHotelArrayList(ArrayList<Hotel> hotelArrayList) {
        this.hotelArrayList.clear();
        this.hotelArrayList.addAll(hotelArrayList);
    }

    public ArrayList<CarRent> getCarRentArrayList() {
        return carRentArrayList;
    }

    public void setCarRentArrayList(ArrayList<CarRent> carRentArrayList) {
        this.carRentArrayList.clear();
        this.carRentArrayList.addAll(carRentArrayList);
    }

    public ArrayList<Equipment> getEquipmentArrayList() {
        return equipmentArrayList;
    }

    public void setEquipmentArrayList(ArrayList<Equipment> equipmentArrayList) {
        this.equipmentArrayList.clear();
        this.equipmentArrayList.addAll(equipmentArrayList);
    }

    public ArrayList<Transfer> getTransferArrayList() {
        return transferArrayList;
    }

    public void setTransferArrayList(ArrayList<Transfer> transferArrayList) {
        this.transferArrayList.clear();
        this.transferArrayList.addAll(transferArrayList);
    }

    public ArrayList<Guide> getGuideArrayList() {
        return guideArrayList;
    }

    public void setGuideArrayList(ArrayList<Guide> guideArrayList) {
        this.guideArrayList.clear();
        this.guideArrayList.addAll(guideArrayList);
    }

    public void setRestaurantArrayList(ArrayList<Restaurant> restaurantArrayList) {
        this.restaurantArrayList.clear();
        this.restaurantArrayList.addAll(restaurantArrayList);
    }

    public ArrayList<Restaurant> getRestaurantArrayList() {
        return restaurantArrayList;
    }

    public void setHotelRoomArrayList(ArrayList<HotelRoom> hotelRoomArrayList) {
        this.hotelRoomArrayList.clear();
        this.hotelRoomArrayList.addAll(hotelRoomArrayList);
    }

    public ArrayList<HotelRoom> getHotelRoomArrayList() {
        return hotelRoomArrayList;
    }

    public void setAttractionsArrayList(ArrayList<Attractions> attractionsArrayList) {
        this.attractionsArrayList.clear();
        this.attractionsArrayList.addAll(attractionsArrayList);
    }

    public ArrayList<Attractions> getAttractionsArrayList() {
        return attractionsArrayList;
    }

    public void setCity_days(int days) {
        this.city_days = days;
    }

    public int getCity_days() {
        return city_days;
    }

    public void setStart_timestamp(long start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public void setS_yy(int s_yy) {
        this.s_yy = s_yy;
    }

    public void setS_MM(int s_MM) {
        this.s_MM = s_MM;
    }

    public void setS_dd(int s_dd) {
        this.s_dd = s_dd;
    }

    public void setEnd_timestamp(long end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public void setE_yy(int e_yy) {
        this.e_yy = e_yy;
    }

    public void setE_MM(int e_MM) {
        this.e_MM = e_MM;
    }

    public void setE_dd(int e_dd) {
        this.e_dd = e_dd;
    }

    public long getStart_timestamp() {
        return start_timestamp;
    }

    public int getS_yy() {
        return s_yy;
    }

    public int getS_MM() {
        return s_MM;
    }

    public int getS_dd() {
        return s_dd;
    }

    public long getEnd_timestamp() {
        return end_timestamp;
    }

    public int getE_yy() {
        return e_yy;
    }

    public int getE_MM() {
        return e_MM;
    }

    public int getE_dd() {
        return e_dd;
    }

    public void setCity_imageRes(int city_imageRes) {
        this.city_imageRes = city_imageRes;
    }

    public int getCity_imageRes() {
        return city_imageRes;
    }

    public void setCity_picture_url(String city_picture_url) {
        this.city_picture_url = city_picture_url;
    }

    public String getCity_picture_url() {
        return city_picture_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public void setDate_timestamp(long date_timestamp) {
        this.date_timestamp = date_timestamp;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public void setMM(int MM) {
        this.MM = MM;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getMember_id() {
        return member_id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public long getDate_timestamp() {
        return date_timestamp;
    }

    public int getYy() {
        return yy;
    }

    public int getMM() {
        return MM;
    }

    public int getDd() {
        return dd;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getStatus() {
        return status;
    }
}
