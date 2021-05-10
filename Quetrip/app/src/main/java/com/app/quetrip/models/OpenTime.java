package com.app.quetrip.models;

public class OpenTime {
    int id = 0;
    int s_hour = 0;
    int s_minute = 0;
    int e_hour = 0;
    int e_minute = 0;

    public OpenTime(int id, int s_hour, int s_minute, int e_hour, int e_minute) {
        this.id = id;
        this.s_hour = s_hour;
        this.s_minute = s_minute;
        this.e_hour = e_hour;
        this.e_minute = e_minute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getS_hour() {
        return s_hour;
    }

    public void setS_hour(int s_hour) {
        this.s_hour = s_hour;
    }

    public int getS_minute() {
        return s_minute;
    }

    public void setS_minute(int s_minute) {
        this.s_minute = s_minute;
    }

    public int getE_hour() {
        return e_hour;
    }

    public void setE_hour(int e_hour) {
        this.e_hour = e_hour;
    }

    public int getE_minute() {
        return e_minute;
    }

    public void setE_minute(int e_minute) {
        this.e_minute = e_minute;
    }
}
