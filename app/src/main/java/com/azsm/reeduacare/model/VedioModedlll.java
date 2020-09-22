package com.azsm.reeduacare.model;

public class VedioModedlll {
    private String id;
    private String vediotitle;
    private String vediourl;
    private String prce;
    private String paymnettype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVediotitle() {
        return vediotitle;
    }

    public void setVediotitle(String vediotitle) {
        this.vediotitle = vediotitle;
    }

    public String getVediourl() {
        return vediourl;
    }

    public void setVediourl(String vediourl) {
        this.vediourl = vediourl;
    }

    public String getPrce() {
        return prce;
    }

    public void setPrce(String prce) {
        this.prce = prce;
    }

    public String getPaymnettype() {
        return paymnettype;
    }

    public void setPaymnettype(String paymnettype) {
        this.paymnettype = paymnettype;
    }

    public VedioModedlll(String id, String vediotitle, String vediourl, String prce, String paymnettype) {
        this.id = id;
        this.vediotitle = vediotitle;
        this.vediourl = vediourl;
        this.prce = prce;
        this.paymnettype = paymnettype;
    }
}
