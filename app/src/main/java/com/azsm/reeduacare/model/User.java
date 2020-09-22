package com.azsm.reeduacare.model;

public class User {

    private String name,email,address,mobilenumber,userid,instructorid,profilepic;

    public User(String name, String email, String address, String mobilenumber, String userid, String instructorid, String profilepic) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobilenumber = mobilenumber;
        this.userid = userid;
        this.instructorid=instructorid;
        this.profilepic=profilepic;
    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getInstructorid() {
        return instructorid;
    }

    public void setInstructorid(String instructorid) {
        this.instructorid = instructorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
