package com.azsm.reeduacare.model;

public class Category {


    private String categoryname,categoryid;

    public Category(String categoryname, String categoryid) {
        this.categoryname = categoryname;
        this.categoryid = categoryid;
    }


    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
}
