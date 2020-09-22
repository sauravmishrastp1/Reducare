package com.azsm.reeduacare.model;

public class Score_cardDate {
    private String atributename;
    private int attribute_image;
    private String no;

    public String getAtributename() {
        return atributename;
    }

    public void setAtributename(String atributename) {
        this.atributename = atributename;
    }

    public int getAttribute_image() {
        return attribute_image;
    }

    public void setAttribute_image(int attribute_image) {
        this.attribute_image = attribute_image;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Score_cardDate(String atributename, int attribute_image, String no) {
        this.atributename = atributename;
        this.attribute_image = attribute_image;
        this.no = no;
    }
}
