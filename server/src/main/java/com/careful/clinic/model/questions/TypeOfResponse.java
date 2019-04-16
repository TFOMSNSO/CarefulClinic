package com.careful.clinic.model.questions;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TypeOfResponse")
public class TypeOfResponse  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type_response;
	private Questions questions;
	
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType_response() {
		return type_response;
	}
	public void setType_response(String type_response) {
		this.type_response = type_response;
	}
	
	@ManyToOne
    @JoinColumn(name = "question_tab_id")
	@JsonIgnore
	public Questions getQuestions() {
		return questions;
	}
	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
	
	

}
