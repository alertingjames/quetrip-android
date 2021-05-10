package com.app.quetrip.models;

public class Comment {
    int id = 0;
    int service_id = 0;
    String category = "";
    int member_id = 0;
    String member_name = "";
    String member_picture = "";
    float ratings = 0.0f;
    String subject = "";
    String description = "";
    long dateTimestamp = 0;
    String status = "";

    public Comment(){}

    public Comment(int id, int service_id, String category, int member_id, String member_name, String member_picture, float ratings, String subject, String description, long dateTimestamp){
        this.id = id;
        this.service_id = service_id;
        this.category = category;
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_picture = member_picture;
        this.ratings = ratings;
        this.subject = subject;
        this.description = description;
        this.dateTimestamp = dateTimestamp;
    }

    public void setDateTimestamp(long dateTimestamp) {
        this.dateTimestamp = dateTimestamp;
    }

    public long getDateTimestamp() {
        return dateTimestamp;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public void setMember_picture(String member_picture) {
        this.member_picture = member_picture;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_picture() {
        return member_picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
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

    public int getService_id() {
        return service_id;
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
