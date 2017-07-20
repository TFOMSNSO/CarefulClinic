package com.careful.clinic.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PERSON database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p"),
@NamedQuery(name="Person.findByFIOD", query="SELECT c FROM Person c WHERE c.personSurname = :personSurname "
												     + "and c.personKindfirstname =:personKindfirstname and"
												     + " c.personKindlastname =:personKindlastname and "
												     + "c.personBirthday =:personBirthday")
})
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String enp;

	@Temporal(TemporalType.DATE)
	@Column(name="PERSON_BIRTHDAY")
	private Date personBirthday;

	@Temporal(TemporalType.DATE)
	@Column(name="PERSON_DATECHANGE")
	private Date personDatechange;

	@Temporal(TemporalType.DATE)
	@Column(name="PERSON_DATEINPUT")
	private Date personDateinput;

	@Temporal(TemporalType.DATE)
	@Column(name="PERSON_DATEPOLICY")
	private Date personDatepolicy;

	@Column(name="PERSON_DOCPERSONID")
	private BigDecimal personDocpersonid;

	@Column(name="PERSON_ESTABLISHMENTAMBUL")
	private BigDecimal personEstablishmentambul;

	@Column(name="PERSON_ESTABLISHMENTDENT")
	private BigDecimal personEstablishmentdent;

	@Column(name="PERSON_ESTABLISHMENTID")
	private BigDecimal personEstablishmentid;

	@Column(name="PERSON_INSPECTION")
	private BigDecimal personInspection;

	@Column(name="PERSON_INSPECTORID")
	private BigDecimal personInspectorid;

	@Column(name="PERSON_KINDFIRSTNAME")
	private String personKindfirstname;

	@Column(name="PERSON_KINDLASTNAME")
	private String personKindlastname;

	@Column(name="PERSON_LINKSMOESTABLISHMENTID")
	private BigDecimal personLinksmoestablishmentid;

	@Column(name="PERSON_NUMDOC")
	private String personNumdoc;

	@Column(name="PERSON_NUMPOLICY")
	private String personNumpolicy;

	@Column(name="PERSON_OPERATION")
	private BigDecimal personOperation;

	@Column(name="PERSON_OUTID")
	private BigDecimal personOutid;

	@Column(name="PERSON_REGNUMBER")
	private String personRegnumber;

	@Column(name="PERSON_SERDOC")
	private String personSerdoc;

	@Column(name="PERSON_SERPOLICY")
	private String personSerpolicy;

	@Column(name="PERSON_SEX")
	private String personSex;

	@Column(name="PERSON_SOCIALID")
	private BigDecimal personSocialid;

	@Column(name="PERSON_STATUSID")
	private BigDecimal personStatusid;

	@Column(name="PERSON_STATUSREC")
	private BigDecimal personStatusrec;

	@Column(name="PERSON_SURNAME")
	private String personSurname;

	@Column(name="SMO_OLD")
	private BigDecimal smoOld;

	//bi-directional one-to-one association to Personadd
	@OneToOne
	@JoinColumn(name="PERSON_ADDRESSID")
	private Personadd personadd;

	public Person() {
	}

	public String getEnp() {
		return this.enp;
	}

	public void setEnp(String enp) {
		this.enp = enp;
	}

	public Date getPersonBirthday() {
		return this.personBirthday;
	}

	public void setPersonBirthday(Date personBirthday) {
		this.personBirthday = personBirthday;
	}

	public Date getPersonDatechange() {
		return this.personDatechange;
	}

	public void setPersonDatechange(Date personDatechange) {
		this.personDatechange = personDatechange;
	}

	public Date getPersonDateinput() {
		return this.personDateinput;
	}

	public void setPersonDateinput(Date personDateinput) {
		this.personDateinput = personDateinput;
	}

	public Date getPersonDatepolicy() {
		return this.personDatepolicy;
	}

	public void setPersonDatepolicy(Date personDatepolicy) {
		this.personDatepolicy = personDatepolicy;
	}

	public BigDecimal getPersonDocpersonid() {
		return this.personDocpersonid;
	}

	public void setPersonDocpersonid(BigDecimal personDocpersonid) {
		this.personDocpersonid = personDocpersonid;
	}

	public BigDecimal getPersonEstablishmentambul() {
		return this.personEstablishmentambul;
	}

	public void setPersonEstablishmentambul(BigDecimal personEstablishmentambul) {
		this.personEstablishmentambul = personEstablishmentambul;
	}

	public BigDecimal getPersonEstablishmentdent() {
		return this.personEstablishmentdent;
	}

	public void setPersonEstablishmentdent(BigDecimal personEstablishmentdent) {
		this.personEstablishmentdent = personEstablishmentdent;
	}

	public BigDecimal getPersonEstablishmentid() {
		return this.personEstablishmentid;
	}

	public void setPersonEstablishmentid(BigDecimal personEstablishmentid) {
		this.personEstablishmentid = personEstablishmentid;
	}

	public BigDecimal getPersonInspection() {
		return this.personInspection;
	}

	public void setPersonInspection(BigDecimal personInspection) {
		this.personInspection = personInspection;
	}

	public BigDecimal getPersonInspectorid() {
		return this.personInspectorid;
	}

	public void setPersonInspectorid(BigDecimal personInspectorid) {
		this.personInspectorid = personInspectorid;
	}

	public String getPersonKindfirstname() {
		return this.personKindfirstname;
	}

	public void setPersonKindfirstname(String personKindfirstname) {
		this.personKindfirstname = personKindfirstname;
	}

	public String getPersonKindlastname() {
		return this.personKindlastname;
	}

	public void setPersonKindlastname(String personKindlastname) {
		this.personKindlastname = personKindlastname;
	}

	public BigDecimal getPersonLinksmoestablishmentid() {
		return this.personLinksmoestablishmentid;
	}

	public void setPersonLinksmoestablishmentid(BigDecimal personLinksmoestablishmentid) {
		this.personLinksmoestablishmentid = personLinksmoestablishmentid;
	}

	public String getPersonNumdoc() {
		return this.personNumdoc;
	}

	public void setPersonNumdoc(String personNumdoc) {
		this.personNumdoc = personNumdoc;
	}

	public String getPersonNumpolicy() {
		return this.personNumpolicy;
	}

	public void setPersonNumpolicy(String personNumpolicy) {
		this.personNumpolicy = personNumpolicy;
	}

	public BigDecimal getPersonOperation() {
		return this.personOperation;
	}

	public void setPersonOperation(BigDecimal personOperation) {
		this.personOperation = personOperation;
	}

	public BigDecimal getPersonOutid() {
		return this.personOutid;
	}

	public void setPersonOutid(BigDecimal personOutid) {
		this.personOutid = personOutid;
	}

	public String getPersonRegnumber() {
		return this.personRegnumber;
	}

	public void setPersonRegnumber(String personRegnumber) {
		this.personRegnumber = personRegnumber;
	}

	public String getPersonSerdoc() {
		return this.personSerdoc;
	}

	public void setPersonSerdoc(String personSerdoc) {
		this.personSerdoc = personSerdoc;
	}

	public String getPersonSerpolicy() {
		return this.personSerpolicy;
	}

	public void setPersonSerpolicy(String personSerpolicy) {
		this.personSerpolicy = personSerpolicy;
	}

	public String getPersonSex() {
		return this.personSex;
	}

	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}

	public BigDecimal getPersonSocialid() {
		return this.personSocialid;
	}

	public void setPersonSocialid(BigDecimal personSocialid) {
		this.personSocialid = personSocialid;
	}

	public BigDecimal getPersonStatusid() {
		return this.personStatusid;
	}

	public void setPersonStatusid(BigDecimal personStatusid) {
		this.personStatusid = personStatusid;
	}

	public BigDecimal getPersonStatusrec() {
		return this.personStatusrec;
	}

	public void setPersonStatusrec(BigDecimal personStatusrec) {
		this.personStatusrec = personStatusrec;
	}

	public String getPersonSurname() {
		return this.personSurname;
	}

	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}

	public BigDecimal getSmoOld() {
		return this.smoOld;
	}

	public void setSmoOld(BigDecimal smoOld) {
		this.smoOld = smoOld;
	}

	public Personadd getPersonadd() {
		return this.personadd;
	}

	public void setPersonadd(Personadd personadd) {
		this.personadd = personadd;
	}

}