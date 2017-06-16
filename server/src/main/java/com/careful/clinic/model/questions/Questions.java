package com.careful.clinic.model.questions;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Questions", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Questions implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String question;
	private List<TypeOfResponse> typeOfResponse;
	
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "questions", cascade = CascadeType.ALL)
	public List<TypeOfResponse> getTypeOfResponse() {
		return typeOfResponse;
	}
	public void setTypeOfResponse(List<TypeOfResponse> typeOfResponse) {
		this.typeOfResponse = typeOfResponse;
	}
	
	
	

}
