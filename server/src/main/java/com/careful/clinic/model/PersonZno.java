package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the person_zno database table.
 *
 */
@Entity
@Table(name="ZNO_PERSON")
public class PersonZno implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name="ID1")
    private String id1;

    @Column(name="ID2")
    private String id2;

    @Column(name="FAM")
    private String personSurname;

    @Column(name="IM")
    private String personKindfirstname;

    @Column(name="OT")
    private String personKindlastname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    //@Temporal(TemporalType.DATE)
    @Column(name="DR")
    private Date personBirthday;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_INSERT")
    @JsonIgnore
    private Date personDateInsert;

    @Column(name="STATE_INSUR")
    @JsonIgnore
    private BigDecimal personStateInsur;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_INSUR")
    @JsonIgnore
    private Date personDateInsur;

    @Column(name="STATE_REGISTR")
    @JsonIgnore
    private BigDecimal personStateRegistr;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_STATE")
    @JsonIgnore
    private Date personDateState;

    @Column(name="STATE_INSERT")
    @JsonIgnore
    private BigDecimal personStateInsert;




    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonKindfirstname() {
        return personKindfirstname;
    }

    public void setPersonKindfirstname(String personKindfirstname) {
        this.personKindfirstname = personKindfirstname;
    }

    public String getPersonKindlastname() {
        return personKindlastname;
    }

    public void setPersonKindlastname(String personKindlastname) {
        this.personKindlastname = personKindlastname;
    }

    public Date getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(Date personBirthday) {
        this.personBirthday = personBirthday;
    }

    public Date getPersonDateInsert() {
        return personDateInsert;
    }

    public void setPersonDateInsert(Date personDateInsert) {
        this.personDateInsert = personDateInsert;
    }

    public BigDecimal getPersonStateInsur() {
        return personStateInsur;
    }

    public void setPersonStateInsur(BigDecimal personStateInsur) {
        this.personStateInsur = personStateInsur;
    }

    public Date getPersonDateInsur() {
        return personDateInsur;
    }

    public void setPersonDateInsur(Date personDateInsur) {
        this.personDateInsur = personDateInsur;
    }

    public BigDecimal getPersonStateRegistr() {
        return personStateRegistr;
    }

    public void setPersonStateRegistr(BigDecimal personStateRegistr) {
        this.personStateRegistr = personStateRegistr;
    }

    public Date getPersonDateState() {
        return personDateState;
    }

    public void setPersonDateState(Date personDateState) {
        this.personDateState = personDateState;
    }

    public BigDecimal getPersonStateInsert() {
        return personStateInsert;
    }

    public void setPersonStateInsert(BigDecimal personStateInsert) {
        this.personStateInsert = personStateInsert;
    }

}