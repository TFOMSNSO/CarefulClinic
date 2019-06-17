package com.careful.clinic.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;




/*
*
*                 " e.personBirthday = :birthday and" +
                " e.beginTreat = :begintreat and" +
                " e.endTreat = :endtreat")
*
* */



/*
*
*         @NamedQuery(name="Expertise.find",query = "SELECT e FROM Expertise e WHERE" +
                " e.surname = :personSurname and" +
                " e.firstName = :personName and" +
                " e.secName = :personLastname "
        )*/


@Entity
@Table(name="ME_MEDEXP")
@NamedQueries({
        @NamedQuery(name = "Expertise.findall", query = "SELECT e FROM Expertise e"),
        @NamedQuery(name = "Expertise.findex", query = "SELECT e FROM Expertise e WHERE" +
                " e.surname = :personSurname and" +
                " e.firstName = :personName and" +
                " e.secName = :personLastname and " +
                " e.personBirthday =:birthday and" +
                " e.beginTreat = :begintreat and " +
                " e.endTreat = :endtreat")
}

)


public class Expertise implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID_RECORD")
    private String id;

    @Column(name="SMO")
    private String smo;

    @Column(name="LPU")
    private String lpu;

    @Column(name="MED_AID")
    private String medAid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="EXAM_DATE")
    private Date examDate;

    @Column(name="EXAM_CODE")
    private String examCode;

    @Column(name="ADDRESS")
    private String address;


    @Column(name="SURNAME")
    private String surname;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="SEC_NAME")
    private String secName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDAY")
    private Date personBirthday;

    @Column(name="SER_POLIS")
    private String serPolis;

    @Column(name="NOM_POLIS")
    private String nomPolis;

    @Column(name="DOC_MED")
    private String docMed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="BEG_TREAT")
    private Date beginTreat;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="END_TREAT")
    private Date endTreat;

    @Column(name="LPU_MES")
    private String lpuMes;

    @Column(name="EXP_MES")
    private String expMes;

    @Column(name="SUMMA")
    private String summa;

    @Column(name="SHTRAF")
    private String shtraf;

    @Column(name="NUM_ACC")
    private String numAcc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_ACC")
    private Date dateAcc;

    @Column(name="COD_EXP")
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

    @Override
    public String toString() {
        return "expertise[" + beginTreat + "," + endTreat + "]";
    }
}
