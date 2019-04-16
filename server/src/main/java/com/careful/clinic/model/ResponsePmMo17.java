package com.careful.clinic.model;


public class ResponsePmMo17 {
	public ResponsePmMo17(String tfoms_id, String god, String kv, String date_begin, String date_end) {
		this.tfoms_id = tfoms_id;
		this.god = god;
		this.kv = kv;
		this.date_begin = date_begin;
		this.date_end = date_end;
	}
	private String tfoms_id;
	private String god;
	private String kv;
	private String date_begin;
	private String date_end;
	
	public String getGod() {
		return god;
	}
	public void setGod(String god) {
		this.god = god;
	}
	public String getKv() {
		return kv;
	}
	public void setKv(String kv) {
		this.kv = kv;
	}
	public String getDate_begin() {
		return date_begin;
	}
	public void setDate_begin(String date_begin) {
		this.date_begin = date_begin;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	
	
	public String getTfoms_id() {
		return tfoms_id;
	}
	public void setTfoms_id(String tfoms_id) {
		this.tfoms_id = tfoms_id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponsePmMo17 [tfoms_id=");
		builder.append(tfoms_id);
		builder.append(", god=");
		builder.append(god);
		builder.append(", kv=");
		builder.append(kv);
		builder.append(", date_begin=");
		builder.append(date_begin);
		builder.append(", date_end=");
		builder.append(date_end);
		builder.append("]");
		return builder.toString();
	}

}
