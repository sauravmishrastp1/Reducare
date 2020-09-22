package com.azsm.reeduacare.model;

public class AttemptModel {
    String questioncount;
    int img;
    String heading;

    public String getQuestioncount() {
        return questioncount;
    }

    public void setQuestioncount(String questioncount) {
        this.questioncount = questioncount;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public AttemptModel(String questioncount, int img, String heading) {
        this.questioncount = questioncount;
        this.img = img;
        this.heading = heading;
    }
}
