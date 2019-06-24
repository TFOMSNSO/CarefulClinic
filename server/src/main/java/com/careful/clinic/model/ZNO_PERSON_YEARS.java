package com.careful.clinic.model;

public class ZNO_PERSON_YEARS extends ZNO_PERSON{
    private Integer currentUser;
    private String years;

    public Integer getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Integer currentUser) {
        this.currentUser = currentUser;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
