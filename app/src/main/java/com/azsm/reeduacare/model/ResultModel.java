package com.azsm.reeduacare.model;

public class ResultModel {
    private String subjcetname;
    private String marks;
    private String totalmarks;

    public String getSubjcetname() {
        return subjcetname;
    }

    public void setSubjcetname(String subjcetname) {
        this.subjcetname = subjcetname;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

    public ResultModel(String subjcetname, String marks, String totalmarks) {
        this.subjcetname = subjcetname;
        this.marks = marks;
        this.totalmarks = totalmarks;
    }
}
