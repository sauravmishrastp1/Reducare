package com.azsm.reeduacare.model;

public class VideoContentMoel {

    private String videourl,videotitle;


    public VideoContentMoel(String videourl, String videotitle) {
        this.videourl = videourl;
        this.videotitle = videotitle;

    }


    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getVideotitle() {
        return videotitle;
    }

    public void setVideotitle(String videotitle) {
        this.videotitle = videotitle;
    }

}
