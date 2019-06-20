package com.careful.clinic.model;

public class SearchZnoKeysModel {


    private String mansAge;
    private String maleAge;
    private String manAgemin;
    private String manAgemax;
    private String maleAgemin;
    private String maleAgemax;
    private boolean exportExcel;
    private Integer currentUser;
    private boolean simaz;
    private boolean vtb;
    private boolean ingos;
    private String count_notes;


    public String getCount_notes() {
        return count_notes;
    }

    public void setCount_notes(String count_notes) {
        this.count_notes = count_notes;
    }

    public String getMansAge() {
        return mansAge;
    }

    public void setMansAge(String mansAge) {
        this.mansAge = mansAge;
    }

    public String getMaleAge() {
        return maleAge;
    }

    public void setMaleAge(String maleAge) {
        this.maleAge = maleAge;
    }

    public String getManAgemin() {
        return manAgemin;
    }

    public void setManAgemin(String manAgemin) {
        this.manAgemin = manAgemin;
    }

    public String getManAgemax() {
        return manAgemax;
    }

    public void setManAgemax(String manAgemax) {
        this.manAgemax = manAgemax;
    }

    public String getMaleAgemin() {
        return maleAgemin;
    }

    public void setMaleAgemin(String maleAgemin) {
        this.maleAgemin = maleAgemin;
    }

    public String getMaleAgemax() {
        return maleAgemax;
    }

    public void setMaleAgemax(String maleAgemax) {
        this.maleAgemax = maleAgemax;
    }

    public boolean isExportExcel() {
        return exportExcel;
    }

    public void setExportExcel(boolean exportExcel) {
        this.exportExcel = exportExcel;
    }

    public Integer getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Integer currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isSimaz() {
        return simaz;
    }

    public void setSimaz(boolean simaz) {
        this.simaz = simaz;
    }

    public boolean isVtb() {
        return vtb;
    }

    public void setVtb(boolean vtb) {
        this.vtb = vtb;
    }

    public boolean isIngos() {
        return ingos;
    }

    public void setIngos(boolean ingos) {
        this.ingos = ingos;
    }


    @Override
    public String toString() {
        return "SearchZnoKeysModel[maleAge:" + maleAge +" ,mansAge:" + mansAge + " ,manAgemin:" + manAgemin + " ,manAgemax:" + manAgemax + " ,maleAgemin:" + maleAgemin + " ,maleAgemax:" + maleAgemax +
                " ,exportExcel:" + exportExcel + " ,currentUser:" + currentUser + " ,simaz:" + simaz + " ,vtb:" + vtb + " ,ingos" + ingos  + " ,count_nodes:" + count_notes + "]";
    }
}
