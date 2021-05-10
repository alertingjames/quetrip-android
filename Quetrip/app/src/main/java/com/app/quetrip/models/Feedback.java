package com.app.quetrip.models;

public class Feedback {
    int id = 0;
    int member_id = 0;
    float ratings = 0.0f;
    String subject = "";
    String description = "";
    String status = "";

    public Feedback(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getRatings() {
        return ratings;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
