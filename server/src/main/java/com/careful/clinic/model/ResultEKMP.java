package com.careful.clinic.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="RESULT_EKMP")
//@NamedQuery(name="RESULT_EKMP.findAll", query="SELECT r FROM RESULT_EKMP r")
public class ResultEKMP implements Serializable {
    //private static final long serialVersionUID = 1L;

    private BigDecimal mo;

    private String nameMo;

    private String surname;

    private String firstName;

    private String secName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private BigDecimal rezobr;

    private BigDecimal resEkmp;

    private BigDecimal countViolation;

    private String codeViolation1;

    private String codeViolation2;

    private String codeViolation3;

    private BigDecimal causeViolation;

    private BigDecimal causeNullViolation;

    private BigDecimal individAccord;

    private BigDecimal individInforming;

    private BigDecimal onDControl;

    private BigDecimal hospitalized;

    private String note;

    private BigDecimal smo;

    @Temporal(TemporalType.DATE)
    @Column(name="\"DATE_DOC\"")
    private Date dateDoc;

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name="D_INSERT")
    private Date dInsert;

    public ResultEKMP() {
    }

    public BigDecimal getMo() {
        return mo;
    }

    public void setMo(BigDecimal mo) {
        this.mo = mo;
    }

    public String getNameMo() {
        return nameMo;
    }

    public void setNameMo(String nameMo) {
        this.nameMo = nameMo;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getRezobr() {
        return rezobr;
    }

    public void setRezobr(BigDecimal rezobr) {
        this.rezobr = rezobr;
    }

    public BigDecimal getResEkmp() {
        return resEkmp;
    }

    public void setResEkmp(BigDecimal resEkmp) {
        this.resEkmp = resEkmp;
    }

    public BigDecimal getCountViolation() {
        return countViolation;
    }

    public void setCountViolation(BigDecimal countViolation) {
        this.countViolation = countViolation;
    }

    public String getCodeViolation1() {
        return codeViolation1;
    }

    public void setCodeViolation1(String codeViolation1) {
        this.codeViolation1 = codeViolation1;
    }

    public String getCodeViolation2() {
        return codeViolation2;
    }

    public void setCodeViolation2(String codeViolation2) {
        this.codeViolation2 = codeViolation2;
    }

    public String getCodeViolation3() {
        return codeViolation3;
    }

    public void setCodeViolation3(String codeViolation3) {
        this.codeViolation3 = codeViolation3;
    }

    public BigDecimal getCauseViolation() {
        return causeViolation;
    }

    public void setCauseViolation(BigDecimal causeViolation) {
        this.causeViolation = causeViolation;
    }

    public BigDecimal getCauseNullViolation() {
        return causeNullViolation;
    }

    public void setCauseNullViolation(BigDecimal causeNullViolation) {
        this.causeNullViolation = causeNullViolation;
    }

    public BigDecimal getIndividAccord() {
        return individAccord;
    }

    public void setIndividAccord(BigDecimal individAccord) {
        this.individAccord = individAccord;
    }

    public BigDecimal getIndividInforming() {
        return individInforming;
    }

    public void setIndividInforming(BigDecimal individInforming) {
        this.individInforming = individInforming;
    }

    public BigDecimal getOnDControl() {
        return onDControl;
    }

    public void setOnDControl(BigDecimal onDControl) {
        this.onDControl = onDControl;
    }

    public BigDecimal getHospitalized() {
        return hospitalized;
    }

    public void setHospitalized(BigDecimal hospitalized) {
        this.hospitalized = hospitalized;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getSmo() {
        return smo;
    }

    public void setSmo(BigDecimal smo) {
        this.smo = smo;
    }

    public Date getDateDoc() {
        return dateDoc;
    }

    public void setDateDoc(Date dateDoc) {
        this.dateDoc = dateDoc;
    }

    public Date getdInsert() {
        return dInsert;
    }

    public void setdInsert(Date dInsert) {
        this.dInsert = dInsert;
    }
}
