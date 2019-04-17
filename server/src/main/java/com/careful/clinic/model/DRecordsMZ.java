package com.careful.clinic.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="D_RECORDS_MZ")
public class DRecordsMZ implements Serializable{
    private String lpu;
    private String mkb;
    private String fio;
    @Temporal(TemporalType.DATE)
    private Date dr;
    private String numCard;
    private String site;
    @Temporal(TemporalType.DATE)
    private Date dateAcc;
    @Temporal(TemporalType.DATE)
    private Date dateEndAcc;
    private String discriptionEndAcc;
    private String doctorFatherOfCard;
    private String doctorFact;
    private String tel;
    @Temporal(TemporalType.DATE)
    private Date datePlan;
    private String recommendation;
    private String gr;
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name="D_INSERT")
    private Date dInsert;

    public DRecordsMZ() {
    }

    public String getLpu() {
        return lpu;
    }

    public void setLpu(String lpu) {
        this.lpu = lpu;
    }

    public String getMkb() {
        return mkb;
    }

    public void setMkb(String mkb) {
        this.mkb = mkb;
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

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Date getDateAcc() {
        return dateAcc;
    }

    public void setDateAcc(Date dateAcc) {
        this.dateAcc = dateAcc;
    }

    public Date getDateEndAcc() {
        return dateEndAcc;
    }

    public void setDateEndAcc(Date dateEndAcc) {
        this.dateEndAcc = dateEndAcc;
    }

    public String getDiscriptionEndAcc() {
        return discriptionEndAcc;
    }

    public void setDiscriptionEndAcc(String discriptionEndAcc) {
        this.discriptionEndAcc = discriptionEndAcc;
    }

    public String getDoctorFatherOfCard() {
        return doctorFatherOfCard;
    }

    public void setDoctorFatherOfCard(String doctorFatherOfCard) {
        this.doctorFatherOfCard = doctorFatherOfCard;
    }

    public String getDoctorFact() {
        return doctorFact;
    }

    public void setDoctorFact(String doctorFact) {
        this.doctorFact = doctorFact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(Date datePlan) {
        this.datePlan = datePlan;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getGr() {
        return gr;
    }

    public void setGr(String gr) {
        this.gr = gr;
    }

    public Date getdInsert() {
        return dInsert;
    }

    public void setdInsert(Date dInsert) {
        this.dInsert = dInsert;
    }
}
