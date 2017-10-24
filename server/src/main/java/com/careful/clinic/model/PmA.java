package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PM_A database table.
 * 
 */
@Entity
@Table(name="PM_A")
@NamedQuery(name="PmA.findAll", query="SELECT p FROM PmA p")
public class PmA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="D_INFO")
	private Date dInfo;

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="D_INSERT")
	private Date dInsert;

	@Temporal(TemporalType.DATE)
	@Column(name="\"DATA\"")
	private Date data;

	@Temporal(TemporalType.DATE)
	private Date dr;

	private String fam;

	private BigDecimal id;

	private String im;

	private String ot;

	private String prim;

	private BigDecimal smo;

	@Column(name="TYPE_INFO")
	private BigDecimal typeInfo;

	public PmA() {
	}

	public Date getDInfo() {
		return this.dInfo;
	}

	public void setDInfo(Date dInfo) {
		this.dInfo = dInfo;
	}

	public Date getDInsert() {
		return this.dInsert;
	}

	public void setDInsert(Date dInsert) {
		this.dInsert = dInsert;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDr() {
		return this.dr;
	}

	public void setDr(Date dr) {
		this.dr = dr;
	}

	public String getFam() {
		return this.fam;
	}

	public void setFam(String fam) {
		this.fam = fam;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIm() {
		return this.im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getOt() {
		return this.ot;
	}

	public void setOt(String ot) {
		this.ot = ot;
	}

	public String getPrim() {
		return this.prim;
	}

	public void setPrim(String prim) {
		this.prim = prim;
	}

	public BigDecimal getSmo() {
		return this.smo;
	}

	public void setSmo(BigDecimal smo) {
		this.smo = smo;
	}

	public BigDecimal getTypeInfo() {
		return this.typeInfo;
	}

	public void setTypeInfo(BigDecimal typeInfo) {
		this.typeInfo = typeInfo;
	}

}