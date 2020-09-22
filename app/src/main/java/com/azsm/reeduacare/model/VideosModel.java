package com.azsm.reeduacare.model;

import java.util.List;

public class VideosModel {

    private String title,coursecategory,price;
    private String thumbnail;
    private List<VideoContentMoel> videoContentMoelList;

    public VideosModel(String title, String coursecategory, String price, List<VideoContentMoel> videoContentMoelList) {
        this.title = title;
        this.coursecategory = coursecategory;
        this.price = price;
        this.videoContentMoelList = videoContentMoelList;
    }

    public List<VideoContentMoel> getVideoContentMoelList() {
        return videoContentMoelList;
    }

    public void setVideoContentMoelList(List<VideoContentMoel> videoContentMoelList) {
        this.videoContentMoelList = videoContentMoelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoursecategory() {
        return coursecategory;
    }

    public void setCoursecategory(String coursecategory) {
        this.coursecategory = coursecategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
