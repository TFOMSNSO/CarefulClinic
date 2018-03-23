package com.careful.clinic.model;

public class ExpertiseRateMo {
	
	public ExpertiseRateMo(String code_mo, String name_mo, Long total, Long total_1, Long total_2, Long total_4) {
		super();
		this.code_mo = code_mo;
		this.name_mo = name_mo;
		this.total = total;
		this.total_1 = total_1;
		this.total_2 = total_2;
		this.total_4 = total_4;
	}


	private String code_mo;
	private String name_mo;
	private Long total;
	private Long total_1;
	private Long total_2;
	private Long total_4;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expertise3aRateMo [code_mo=");
		builder.append(code_mo);
		builder.append(", name_mo=");
		builder.append(name_mo);
		builder.append(", total=");
		builder.append(total);
		builder.append(", total_1=");
		builder.append(total_1);
		builder.append(", total_2=");
		builder.append(total_2);
		builder.append(", total_4=");
		builder.append(total_4);
		builder.append("]");
		return builder.toString();
	}


	public String getCode_mo() {
		return code_mo;
	}


	public void setCode_mo(String code_mo) {
		this.code_mo = code_mo;
	}


	public String getName_mo() {
		return name_mo;
	}


	public void setName_mo(String name_mo) {
		this.name_mo = name_mo;
	}


	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	public Long getTotal_1() {
		return total_1;
	}


	public void setTotal_1(Long total_1) {
		this.total_1 = total_1;
	}


	public Long getTotal_2() {
		return total_2;
	}


	public void setTotal_2(Long total_2) {
		this.total_2 = total_2;
	}


	public Long getTotal_4() {
		return total_4;
	}


	public void setTotal_4(Long total_4) {
		this.total_4 = total_4;
	}
	
	
	

}
