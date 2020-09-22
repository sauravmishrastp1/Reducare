package com.azsm.reeduacare.model;

import java.util.List;

public class SellerModel {


    private String coursetitle,courseprice,courserating,username;
    private List<String> videolist;


    public SellerModel(String coursetitle, String courseprice, String courserating, String username, List<String> videolist) {
        this.coursetitle = coursetitle;
        this.courseprice = courseprice;
        this.courserating = courserating;
        this.username = username;
        this.videolist = videolist;
    }


    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }

    public String getCourseprice() {
        return courseprice;
    }

    public void setCourseprice(String courseprice) {
        this.courseprice = courseprice;
    }

    public String getCourserating() {
        return courserating;
    }

    public void setCourserating(String courserating) {
        this.courserating = courserating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getVideolist() {
        return videolist;
    }

    public void setVideolist(List<String> videolist) {
        this.videolist = videolist;
    }
}
