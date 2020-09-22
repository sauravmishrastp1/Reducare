package com.azsm.reeduacare.model;

public class Daysmodelcalss {
    private String feetype;
    private boolean select;

    public String getFeetype() {
        return feetype;
    }

    public void setFeetype(String feetype) {
        this.feetype = feetype;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Daysmodelcalss(String feetype, boolean select) {
        this.feetype = feetype;
        this.select = select;
    }
}
