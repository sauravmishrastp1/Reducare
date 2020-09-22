package com.azsm.reeduacare.model;

public class ProvideCourceModelClass {
    private String courceimg;
    private String courcebname;
    private String courceid;
    private String price;

    public String getCourceimg() {
        return courceimg;
    }

    public void setCourceimg(String courceimg) {
        this.courceimg = courceimg;
    }

    public String getCourcebname() {
        return courcebname;
    }

    public void setCourcebname(String courcebname) {
        this.courcebname = courcebname;
    }

    public String getCourceid() {
        return courceid;
    }

    public void setCourceid(String courceid) {
        this.courceid = courceid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ProvideCourceModelClass(String courceimg, String courcebname, String courceid, String price) {
        this.courceimg = courceimg;
        this.courcebname = courcebname;
        this.courceid = courceid;
        this.price = price;
    }
}
