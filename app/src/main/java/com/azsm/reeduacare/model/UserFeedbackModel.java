package com.azsm.reeduacare.model;

public class UserFeedbackModel {

    private String username,commenttime,coment;
    private float rating;


    public UserFeedbackModel(float rating, String username, String commenttime, String coment) {
        this.rating = rating;
        this.username = username;
        this.commenttime = commenttime;
        this.coment = coment;
    }


    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
}
