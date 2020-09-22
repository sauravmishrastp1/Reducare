package com.azsm.reeduacare.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dout {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer_text")
    @Expose
    private String answerText;
    @SerializedName("answer_images")
    @Expose
    private String answerImages;
    @SerializedName("answer_videos")
    @Expose
    private String answerVideos;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerImages() {
        return answerImages;
    }

    public void setAnswerImages(String answerImages) {
        this.answerImages = answerImages;
    }

    public String getAnswerVideos() {
        return answerVideos;
    }

    public void setAnswerVideos(String answerVideos) {
        this.answerVideos = answerVideos;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }


}
