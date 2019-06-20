package com.careful.clinic.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;



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
    @Column(name="ID")
    private String _id;

    @Column(name="ID_RECORD")
    private String id;

    @Column(name="SMO")
    private String smo;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="EXAM_DATE")
    private Date examDate;

    @JsonIgnore
    @Column(name="SURNAME")
    private String surname;

    @JsonIgnore
    @Column(name="FIRST_NAME")
    private String firstName;

    @JsonIgnore
    @Column(name="SEC_NAME")
    private String secName;

    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDAY")
    private Date personBirthday;

    @JsonIgnore
    @Column(name="ADDRESS")
    private String address;

    @Column(name="EXP_NAME")
    private String expertName;

    @JsonIgnore
    @Column(name="DOCT_NAME")
    private String doctName;

    @JsonIgnore
    @Column(name="SER_POLIS")
    private String serPolis;

    @JsonIgnore
    @Column(name="NOM_POLIS")
    private String nomPolis;

    @JsonIgnore
    @Column(name="DOC_MED")
    private String docMed;

    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="BEG_TREAT")
    private Date beginTreat;

    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="END_TREAT")
    private Date endTreat;

    @JsonIgnore
    @Column(name="LPU_MES")
    private String lpuMes;

    @JsonIgnore
    @Column(name="EXP_MES")
    private String expMes;

    @JsonIgnore
    @Column(name="SUMMA")
    private String summa;

    @JsonIgnore
    @Column(name="SHTRAF")
    private String shtraf;

    @JsonIgnore
    @Column(name="NUM_ACC")
    private String numAcc;

    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_ACC")
    private Date dateAcc;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="EXAM_CODE",referencedColumnName = "ID"),
            @JoinColumn(name="COD_EXP", referencedColumnName = "TYPE_EXP")
    })
    private SPR_EXAM_CODE examCodeInfo;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne
    @JoinColumn(name="DEF_MAIN",referencedColumnName = "ID")
    private ME_SPR_DEFECTS defInfo;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="MEDEXPR_ID")
    private List<ME_MEDEXP_DEFECT> defects;


    public ME_SPR_DEFECTS getDefInfo() {
        return defInfo;
    }

    public void setDefInfo(ME_SPR_DEFECTS defInfo) {
        this.defInfo = defInfo;
    }

    public List<ME_MEDEXP_DEFECT> getDefects() {
        return defects;
    }

    public void setDefects(List<ME_MEDEXP_DEFECT> defects) {
        this.defects = defects;
    }

    public SPR_EXAM_CODE getExamCodeInfo() {
        return examCodeInfo;
    }

    public void setExamCodeInfo(SPR_EXAM_CODE examCodeInfo) {
        this.examCodeInfo = examCodeInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmo() {
        return smo;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public void setSmo(String smo) {
        this.smo = smo;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoctName() {
        return doctName;
    }

    public void setDoctName(String doctName) {
        this.doctName = doctName;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Expertise(Expertise b) {
        this.id = b.id;
        this.smo = b.smo;
        this.surname = b.surname;
        this.firstName = b.firstName;
        this.secName = b.secName;
        this.personBirthday = b.personBirthday;
        this.address = b.address;
        this.doctName = b.doctName;
        this.serPolis = b.serPolis;
        this.nomPolis = b.nomPolis;
        this.docMed = b.docMed;
        this.beginTreat = b.beginTreat;
        this.endTreat = b.endTreat;
        this.lpuMes = b.lpuMes;
        this.expMes = b.expMes;
        this.summa = b.summa;
        this.shtraf = b.shtraf;
        this.numAcc = b.numAcc;
        this.dateAcc = b.dateAcc;

    }
    public Expertise(){}
    @Override
    public String toString() {
        return "expertise[" + beginTreat + "," + endTreat +  " , defects"  + defects.size() +  "]";
    }
}
