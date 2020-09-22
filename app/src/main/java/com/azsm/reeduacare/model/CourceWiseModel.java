package com.azsm.reeduacare.model;

public class CourceWiseModel {

    private int courceimg;
    private String courcebname;

    public int getCourceimg() {
        return courceimg;
    }

    public void setCourceimg(int courceimg) {
        this.courceimg = courceimg;
    }

    public String getCourcebname() {
        return courcebname;
    }

    public void setCourcebname(String courcebname) {
        this.courcebname = courcebname;
    }

    public CourceWiseModel(int courceimg, String courcebname) {
        this.courceimg = courceimg;
        this.courcebname = courcebname;
    }
}
