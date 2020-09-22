package com.azsm.reeduacare.model;

public class Mycourcer_model {
    private String name;
    private String dis;
    private String imgurl;
    private String disss;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDisss() {
        return disss;
    }

    public void setDisss(String disss) {
        this.disss = disss;
    }

    public Mycourcer_model(String name, String dis, String imgurl, String disss) {
        this.name = name;
        this.dis = dis;
        this.imgurl = imgurl;
        this.disss = disss;
    }
}
