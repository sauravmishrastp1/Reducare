package com.azsm.reeduacare.model;

public class VideoModel {

    private String videoid,videotitle,videourl,preview;
    private boolean isexpanded;

    public VideoModel(String videoid, String videotitle, String videourl, String preview) {
        this.videoid = videoid;
        this.videotitle = videotitle;
        this.videourl = videourl;
        this.preview = preview;
    }

    public VideoModel() {
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid;
    }

    public String getVideotitle() {
        return videotitle;
    }

    public void setVideotitle(String videotitle) {
        this.videotitle = videotitle;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public boolean isIsexpanded() {
        return isexpanded;
    }

    public void setIsexpanded(boolean isexpanded) {
        this.isexpanded = isexpanded;
    }
}
