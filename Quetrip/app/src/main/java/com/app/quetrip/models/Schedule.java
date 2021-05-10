package com.app.quetrip.models;

import java.util.ArrayList;

public class Schedule {
    int id = 0;
    int member_id = 0;
    String title = "";
    long start_timestamp = 0;
    int s_yy = 0;
    int s_MM = 0;
    int s_dd = 0;
    long end_timestamp = 0;
    int e_yy = 0;
    int e_MM = 0;
    int e_dd = 0;
    int days = 0;
    int adults = 0;
    int seniors = 0;
    int children = 0;
    int infants = 0;
    String status = "";
    ArrayList<DailyPlan> dailyPlans = new ArrayList<>();

    public Schedule(){}

    public Schedule(int id, int member_id, String title, long start_timestamp, int s_yy, int s_MM, int s_dd,
                    long end_timestamp, int e_yy, int e_MM, int e_dd, int days, int adults, int seniors, int children, int infants,
                    String status, ArrayList<DailyPlan> dailyPlans) {
        this.id = id;
        this.member_id = member_id;
        this.title = title;
        this.start_timestamp = start_timestamp;
        this.s_yy = s_yy;
        this.s_MM = s_MM;
        this.s_dd = s_dd;
        this.end_timestamp = end_timestamp;
        this.e_yy = e_yy;
        this.e_MM = e_MM;
        this.e_dd = e_dd;
        this.days = days;
        this.adults = adults;
        this.seniors = seniors;
        this.children = children;
        this.infants = infants;
        this.status = status;
        this.dailyPlans.clear();
        this.dailyPlans.addAll(dailyPlans);
    }

    public void setDailyPlans(ArrayList<DailyPlan> dailyPlans) {
        this.dailyPlans.clear();
        this.dailyPlans.addAll(dailyPlans);
    }

    public ArrayList<DailyPlan> getDailyPlans() {
        return dailyPlans;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setSeniors(int seniors) {
        this.seniors = seniors;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public int getAdults() {
        return adults;
    }

    public int getSeniors() {
        return seniors;
    }

    public int getChildren() {
        return children;
    }

    public int getInfants() {
        return infants;
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

    public void setE_yy(int e_yy) {
        this.e_yy = e_yy;
    }

    public void setE_MM(int e_MM) {
        this.e_MM = e_MM;
    }

    public void setE_dd(int e_dd) {
        this.e_dd = e_dd;
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

    public int getE_yy() {
        return e_yy;
    }

    public int getE_MM() {
        return e_MM;
    }

    public int getE_dd() {
        return e_dd;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart_timestamp(long start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public void setEnd_timestamp(long end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public int getMember_id() {
        return member_id;
    }

    public String getTitle() {
        return title;
    }

    public long getStart_timestamp() {
        return start_timestamp;
    }

    public long getEnd_timestamp() {
        return end_timestamp;
    }

    public int getDays() {
        return days;
    }
}
