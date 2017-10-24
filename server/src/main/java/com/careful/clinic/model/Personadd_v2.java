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
public class Personadd_v2 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*tele2: string;
	  teledom: string;
	  telework: string;
	  edit: string;*/

	@Id
	@Column(name="PERSONADD_ADDRESSID")
	@JsonIgnore
	private long personaddAddressid;

	@Column(name="BIRTH_OKSM")
	@JsonIgnore
	private String birthOksm;
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="D_DATE")
	@JsonIgnore
	private Date d_Date;

	@Column(name="D_NUM")
	@JsonIgnore
	private String d_Num;

	@Column(name="D_SER")
	@JsonIgnore
	private String d_Ser;

	@Column(name="D_V")
	@JsonIgnore
	private BigDecimal d_V;
	

	
	@Temporal(TemporalType.DATE)
	@JsonIgnore
	private Date d1;

	@Temporal(TemporalType.DATE)
	@JsonIgnore
	private Date d2;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_PRIK")
	@JsonIgnore
	private Date datePrik;

	@Temporal(TemporalType.DATE)
	@JsonIgnore
	private Date datepassport;

	@Column(name="DOK_VI")
	@JsonIgnore
	private String dokVi;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore
	private String enp;

	@Temporal(TemporalType.DATE)
	@Column(name="ENP_DATE")
	@JsonIgnore
	private Date enpDate;
	
	@JsonIgnore
	private BigDecimal fpolic;

	@JsonIgnore
	private BigDecimal kateg;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_DR")
	@JsonIgnore
	private Date lastDr;

	@Column(name="LAST_FAM")
	@JsonIgnore
	private String lastFam;

	@Column(name="LAST_IM")
	@JsonIgnore
	private String lastIm;

	@Column(name="LAST_OT")
	@JsonIgnore
	private String lastOt;

	@JsonIgnore
	private BigDecimal method;

	@Column(name="PERSONADD_PRIM")
	@JsonIgnore
	private String personaddPrim;

	@JsonIgnore
	private BigDecimal petition;

	@Column(name="PR_ADRES")
	@JsonIgnore
	private String prAdres;

	@Column(name="PR_FAM")
	@JsonIgnore
	private String prFam;

	@Column(name="PR_IM")
	@JsonIgnore
	private String prIm;

	@Column(name="PR_OT")
	@JsonIgnore
	private String prOt;

	@Column(name="PR_TEL")
	@JsonIgnore
	private String prTel;

	@JsonIgnore
	private BigDecimal pred;

	@JsonIgnore
	private BigDecimal russian;

	@JsonIgnore
	private String snils;

	private String tele2;

	private String teledom;

	private String telework;

	@Temporal(TemporalType.DATE)
	@Column(name="VS_DATE")
	@JsonIgnore
	private Date vsDate;

	@Column(name="VS_NUM")
	@JsonIgnore
	private String vsNum;

	@JsonIgnore
	private BigDecimal za;

	@Temporal(TemporalType.DATE)
	@JsonIgnore
	private Date zad;

	@JsonIgnore
	private BigDecimal zap;

	
	@Column(name="BORN")
	@JsonIgnore
	private String born;

	public Personadd_v2() {
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

	@JsonIgnore
	public Date getDDate() {
		return this.d_Date;
	}

	public void setDDate(Date d_Date) {
		this.d_Date = d_Date;
	}
	@JsonIgnore
	public String getDNum() {
		return this.d_Num;
	}

	public void setDNum(String d_Num) {
		this.d_Num = d_Num;
	}
	@JsonIgnore
	public String getDSer() {
		return this.d_Ser;
	}

	public void setDSer(String d_Ser) {
		this.d_Ser = d_Ser;
	}
	@JsonIgnore
	public BigDecimal getDV() {
		return this.d_V;
	}

	public void setDV(BigDecimal d_V) {
		this.d_V = d_V;
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
		builder.append(d_Date);
		builder.append(", dNum=");
		builder.append(d_Num);
		builder.append(", dSer=");
		builder.append(d_Ser);
		builder.append(", dV=");
		builder.append(d_V);
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