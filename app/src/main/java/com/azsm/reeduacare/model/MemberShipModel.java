package com.azsm.reeduacare.model;

public class MemberShipModel {
    private String courcename;

    public String getCourcename() {
        return courcename;
    }

    public void setCourcename(String courcename) {
        this.courcename = courcename;
    }

    public MemberShipModel(String courcename) {
        this.courcename = courcename;
    }
}
