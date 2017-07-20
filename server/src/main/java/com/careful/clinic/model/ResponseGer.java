package com.careful.clinic.model;


public class ResponseGer{
	
	 public ResponseGer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseGer(String start_date_etap1, String end_date_etap1, String start_date_etap2, String end_date_etap2,
			String ref_id_person, String pm_god, String pm_kvartal, String pM_HOSPITAL_RESULT, String adress,
			String tel, String pm_result) {
		super();
		this.start_date_etap1 = start_date_etap1;
		this.end_date_etap1 = end_date_etap1;
		this.start_date_etap2 = start_date_etap2;
		this.end_date_etap2 = end_date_etap2;
		this.ref_id_person = ref_id_person;
		this.pm_god = pm_god;
		this.pm_kvartal = pm_kvartal;
		PM_HOSPITAL_RESULT = pM_HOSPITAL_RESULT;
		this.adress = adress;
		this.tel = tel;
		this.pm_result = pm_result;
	}
	private String start_date_etap1;
	 private String end_date_etap1;
	 private String start_date_etap2;
	 private String end_date_etap2;
	 private String ref_id_person;
	 private String pm_god;
	 private String pm_kvartal;
	 private String PM_HOSPITAL_RESULT;
	 private String adress;
	 private String tel;
	 private String pm_result;
	 
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseGer [start_date_etap1=");
		builder.append(start_date_etap1);
		builder.append(", end_date_etap1=");
		builder.append(end_date_etap1);
		builder.append(", start_date_etap2=");
		builder.append(start_date_etap2);
		builder.append(", end_date_etap2=");
		builder.append(end_date_etap2);
		builder.append(", ref_id_person=");
		builder.append(ref_id_person);
		builder.append(", pm_god=");
		builder.append(pm_god);
		builder.append(", pm_kvartal=");
		builder.append(pm_kvartal);
		builder.append(", PM_HOSPITAL_RESULT=");
		builder.append(PM_HOSPITAL_RESULT);
		builder.append(", adress=");
		builder.append(adress);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", pm_result=");
		builder.append(pm_result);
		builder.append("]");
		return builder.toString();
	}
	public String getStart_date_etap1() {
		return start_date_etap1;
	}
	public void setStart_date_etap1(String start_date_etap1) {
		this.start_date_etap1 = start_date_etap1;
	}
	public String getEnd_date_etap1() {
		return end_date_etap1;
	}
	public void setEnd_date_etap1(String end_date_etap1) {
		this.end_date_etap1 = end_date_etap1;
	}
	public String getStart_date_etap2() {
		return start_date_etap2;
	}
	public void setStart_date_etap2(String start_date_etap2) {
		this.start_date_etap2 = start_date_etap2;
	}
	public String getEnd_date_etap2() {
		return end_date_etap2;
	}
	public void setEnd_date_etap2(String end_date_etap2) {
		this.end_date_etap2 = end_date_etap2;
	}
	public String getRef_id_person() {
		return ref_id_person;
	}
	public void setRef_id_person(String ref_id_person) {
		this.ref_id_person = ref_id_person;
	}
	public String getPm_god() {
		return pm_god;
	}
	public void setPm_god(String pm_god) {
		this.pm_god = pm_god;
	}
	public String getPm_kvartal() {
		return pm_kvartal;
	}
	public void setPm_kvartal(String pm_kvartal) {
		this.pm_kvartal = pm_kvartal;
	}
	public String getPM_HOSPITAL_RESULT() {
		return PM_HOSPITAL_RESULT;
	}
	public void setPM_HOSPITAL_RESULT(String pM_HOSPITAL_RESULT) {
		PM_HOSPITAL_RESULT = pM_HOSPITAL_RESULT;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPm_result() {
		return pm_result;
	}
	public void setPm_result(String pm_result) {
		this.pm_result = pm_result;
	}
	 
	 

}
