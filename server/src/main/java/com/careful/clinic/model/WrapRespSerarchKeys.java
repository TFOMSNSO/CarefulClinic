package com.careful.clinic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WrapRespSerarchKeys implements Serializable{
	
	public WrapRespSerarchKeys() {
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrapRespSerarchKeys(String surname, String firstname, String lastname, String bithday, Integer personLinksmoestablishmentid, String personEstablishmentambul, String tele2 , String teledom, String telework,Integer currentUser) {
		this.personSurname = surname;
		this.personKindfirstname = firstname;
		this.personKindlastname = lastname;
		this.personBirthday = bithday;
		this.personLinksmoestablishmentid = personLinksmoestablishmentid;
		this.personEstablishmentambul = personEstablishmentambul; 
		this.personadd = new WrapRespSerarchKeysForPersonadd(tele2,teledom,telework);
		this.currentUser = currentUser;
	}
	private String personSurname; 
	private String personKindfirstname;
	private String personKindlastname;
	private String personBirthday;
	private Integer personLinksmoestablishmentid;
	private String personEstablishmentambul;
	private WrapRespSerarchKeysForPersonadd personadd;
	private String years;
	private Integer currentUser;
	@JsonIgnore
	private String count_pmIstages;
	@JsonIgnore
	private String pma_d_info;
	@JsonIgnore
	private String pma_type_info;
	@JsonIgnore
	private String pma_smo;
	
	@JsonIgnore
	public String  getTele2(){
		return personadd.getTele2();
	}
	@JsonIgnore
	public String  getTeleWork(){
		return personadd.getTelework();
	}
	@JsonIgnore
	public String  getTeleDom(){
		return personadd.getTeledom();
	}
	
	//@JsonProperty("respGerl")
	//@JsonIgnore
	protected List<ResponseGer> respGerl = new ArrayList<ResponseGer>();
	@JsonIgnore
	protected List<ResponsePmMo17> respPlan = new ArrayList<ResponsePmMo17>();
	
	
	public List<ResponsePmMo17> getRespPlan() {
		return respPlan;
	}
	public void setRespPlan(ResponsePmMo17 respPlan) {
		this.respPlan.add(respPlan);
	}
	
	public List<ResponseGer> getRespGerl() {
		return respGerl;
	}
	public void updateRespGerl(ResponseGer respGerl) {
		this.respGerl.add(respGerl);
	}
	
	public void setRespGerl(List<ResponseGer> respGerl) {
		this.respGerl = respGerl;
	}

	
	public Integer getPersonLinksmoestablishmentid() {
		return personLinksmoestablishmentid;
	}
	public void setPersonLinksmoestablishmentid(Integer personLinksmoestablishmentid) {
		this.personLinksmoestablishmentid = personLinksmoestablishmentid;
	}
	public String getPersonEstablishmentambul() {
		return personEstablishmentambul;
	}
	public void setPersonEstablishmentambul(String personEstablishmentambul) {
		this.personEstablishmentambul = personEstablishmentambul;
	}
	public WrapRespSerarchKeysForPersonadd getPersonadd() {
		return personadd;
	}
	public void setPersonadd(WrapRespSerarchKeysForPersonadd personadd) {
		this.personadd = personadd;
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
	public String getPersonBirthday() {
		return personBirthday;
	}
	public void setPersonBirthday(String personBirthday) {
		this.personBirthday = personBirthday;
	}
	
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public Integer getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(Integer currentUser) {
		this.currentUser = currentUser;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WrapRespSerarchKeys [personSurname=");
		builder.append(personSurname);
		builder.append(", personKindfirstname=");
		builder.append(personKindfirstname);
		builder.append(", personKindlastname=");
		builder.append(personKindlastname);
		builder.append(", personBirthday=");
		builder.append(personBirthday);
		builder.append(", personLinksmoestablishmentid=");
		builder.append(personLinksmoestablishmentid);
		builder.append(", personEstablishmentambul=");
		builder.append(personEstablishmentambul);
		builder.append(", personadd=");
		builder.append(personadd);
		builder.append(", years=");
		builder.append(years);
		builder.append(", currentUser=");
		builder.append(currentUser);
		builder.append(", respGerl=");
		builder.append(respGerl);
		builder.append("]");
		return builder.toString();
	}
	public String getCount_pmIstages() {
		return count_pmIstages;
	}
	public void setCount_pmIstages(String count_pmIstages1,String count_pmIstages2,String count_pmIstages3) {
		StringBuilder sb = new StringBuilder();
		sb.append(count_pmIstages1);
		sb.append("; ");
		sb.append(count_pmIstages2);
		sb.append("; ");
		sb.append(count_pmIstages3);
		this.count_pmIstages = sb.toString();
	}
	public String getPma_d_info() {
		return pma_d_info;
	}
	public void setPma_d_info(String pma_d_info) {
		this.pma_d_info = pma_d_info;
	}
	public String getPma_type_info() {
		return pma_type_info;
	}
	public void setPma_type_info(String pma_type_info) {
		this.pma_type_info = pma_type_info;
	}
	public String getPma_smo() {
		return pma_smo;
	}
	public void setPma_smo(String pma_smo) {
		this.pma_smo = pma_smo;
	}
	
	
}

 class WrapRespSerarchKeysForPersonadd  implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrapRespSerarchKeysForPersonadd() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WrapRespSerarchKeysForPersonadd(String tele2, String teledom, String telework) {
		this.tele2 = tele2;
		this.teledom = teledom;
		this.telework = telework;
	}
	
     private String tele2;
	  private String teledom;;
	  private String telework;
	  
	public String getTele2() {
		return tele2;
	}
	public void setTele2(String tele2) {
		this.tele2 = tele2;
	}
	public String getTeledom() {
		return teledom;
	}
	public void setTeledom(String teledom) {
		this.teledom = teledom;
	}
	public String getTelework() {
		return telework;
	}
	public void setTelework(String telework) {
		this.telework = telework;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WrapRespSerarchKeysForPersonadd [tele2=");
		builder.append(tele2);
		builder.append(", teledom=");
		builder.append(teledom);
		builder.append(", telework=");
		builder.append(telework);
		builder.append("]");
		return builder.toString();
	}

	
}