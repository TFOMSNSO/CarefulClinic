package com.careful.clinic.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ExpertiseInfo {
    private String id;

    private String smo;

    private String lpu;

    private String medAid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    private Date examDate;

    private String examCode;

    private String address;

    private String surname;

    private String firstName;

    private String secName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    private Date personBirthday;

    private String serPolis;

    private String nomPolis;

    private String docMed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    private Date beginTreat;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    private Date endTreat;

    private String lpuMes;

    private String expMes;

    private String summa;

    private String shtraf;

    private String numAcc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    private Date dateAcc;

    private String codExp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmo() {
        return smo;
    }

    public void setSmo(String smo) {
        this.smo = smo;
    }

    public String getLpu() {
        return lpu;
    }

    public void setLpu(String lpu) {
        this.lpu = lpu;
    }

    public String getMedAid() {
        return medAid;
    }

    public void setMedAid(String medAid) {
        this.medAid = medAid;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public Date getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(Date personBirthday) {
        this.personBirthday = personBirthday;
    }

    public String getSerPolis() {
        return serPolis;
    }

    public void setSerPolis(String serPolis) {
        this.serPolis = serPolis;
    }

    public String getNomPolis() {
        return nomPolis;
    }

    public void setNomPolis(String nomPolis) {
        this.nomPolis = nomPolis;
    }

    public String getDocMed() {
        return docMed;
    }

    public void setDocMed(String docMed) {
        this.docMed = docMed;
    }

    public Date getBeginTreat() {
        return beginTreat;
    }

    public void setBeginTreat(Date beginTreat) {
        this.beginTreat = beginTreat;
    }

    public Date getEndTreat() {
        return endTreat;
    }

    public void setEndTreat(Date endTreat) {
        this.endTreat = endTreat;
    }

    public String getLpuMes() {
        return lpuMes;
    }

    public void setLpuMes(String lpuMes) {
        this.lpuMes = lpuMes;
    }

    public String getExpMes() {
        return expMes;
    }

    public void setExpMes(String expMes) {
        this.expMes = expMes;
    }

    public String getSumma() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa = summa;
    }

    public String getShtraf() {
        return shtraf;
    }

    public void setShtraf(String shtraf) {
        this.shtraf = shtraf;
    }

    public String getNumAcc() {
        return numAcc;
    }

    public void setNumAcc(String numAcc) {
        this.numAcc = numAcc;
    }

    public Date getDateAcc() {
        return dateAcc;
    }

    public void setDateAcc(Date dateAcc) {
        this.dateAcc = dateAcc;
    }

    public String getCodExp() {
        return codExp;
    }

    public void setCodExp(String codExp) {
        this.codExp = codExp;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "expertise[" + beginTreat + "," + endTreat + "]";
    }
}
