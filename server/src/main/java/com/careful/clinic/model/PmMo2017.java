package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PM_MO_2017 database table.
 *
 * */

@Entity
@Table(name="PM_MO_2017")
@NamedQuery(name="PmMo2017.findByAdressid", query="SELECT p FROM PmMo2017 p where p.tfomsId = :tfomsId")
public class PmMo2017 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String adress;

	@Temporal(TemporalType.DATE)
	@Column(name="\"DATA\"")
	private Date data;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_BEGIN")
	private Date dateBegin;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_END")
	private Date dateEnd;

	private BigDecimal god;

	private BigDecimal kv;

	@Column(name="MO_PRIK")
	private BigDecimal moPrik;

	@Column(name="REF_ID_PERSON")
	private BigDecimal refIdPerson;

	private BigDecimal res;

	@Column(name="RES_INFO")
	private BigDecimal resInfo;

	@Column(name="RES_MO")
	private BigDecimal resMo;

	private BigDecimal smo;

	private String tel;

	@Id
	@Column(name="TFOMS_ID")
	private Integer tfomsId;

	public PmMo2017() {
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDateBegin() {
		return this.dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public BigDecimal getGod() {
		return this.god;
	}

	public void setGod(BigDecimal god) {
		this.god = god;
	}

	public BigDecimal getKv() {
		return this.kv;
	}

	public void setKv(BigDecimal kv) {
		this.kv = kv;
	}

	public BigDecimal getMoPrik() {
		return this.moPrik;
	}

	public void setMoPrik(BigDecimal moPrik) {
		this.moPrik = moPrik;
	}

	public BigDecimal getRefIdPerson() {
		return this.refIdPerson;
	}

	public void setRefIdPerson(BigDecimal refIdPerson) {
		this.refIdPerson = refIdPerson;
	}

	public BigDecimal getRes() {
		return this.res;
	}

	public void setRes(BigDecimal res) {
		this.res = res;
	}

	public BigDecimal getResInfo() {
		return this.resInfo;
	}

	public void setResInfo(BigDecimal resInfo) {
		this.resInfo = resInfo;
	}

	public BigDecimal getResMo() {
		return this.resMo;
	}

	public void setResMo(BigDecimal resMo) {
		this.resMo = resMo;
	}

	public BigDecimal getSmo() {
		return this.smo;
	}

	public void setSmo(BigDecimal smo) {
		this.smo = smo;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getTfomsId() {
		return this.tfomsId;
	}

	public void setTfomsId(Integer tfomsId) {
		this.tfomsId = tfomsId;
	}

}