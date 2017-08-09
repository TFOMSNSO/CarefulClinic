package com.careful.clinic.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WrapPmI {
	
	private String fam;
	private String im;
	private String ot;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
	private Date dr;
	private Integer nStage;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
	private Date dInfo;
	private BigDecimal tInfo;
	private BigDecimal smo;
	
	
	
	public WrapPmI(String fam, String im, String ot, Date dr, Integer nStage, Date dInfo, BigDecimal tInfo, BigDecimal smo) {
		super();
		this.fam = fam;
		this.im = im;
		this.ot = ot;
		this.dr = dr;
		this.nStage = nStage;
		this.dInfo = dInfo;
		this.tInfo = tInfo;
		this.smo = smo;
	}



	public WrapPmI() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getFam() {
		return fam;
	}



	public void setFam(String fam) {
		this.fam = fam;
	}



	public String getIm() {
		return im;
	}



	public void setIm(String im) {
		this.im = im;
	}



	public String getOt() {
		return ot;
	}



	public void setOt(String ot) {
		this.ot = ot;
	}



	public Date getDr() {
		return dr;
	}



	public void setDr(Date dr) {
		this.dr = dr;
	}



	public Integer getnStage() {
		return nStage;
	}



	public void setnStage(Integer nStage) {
		this.nStage = nStage;
	}



	public Date getdInfo() {
		return dInfo;
	}



	public void setdInfo(Date dInfo) {
		this.dInfo = dInfo;
	}







	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WrapPmI [fam=");
		builder.append(fam);
		builder.append(", im=");
		builder.append(im);
		builder.append(", ot=");
		builder.append(ot);
		builder.append(", dr=");
		builder.append(dr);
		builder.append(", nStage=");
		builder.append(nStage);
		builder.append(", dInfo=");
		builder.append(dInfo);
		builder.append(", tInfo=");
		builder.append(tInfo);
		builder.append(", smo=");
		builder.append(smo);
		builder.append("]");
		return builder.toString();
	}



	public BigDecimal gettInfo() {
		return tInfo;
	}



	public void settInfo(BigDecimal tInfo) {
		this.tInfo = tInfo;
	}



	public BigDecimal getSmo() {
		return smo;
	}



	public void setSmo(BigDecimal smo) {
		this.smo = smo;
	}



	@Override
	public boolean equals(Object obj) {
		 if (obj instanceof WrapPmI) {
			    
			    
	            return ((WrapPmI) obj).getFam().equals(getFam()) &&
	            		       ((WrapPmI) obj).getIm().equals(getIm()) &&
	            		       ((WrapPmI) obj).getOt().equals(getOt()) &&
	            		       ((WrapPmI) obj).getDr().compareTo(getDr()) == 0 &&
	            		       ((WrapPmI) obj).getdInfo().compareTo(getdInfo()) == 0 &&
	            		       ((WrapPmI) obj).getSmo().compareTo(getSmo()) == 0 &&
	            		       ((WrapPmI) obj).getnStage() == getnStage() &&
	            		       ((WrapPmI) obj).gettInfo().compareTo(gettInfo()) == 0;
	            		       
	            		       
	        } else {
	            return false;
	        }
	}



	@Override
	public int hashCode() {
		return Objects.hash(getFam(), getIm(), getOt(), getDr(), getnStage(), getSmo(), getdInfo() );
		}



	


}
