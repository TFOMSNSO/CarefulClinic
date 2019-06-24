package com.careful.clinic.model;

public class ListExcelFiles {
	public ListExcelFiles(String namefile, String fullpathfile) {
		super();
		this.namefile = namefile;
		this.fullpathfile = fullpathfile;
	}

	public ListExcelFiles(String namefile, String fullpathfile,String description) {
		super();
		this.namefile = namefile;
		this.fullpathfile = fullpathfile;
		this.description = description;
	}

	public ListExcelFiles() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String namefile;
	private String fullpathfile;
	private String description;
	
	public String getNamefile() {
		return namefile;
	}
	public void setNamefile(String namefile) {
		this.namefile = namefile;
	}
	public String getFullpathfile() {
		return fullpathfile;
	}
	public void setFullpathfile(String fullpathfile) {
		this.fullpathfile = fullpathfile;
	}

	public String getDescriotion() {
		return description;
	}

	public void setDescriotion(String descriotion) {
		this.description = descriotion;
	}
}
