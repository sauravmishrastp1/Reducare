package com.azsm.reeduacare.model;

public class AtempttList {
    private String testheading;
    private String questioncount;
    private String time;
   private String id;

    public String getTestheading() {
        return testheading;
    }

    public void setTestheading(String testheading) {
        this.testheading = testheading;
    }

    public String getQuestioncount() {
        return questioncount;
    }

    public void setQuestioncount(String questioncount) {
        this.questioncount = questioncount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtempttList(String testheading, String questioncount, String time, String id) {
        this.testheading = testheading;
        this.questioncount = questioncount;
        this.time = time;
        this.id = id;
    }
}
