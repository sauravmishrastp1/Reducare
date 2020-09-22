package com.azsm.reeduacare.model;

public class Menu_model_study_matrial {

    public String menuName, url;
    public String hasChildren, isGroup;
    public int PDF;

    public Menu_model_study_matrial(String menuName, String url, String hasChildren, String isGroup, int PDF) {
        this.menuName = menuName;
        this.url = url;
        this.hasChildren = hasChildren;
        this.isGroup = isGroup;
        this.PDF = PDF;
    }
}
