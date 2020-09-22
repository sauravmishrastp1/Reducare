package com.azsm.reeduacare.model;

public class SearchModel {

    private String keywordname;

    public SearchModel(String keywordname) {
        this.keywordname = keywordname;
    }


    public String getKeywordname() {
        return keywordname;
    }

    public void setKeywordname(String keywordname) {
        this.keywordname = keywordname;
    }
}
