package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PM_I database table.
 * 
 */

@Entity
@Table(name="PM_I")
@NamedQueries({
@NamedQuery(name="PmI.findByFIOD", query="SELECT c FROM PmI c WHERE c.fam = :fam "
												     + "and c.im =:im and"
												     + " c.ot =:ot and "
												     + "c.dr =:dr order by c.dInfo desc")
})
public class PmI implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Temporal(TemporalType.DATE)
	@Column(name="D_INFO")
	private Date dInfo;

	@Id
	@Column(name="D_INSERT")
	private Date dInsert;

	@Temporal(TemporalType.DATE)
	@Column(name="\"DATA\"")
	private Date data;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy", timezone = "Asia/Novosibirsk")
	private Date dr;
	
	private String fam;
	
	private Integer id;

	private String im;

	@Column(name="N_STAGE")
	private Integer nStage;

	private String ot;

	private String prim;

	private BigDecimal smo;

	@Column(name="T_INFO")
	private BigDecimal tInfo;

	public PmI() {
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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIm() {
		return this.im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public Integer getNStage() {
		return this.nStage;
	}

	public void setNStage(Integer nStage) {
		this.nStage = nStage;
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

	public BigDecimal getTInfo() {
		return this.tInfo;
	}

	public void setTInfo(BigDecimal tInfo) {
		this.tInfo = tInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PmI [dInfo=");
		builder.append(dInfo);
		builder.append(", dInsert=");
		builder.append(dInsert);
		builder.append(", data=");
		builder.append(data);
		builder.append(", dr=");
		builder.append(dr);
		builder.append(", fam=");
		builder.append(fam);
		builder.append(", id=");
		builder.append(id);
		builder.append(", im=");
		builder.append(im);
		builder.append(", nStage=");
		builder.append(nStage);
		builder.append(", ot=");
		builder.append(ot);
		builder.append(", prim=");
		builder.append(prim);
		builder.append(", smo=");
		builder.append(smo);
		builder.append(", tInfo=");
		builder.append(tInfo);
		builder.append("]");
		return builder.toString();
	}

}