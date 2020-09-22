package com.azsm.reeduacare.model;

public class CategoryModel {

    private String coursename;
    private String courseicon;
    private int backgroungresourse;
    private String courseid;


    public CategoryModel(String coursename, String courseicon, int backgroungresourse, String courseid) {
        this.coursename = coursename;
        this.courseicon = courseicon;
        this.backgroungresourse = backgroungresourse;
        this.courseid = courseid;
    }


    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCourseicon() {
        return courseicon;
    }

    public void setCourseicon(String courseicon) {
        this.courseicon = courseicon;
    }

    public int getBackgroungresourse() {
        return backgroungresourse;
    }

    public void setBackgroungresourse(int backgroungresourse) {
        this.backgroungresourse = backgroungresourse;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
}
