package com.careful.clinic.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Wrap3a_b_Expertise {

	public Wrap3a_b_Expertise(String fio, Date dr, Long smoid, String serpolis, String numpolis, String lpu,
			String ambkarta, Date dat_beg, Date dat_end, Long lpu_prik, Long s1, Long account, Date ac_date,
			String rezobr, String f_mkb_usl, String tel, Long id){
		super();
		this.tel = tel;
		this.id = id;
		this.fio = fio;
		this.dr = dr;
		this.smoid = smoid;
		this.serpolis = serpolis;
		this.numpolis = numpolis;
		this.lpu = lpu;
		this.ambkarta = ambkarta;
		this.dat_beg = dat_beg;
		this.dat_end = dat_end;
		this.lpu_prik = lpu_prik;
		this.s1 = s1;
		this.account = account;
		this.ac_date = ac_date;
		this.rezobr = rezobr;
		this.f_mkb_usl = f_mkb_usl;
	}
	public Wrap3a_b_Expertise() {
		
	}
	
	private String fio;
	private Date dr;
	private String tel;
	private Long smoid;
	private String serpolis;
	private String numpolis;
	private String lpu;
	private String ambkarta;
	private Date dat_beg;
	private Date dat_end;
	private Long lpu_prik;
	private Long s1;
	private Long account;
	private Date ac_date;
	private String rezobr;
	private String f_mkb_usl;
	private Long id;
	
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public Date getDr() {
		return dr;
	}
	public void setDr(Date dr) {
		this.dr = dr;
	}
	public Long getSmoid() {
		return smoid;
	}
	public void setSmoid(Long smoid) {
		this.smoid = smoid;
	}
	public String getSerpolis() {
		return serpolis;
	}
	public void setSerpolis(String serpolis) {
		this.serpolis = serpolis;
	}
	public String getNumpolis() {
		return numpolis;
	}
	public void setNumpolis(String numpolis) {
		this.numpolis = numpolis;
	}
	public String getLpu() {
		return lpu;
	}
	public void setLpu(String lpu) {
		this.lpu = lpu;
	}
	public String getAmbkarta() {
		return ambkarta;
	}
	public void setAmbkarta(String ambkarta) {
		this.ambkarta = ambkarta;
	}
	public Date getDat_beg() {
		return dat_beg;
	}
	public void setDat_beg(Date dat_beg) {
		this.dat_beg = dat_beg;
	}
	public Date getDat_end() {
		return dat_end;
	}
	public void setDat_end(Date dat_end) {
		this.dat_end = dat_end;
	}
	public Long getLpu_prik() {
		return lpu_prik;
	}
	public void setLpu_prik(Long lpu_prik) {
		this.lpu_prik = lpu_prik;
	}
	public Long getS1() {
		return s1;
	}
	public void setS1(Long s1) {
		this.s1 = s1;
	}
	public Long getAccount() {
		return account;
	}
	public void setAccount(Long account) {
		this.account = account;
	}
	public Date getAc_date() {
		return ac_date;
	}
	public void setAc_date(Date ac_date) {
		this.ac_date = ac_date;
	}
	public String getRezobr() {
		return rezobr;
	}
	public void setRezobr(String rezobr) {
		this.rezobr = rezobr;
	}
	public String getF_mkb_usl() {
		return f_mkb_usl;
	}
	public void setF_mkb_usl(String f_mkb_usl) {
		this.f_mkb_usl = f_mkb_usl;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
