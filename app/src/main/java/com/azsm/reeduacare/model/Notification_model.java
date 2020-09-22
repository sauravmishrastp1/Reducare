package com.azsm.reeduacare.model;

public class Notification_model {
    private String id;
    private String  category_name;
    private String type;
    private String category_image;
    private String price;
    private String description;
    private String notification;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Notification_model(String id, String category_name, String type, String category_image, String price, String description, String notification) {
        this.id = id;
        this.category_name = category_name;
        this.type = type;
        this.category_image = category_image;
        this.price = price;
        this.description = description;
        this.notification = notification;

    }
}
