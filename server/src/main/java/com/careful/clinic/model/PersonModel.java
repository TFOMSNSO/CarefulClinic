package com.careful.clinic.model;

public class PersonModel {
	private String surname; 
	private String firstname;
	private String lastname;
	
	public PersonModel() {
		
	}
	private String bithday;
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBithday() {
		return bithday;
	}
	public void setBithday(String bithday) {
		this.bithday = bithday;
	}
	@Override
	public String toString() {
		return "PersonModel [surname=" + surname + ", firstname=" + firstname + ", lastname=" + lastname + ", bythday="
				+ bithday + "]";
	}
	
	
}
