package com.azsm.reeduacare.model;

public class ExamNation_Model {
    private String course;
    private String id;
    private String quizetime;
    private String quiztime1;
    private String paymnetstatus;
    private String praymnetPrice;
    private String winingprice;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuizetime() {
        return quizetime;
    }

    public void setQuizetime(String quizetime) {
        this.quizetime = quizetime;
    }

    public String getQuiztime1() {
        return quiztime1;
    }

    public void setQuiztime1(String quiztime1) {
        this.quiztime1 = quiztime1;
    }

    public String getPaymnetstatus() {
        return paymnetstatus;
    }

    public void setPaymnetstatus(String paymnetstatus) {
        this.paymnetstatus = paymnetstatus;
    }

    public String getPraymnetPrice() {
        return praymnetPrice;
    }

    public void setPraymnetPrice(String praymnetPrice) {
        this.praymnetPrice = praymnetPrice;
    }

    public String getWiningprice() {
        return winingprice;
    }

    public void setWiningprice(String winingprice) {
        this.winingprice = winingprice;
    }

    public ExamNation_Model(String course, String id, String quizetime, String quiztime1, String paymnetstatus, String praymnetPrice, String winingprice) {
        this.course = course;
        this.id = id;
        this.quizetime = quizetime;
        this.quiztime1 = quiztime1;
        this.paymnetstatus = paymnetstatus;
        this.praymnetPrice = praymnetPrice;
        this.winingprice = winingprice;
    }
}