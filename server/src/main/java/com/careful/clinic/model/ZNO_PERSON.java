package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the person_zno database table.
 *
 */
@Entity
@Table(name = "ZNO_PERSON")
@NamedQueries({
        @NamedQuery(name="personzno.findbyid1",query = "SELECT z FROM ZNO_PERSON z "),
        @NamedQuery(name="personzno.findbyname",query = "SELECT z FROM ZNO_PERSON z " +
                "WHERE z.personKindfirstname = :personFirstname " +
                " and z.personSurname = :personSurname" +
                " and z.personKindlastname = :personLastname" +
                " and z.personBirthday = :birthday"),
        @NamedQuery(name="personzno.findbynamesmo",query = "SELECT z FROM ZNO_PERSON z " +
                "WHERE z.personKindfirstname = :personFirstname " +
                " and z.personSurname = :personSurname" +
                " and z.personKindlastname = :personLastname" +
                " and z.personBirthday = :birthday" +
                " and z.smo = :smo"),
        @NamedQuery(name="personzno.findbyid",query = "SELECT z FROM ZNO_PERSON z WHERE z.id1 = :p_id")
})
public class ZNO_PERSON implements Serializable {
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
    @Column(name="DR")
    private Date personBirthday;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Column(name="DATE_INSERT")
    private Date personDateInsert;

    @Column(name="STATE_INSUR")
    private BigDecimal personStateInsur;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Column(name="DATE_INSUR")
    private Date personDateInsur;

    @Column(name="STATE_REGISTR")
    private BigDecimal personStateRegistr;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Column(name="DATE_STATE")
    private Date personDateState;

    @Column(name="STATE_INSERT")
    private BigDecimal personStateInsert;

    @Column(name="SMO")
    private String smo;


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

    public String getSmo() {
        return smo;
    }

    public void setSmo(String smo) {
        this.smo = smo;
    }

    @Override
    public String toString() {
        return "ZNO_PERSON[ID1:"+ id1 + ",ID2:" + id2 +",FAM:"+ personSurname + ",IM:" + personKindfirstname + ",OT:" + personKindlastname +
                ",DR:"+personBirthday + ",DATE_INSERT:" + personDateInsert + ",DATE_STATE:" + personDateState + ",STATE_INSUR:" + personStateInsur +
                ",DATE_INSUR:"+ personDateInsur  + ",SMO:" + smo + "]";
    }
}