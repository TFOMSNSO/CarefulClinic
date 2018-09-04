package com.careful.clinic.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InformDReestr {

    private String fio;
    private Date dr;
    private String tel;
    private String address;
    private String smoid;
    private String pol;
    private String lpu;
    private String ambkarta;
    private Date dat_beg;
    private Date dat_end;
    private String lpu_prik;
    private String account;
    private Date ac_date;
    private String rezobr;
    private String ds1;
    private String prdn;
    private String mes;
    private String kratnost;
    private Date last_treatment;
    private Date plan_inform;
    private Date date_inform;
    private Long id_assent;

    public InformDReestr(String fio, Date dr, String tel, String address, String smoid, String pol, String lpu,
                         String ambkarta, Date dat_beg, Date dat_end, String lpu_prik, String account, Date ac_date, String rezobr,
                         String ds1, String prdn, String mes, String kratnost, Date last_treatment, Date plan_inform,
                         Date date_inform, Long id_assent) {
        super();
        this.fio = fio;
        this.dr = dr;
        this.tel = tel;
        this.address = address;
        this.smoid = smoid;
        this.pol = pol;
        this.lpu = lpu;
        this.ambkarta = ambkarta;
        this.dat_beg = dat_beg;
        this.dat_end = dat_end;
        this.lpu_prik = lpu_prik;
        this.account = account;
        this.ac_date = ac_date;
        this.rezobr = rezobr;
        this.ds1 = ds1;
        this.prdn = prdn;
        this.mes = mes;
        this.kratnost = kratnost;
        this.last_treatment = last_treatment;
        this.plan_inform = plan_inform;
        this.date_inform = date_inform;
        this.id_assent = id_assent;
    }

    public InformDReestr() {

    }

    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
    public Date getDr() {
        return dr;
    }
    public void setDr(Date dr) {
        this.dr = dr;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSmoid() {
        return smoid;
    }
    public void setSmoid(String smoid) {
        this.smoid = smoid;
    }
    public String getPol() {
        return pol;
    }
    public void setPol(String pol) {
        this.pol = pol;
    }
    public String getLpu() {
        return lpu;
    }
    public void setLpu(String lpu) {
        this.lpu = lpu;
    }
    public String getAmbkarta() {
        return ambkarta;
    }
    public void setAmbkarta(String ambkarta) {
        this.ambkarta = ambkarta;
    }
    public Date getDat_beg() {
        return dat_beg;
    }
    public void setDat_beg(Date dat_beg) {
        this.dat_beg = dat_beg;
    }
    public Date getDat_end() {
        return dat_end;
    }
    public void setDat_end(Date dat_end) {
        this.dat_end = dat_end;
    }
    public String getLpu_prik() {
        return lpu_prik;
    }
    public void setLpu_prik(String lpu_prik) {
        this.lpu_prik = lpu_prik;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public Date getAc_date() {
        return ac_date;
    }
    public void setAc_date(Date ac_date) {
        this.ac_date = ac_date;
    }
    public String getRezobr() {
        return rezobr;
    }
    public void setRezobr(String rezobr) {
        this.rezobr = rezobr;
    }
    public String getDs1() {
        return ds1;
    }
    public void setDs1(String ds1) {
        this.ds1 = ds1;
    }
    public String getPrdn() {
        return prdn;
    }
    public void setPrdn(String prdn) {
        this.prdn = prdn;
    }
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
    public String getKratnost() {
        return kratnost;
    }
    public void setKratnost(String kratnost) {
        this.kratnost = kratnost;
    }
    public Date getLast_treatment() {
        return last_treatment;
    }
    public void setLast_treatment(Date last_treatment) {
        this.last_treatment = last_treatment;
    }
    public Date getPlan_inform() {
        return plan_inform;
    }
    public void setPlan_inform(Date plan_inform) {
        this.plan_inform = plan_inform;
    }
    public Date getDate_inform() {
        return date_inform;
    }
    public void setDate_inform(Date date_inform) {
        this.date_inform = date_inform;
    }
    public Long getId_assent() {
        return id_assent;
    }
    public void setId_assent(Long id_assent) {
        this.id_assent = id_assent;
    }


}


