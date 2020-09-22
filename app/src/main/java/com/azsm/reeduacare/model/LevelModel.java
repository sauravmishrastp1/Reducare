package com.azsm.reeduacare.model;

public class LevelModel {
    private String levelno;
    int lockimg;

    public String getLevelno() {
        return levelno;
    }

    public void setLevelno(String levelno) {
        this.levelno = levelno;
    }

    public int getLockimg() {
        return lockimg;
    }

    public void setLockimg(int lockimg) {
        this.lockimg = lockimg;
    }

    public LevelModel(String levelno, int lockimg) {
        this.levelno = levelno;
        this.lockimg = lockimg;
    }
}
