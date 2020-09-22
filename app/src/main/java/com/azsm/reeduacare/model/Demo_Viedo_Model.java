package com.azsm.reeduacare.model;

public class Demo_Viedo_Model {
    private String id;
    private String veddio_title;
    private String vedio_file;
    private String vedio_thumbnail;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVeddio_title() {
        return veddio_title;
    }

    public void setVeddio_title(String veddio_title) {
        this.veddio_title = veddio_title;
    }

    public String getVedio_file() {
        return vedio_file;
    }

    public void setVedio_file(String vedio_file) {
        this.vedio_file = vedio_file;
    }

    public String getVedio_thumbnail() {
        return vedio_thumbnail;
    }

    public void setVedio_thumbnail(String vedio_thumbnail) {
        this.vedio_thumbnail = vedio_thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Demo_Viedo_Model(String id, String veddio_title, String vedio_file, String vedio_thumbnail, String type) {
        this.id = id;
        this.veddio_title = veddio_title;
        this.vedio_file = vedio_file;
        this.vedio_thumbnail = vedio_thumbnail;
        this.type = type;
    }
}
