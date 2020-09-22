package com.azsm.reeduacare.model;

public class StudyMatrialSubtopic {
    private String sno;
    private String heading;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public StudyMatrialSubtopic(String sno, String heading) {
        this.sno = sno;
        this.heading = heading;
    }
}
