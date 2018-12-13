package com.careful.clinic.model;

public class AfterDisp3Group {
    private String code_mo;
    private String name_mo;
    private Long total;
    private Long total_1;
    private Long total_2;
    private Long total_4;
    private Long total_20_22;
    private Long total_20;
    private Long total_22;
    private Long total_21_23;
    private Long total_21;
    private Long total_23;

    public AfterDisp3Group(String code_mo, String name_mo, Long total, Long total_1, Long total_2, Long total_4, Long total_20_22, Long total_20, Long total_22, Long total_21_23, Long total_21, Long total_23) {
        this.code_mo = code_mo;
        this.name_mo = name_mo;
        this.total = total;
        this.total_1 = total_1;
        this.total_2 = total_2;
        this.total_4 = total_4;
        this.total_20_22 = total_20_22;
        this.total_20 = total_20;
        this.total_22 = total_22;
        this.total_21_23 = total_21_23;
        this.total_21 = total_21;
        this.total_23 = total_23;
    }

    @Override
    public String toString() {
        return "AfterDisp3Group{" +
                "code_mo='" + code_mo + '\'' +
                ", name_mo='" + name_mo + '\'' +
                ", total=" + total +
                ", total_1=" + total_1 +
                ", total_2=" + total_2 +
                ", total_4=" + total_4 +
                ", total_20_22=" + total_20_22 +
                ", total_20=" + total_20 +
                ", total_22=" + total_22 +
                ", total_21_23=" + total_21_23 +
                ", total_21=" + total_21 +
                ", total_23=" + total_23 +
                '}';
    }

    public String getCode_mo() {
        return code_mo;
    }

    public void setCode_mo(String code_mo) {
        this.code_mo = code_mo;
    }

    public String getName_mo() {
        return name_mo;
    }

    public void setName_mo(String name_mo) {
        this.name_mo = name_mo;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal_1() {
        return total_1;
    }

    public void setTotal_1(Long total_1) {
        this.total_1 = total_1;
    }

    public Long getTotal_2() {
        return total_2;
    }

    public void setTotal_2(Long total_2) {
        this.total_2 = total_2;
    }

    public Long getTotal_4() {
        return total_4;
    }

    public void setTotal_4(Long total_4) {
        this.total_4 = total_4;
    }

    public Long getTotal_20_22() {
        return total_20_22;
    }

    public void setTotal_20_22(Long total_20_22) {
        this.total_20_22 = total_20_22;
    }

    public Long getTotal_20() {
        return total_20;
    }

    public void setTotal_20(Long total_20) {
        this.total_20 = total_20;
    }

    public Long getTotal_22() {
        return total_22;
    }

    public void setTotal_22(Long total_22) {
        this.total_22 = total_22;
    }

    public Long getTotal_21_23() {
        return total_21_23;
    }

    public void setTotal_21_23(Long total_21_23) {
        this.total_21_23 = total_21_23;
    }

    public Long getTotal_21() {
        return total_21;
    }

    public void setTotal_21(Long total_21) {
        this.total_21 = total_21;
    }

    public Long getTotal_23() {
        return total_23;
    }

    public void setTotal_23(Long total_23) {
        this.total_23 = total_23;
    }
}
