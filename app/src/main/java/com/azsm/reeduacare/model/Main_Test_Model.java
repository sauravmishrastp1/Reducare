package com.azsm.reeduacare.model;

public class Main_Test_Model {
    private String id;
    private String name;
    private String active_inactive;

    public Main_Test_Model(String id, String name, String active_inactive) {
        this.id = id;
        this.name = name;
        this.active_inactive = active_inactive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive_inactive() {
        return active_inactive;
    }

    public void setActive_inactive(String active_inactive) {
        this.active_inactive = active_inactive;
    }
}
