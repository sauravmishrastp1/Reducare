package com.azsm.reeduacare.model;

public class MenuModel {

    public String menuName, url;
    public boolean hasChildren, isGroup;
    public int img;

    public MenuModel(String menuName , boolean hasChildren, boolean isGroup, String url, int img) {
        this.menuName = menuName;
        this.url = url;
        this.hasChildren = hasChildren;
        this.isGroup = isGroup;
        this.img = img;
    }
}
