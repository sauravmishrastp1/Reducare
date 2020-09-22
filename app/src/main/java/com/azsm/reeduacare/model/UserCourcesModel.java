package com.azsm.reeduacare.model;

public class UserCourcesModel {

    private String coursetitle,id,coursecategory,coursediscription,courseprice,islive,courseid,totalvideo,courseicon,createdbyname,creaedbyemail;
    private Float rating;

    public UserCourcesModel(String coursetitle, String coursecategory, String coursediscription, String courseprice, String islive, String courseid, String totalvideo, String courseicon) {
        this.coursetitle = coursetitle;
        this.coursecategory = coursecategory;
        this.coursediscription = coursediscription;
        this.courseprice = courseprice;
        this.islive = islive;
        this.courseid=courseid;
        this.totalvideo=totalvideo;
        this.courseicon=courseicon;
    }


    public UserCourcesModel(String coursetitle, String coursecategory, String coursediscription, String courseprice, String islive, String courseid, String totalvideo, String courseicon, Float rating) {
        this.coursetitle = coursetitle;
        this.coursecategory = coursecategory;
        this.coursediscription = coursediscription;
        this.courseprice = courseprice;
        this.islive = islive;
        this.courseid = courseid;
        this.totalvideo = totalvideo;
        this.courseicon = courseicon;
        this.rating = rating;
    }


    public UserCourcesModel(String coursetitle, String coursecategory, String coursediscription, String courseprice, String islive, String courseid, String totalvideo, String courseicon, Float rating, String createdbyname, String creaedbyemail) {
        this.coursetitle = coursetitle;
        this.coursecategory = coursecategory;
        this.coursediscription = coursediscription;
        this.courseprice = courseprice;
        this.islive = islive;
        this.courseid = courseid;
        this.totalvideo = totalvideo;
        this.courseicon = courseicon;
        this.createdbyname = createdbyname;
        this.creaedbyemail = creaedbyemail;
        this.rating = rating;
    }


    public UserCourcesModel(String coursetitle, String id , String coursecategory, String coursediscription, String courseprice, String islive, String courseid, String totalvideo, String courseicon, Float rating, String createdbyname, String creaedbyemail) {
        this.coursetitle = coursetitle;
        this.coursecategory = coursecategory;
        this.coursediscription = coursediscription;
        this.courseprice = courseprice;
        this.islive = islive;
        this.courseid = courseid;
        this.totalvideo = totalvideo;
        this.courseicon = courseicon;
        this.createdbyname = createdbyname;
        this.creaedbyemail = creaedbyemail;
        this.rating = rating;
        this.id=id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedbyname() {
        return createdbyname;
    }

    public void setCreatedbyname(String createdbyname) {
        this.createdbyname = createdbyname;
    }

    public String getCreaedbyemail() {
        return creaedbyemail;
    }

    public void setCreaedbyemail(String creaedbyemail) {
        this.creaedbyemail = creaedbyemail;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getCourseicon() {
        return courseicon;
    }

    public void setCourseicon(String courseicon) {
        this.courseicon = courseicon;
    }

    public String getTotalvideo() {
        return totalvideo;
    }

    public void setTotalvideo(String totalvideo) {
        this.totalvideo = totalvideo;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }

    public String getCoursecategory() {
        return coursecategory;
    }

    public void setCoursecategory(String coursecategory) {
        this.coursecategory = coursecategory;
    }

    public String getCoursediscription() {
        return coursediscription;
    }

    public void setCoursediscription(String coursediscription) {
        this.coursediscription = coursediscription;
    }

    public String getCourseprice() {
        return courseprice;
    }

    public void setCourseprice(String courseprice) {
        this.courseprice = courseprice;
    }

    public String getIslive() {
        return islive;
    }

    public void setIslive(String islive) {
        this.islive = islive;
    }
}
