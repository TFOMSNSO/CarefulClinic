package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PERSONADD database table.
 * 
 */
@Entity
@NamedQuery(name="Personadd.findAll", query="SELECT p FROM Personadd p")
public class Personadd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PERSONADD_ADDRESSID")
	private long personaddAddressid;

	@Column(name="BIRTH_OKSM")
	private String birthOksm;

	private String born;

	@Temporal(TemporalType.DATE)
	@Column(name="D_DATE")
	private Date dDate;

	@Column(name="D_NUM")
	private String dNum;

	@Column(name="D_SER")
	private String dSer;

	@Column(name="D_V")
	private BigDecimal dV;

	@Temporal(TemporalType.DATE)
	private Date d1;

	@Temporal(TemporalType.DATE)
	private Date d2;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_PRIK")
	private Date datePrik;

	@Temporal(TemporalType.DATE)
	private Date datepassport;

	@Column(name="DOK_VI")
	private String dokVi;

	private String email;

	private String enp;

	@Temporal(TemporalType.DATE)
	@Column(name="ENP_DATE")
	private Date enpDate;

	private BigDecimal fpolic;

	private BigDecimal kateg;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DR")
	private Date lastDr;

	@Column(name="LAST_FAM")
	private String lastFam;

	@Column(name="LAST_IM")
	private String lastIm;

	@Column(name="LAST_OT")
	private String lastOt;

	private BigDecimal method;

	@Column(name="PERSONADD_PRIM")
	private String personaddPrim;

	private BigDecimal petition;

	@Column(name="PR_ADRES")
	private String prAdres;

	@Column(name="PR_FAM")
	private String prFam;

	@Column(name="PR_IM")
	private String prIm;

	@Column(name="PR_OT")
	private String prOt;

	@Column(name="PR_TEL")
	private String prTel;

	private BigDecimal pred;

	private BigDecimal russian;

	private String snils;

	private String tele2;

	private String teledom;

	private String telework;

	@Temporal(TemporalType.DATE)
	@Column(name="VS_DATE")
	private Date vsDate;

	@Column(name="VS_NUM")
	private String vsNum;

	private BigDecimal za;

	@Temporal(TemporalType.DATE)
	private Date zad;

	private BigDecimal zap;

	//bi-directional one-to-one association to Person
	@OneToOne(mappedBy="personadd")
	@JsonIgnore
	private Person person;

	public Personadd() {
	}

	public long getPersonaddAddressid() {
		return this.personaddAddressid;
	}

	public void setPersonaddAddressid(long personaddAddressid) {
		this.personaddAddressid = personaddAddressid;
	}

	public String getBirthOksm() {
		return this.birthOksm;
	}

	public void setBirthOksm(String birthOksm) {
		this.birthOksm = birthOksm;
	}

	public String getBorn() {
		return this.born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public Date getDDate() {
		return this.dDate;
	}

	public void setDDate(Date dDate) {
		this.dDate = dDate;
	}

	public String getDNum() {
		return this.dNum;
	}

	public void setDNum(String dNum) {
		this.dNum = dNum;
	}

	public String getDSer() {
		return this.dSer;
	}

	public void setDSer(String dSer) {
		this.dSer = dSer;
	}

	public BigDecimal getDV() {
		return this.dV;
	}

	public void setDV(BigDecimal dV) {
		this.dV = dV;
	}

	public Date getD1() {
		return this.d1;
	}

	public void setD1(Date d1) {
		this.d1 = d1;
	}

	public Date getD2() {
		return this.d2;
	}

	public void setD2(Date d2) {
		this.d2 = d2;
	}

	public Date getDatePrik() {
		return this.datePrik;
	}

	public void setDatePrik(Date datePrik) {
		this.datePrik = datePrik;
	}

	public Date getDatepassport() {
		return this.datepassport;
	}

	public void setDatepassport(Date datepassport) {
		this.datepassport = datepassport;
	}

	public String getDokVi() {
		return this.dokVi;
	}

	public void setDokVi(String dokVi) {
		this.dokVi = dokVi;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnp() {
		return this.enp;
	}

	public void setEnp(String enp) {
		this.enp = enp;
	}

	public Date getEnpDate() {
		return this.enpDate;
	}

	public void setEnpDate(Date enpDate) {
		this.enpDate = enpDate;
	}

	public BigDecimal getFpolic() {
		return this.fpolic;
	}

	public void setFpolic(BigDecimal fpolic) {
		this.fpolic = fpolic;
	}

	public BigDecimal getKateg() {
		return this.kateg;
	}

	public void setKateg(BigDecimal kateg) {
		this.kateg = kateg;
	}

	public Date getLastDr() {
		return this.lastDr;
	}

	public void setLastDr(Date lastDr) {
		this.lastDr = lastDr;
	}

	public String getLastFam() {
		return this.lastFam;
	}

	public void setLastFam(String lastFam) {
		this.lastFam = lastFam;
	}

	public String getLastIm() {
		return this.lastIm;
	}

	public void setLastIm(String lastIm) {
		this.lastIm = lastIm;
	}

	public String getLastOt() {
		return this.lastOt;
	}

	public void setLastOt(String lastOt) {
		this.lastOt = lastOt;
	}

	public BigDecimal getMethod() {
		return this.method;
	}

	public void setMethod(BigDecimal method) {
		this.method = method;
	}

	public String getPersonaddPrim() {
		return this.personaddPrim;
	}

	public void setPersonaddPrim(String personaddPrim) {
		this.personaddPrim = personaddPrim;
	}

	public BigDecimal getPetition() {
		return this.petition;
	}

	public void setPetition(BigDecimal petition) {
		this.petition = petition;
	}

	public String getPrAdres() {
		return this.prAdres;
	}

	public void setPrAdres(String prAdres) {
		this.prAdres = prAdres;
	}

	public String getPrFam() {
		return this.prFam;
	}

	public void setPrFam(String prFam) {
		this.prFam = prFam;
	}

	public String getPrIm() {
		return this.prIm;
	}

	public void setPrIm(String prIm) {
		this.prIm = prIm;
	}

	public String getPrOt() {
		return this.prOt;
	}

	public void setPrOt(String prOt) {
		this.prOt = prOt;
	}

	public String getPrTel() {
		return this.prTel;
	}

	public void setPrTel(String prTel) {
		this.prTel = prTel;
	}

	public BigDecimal getPred() {
		return this.pred;
	}

	public void setPred(BigDecimal pred) {
		this.pred = pred;
	}

	public BigDecimal getRussian() {
		return this.russian;
	}

	public void setRussian(BigDecimal russian) {
		this.russian = russian;
	}

	public String getSnils() {
		return this.snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
	}

	public String getTele2() {
		return this.tele2;
	}

	public void setTele2(String tele2) {
		this.tele2 = tele2;
	}

	public String getTeledom() {
		return this.teledom;
	}

	public void setTeledom(String teledom) {
		this.teledom = teledom;
	}

	public String getTelework() {
		return this.telework;
	}

	public void setTelework(String telework) {
		this.telework = telework;
	}

	public Date getVsDate() {
		return this.vsDate;
	}

	public void setVsDate(Date vsDate) {
		this.vsDate = vsDate;
	}

	public String getVsNum() {
		return this.vsNum;
	}

	public void setVsNum(String vsNum) {
		this.vsNum = vsNum;
	}

	public BigDecimal getZa() {
		return this.za;
	}

	public void setZa(BigDecimal za) {
		this.za = za;
	}

	public Date getZad() {
		return this.zad;
	}

	public void setZad(Date zad) {
		this.zad = zad;
	}

	public BigDecimal getZap() {
		return this.zap;
	}

	public void setZap(BigDecimal zap) {
		this.zap = zap;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Personadd [personaddAddressid=");
		builder.append(personaddAddressid);
		builder.append(", birthOksm=");
		builder.append(birthOksm);
		builder.append(", born=");
		builder.append(born);
		builder.append(", dDate=");
		builder.append(dDate);
		builder.append(", dNum=");
		builder.append(dNum);
		builder.append(", dSer=");
		builder.append(dSer);
		builder.append(", dV=");
		builder.append(dV);
		builder.append(", d1=");
		builder.append(d1);
		builder.append(", d2=");
		builder.append(d2);
		builder.append(", datePrik=");
		builder.append(datePrik);
		builder.append(", datepassport=");
		builder.append(datepassport);
		builder.append(", dokVi=");
		builder.append(dokVi);
		builder.append(", email=");
		builder.append(email);
		builder.append(", enp=");
		builder.append(enp);
		builder.append(", enpDate=");
		builder.append(enpDate);
		builder.append(", fpolic=");
		builder.append(fpolic);
		builder.append(", kateg=");
		builder.append(kateg);
		builder.append(", lastDr=");
		builder.append(lastDr);
		builder.append(", lastFam=");
		builder.append(lastFam);
		builder.append(", lastIm=");
		builder.append(lastIm);
		builder.append(", lastOt=");
		builder.append(lastOt);
		builder.append(", method=");
		builder.append(method);
		builder.append(", personaddPrim=");
		builder.append(personaddPrim);
		builder.append(", petition=");
		builder.append(petition);
		builder.append(", prAdres=");
		builder.append(prAdres);
		builder.append(", prFam=");
		builder.append(prFam);
		builder.append(", prIm=");
		builder.append(prIm);
		builder.append(", prOt=");
		builder.append(prOt);
		builder.append(", prTel=");
		builder.append(prTel);
		builder.append(", pred=");
		builder.append(pred);
		builder.append(", russian=");
		builder.append(russian);
		builder.append(", snils=");
		builder.append(snils);
		builder.append(", tele2=");
		builder.append(tele2);
		builder.append(", teledom=");
		builder.append(teledom);
		builder.append(", telework=");
		builder.append(telework);
		builder.append(", vsDate=");
		builder.append(vsDate);
		builder.append(", vsNum=");
		builder.append(vsNum);
		builder.append(", za=");
		builder.append(za);
		builder.append(", zad=");
		builder.append(zad);
		builder.append(", zap=");
		builder.append(zap);
		builder.append("]");
		return builder.toString();
	}

}