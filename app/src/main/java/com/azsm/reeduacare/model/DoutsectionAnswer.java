package com.azsm.reeduacare.model;

public class DoutsectionAnswer {

   private String vediourl;
   private String QUESTON;

    public String getVediourl() {
        return vediourl;
    }

    public void setVediourl(String vediourl) {
        this.vediourl = vediourl;
    }

    public String getQUESTON() {
        return QUESTON;
    }

    public void setQUESTON(String QUESTON) {
        this.QUESTON = QUESTON;
    }

    public DoutsectionAnswer(String vediourl, String QUESTON) {
        this.vediourl = vediourl;
        this.QUESTON = QUESTON;
    }
}
