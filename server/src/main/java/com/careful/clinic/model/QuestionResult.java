package com.careful.clinic.model;

import java.util.Arrays;
import java.util.List;

public class QuestionResult {
	
	String date_response;
	MassiveOfAnswers questions_list[];
	/*{"date_response":"2017-07-07T17:00:00.000Z","questions_list":[{"id":1,"question":2},{"id":2,"question":8},{"id":3,"question":11}]}*/
	
	public String getDate_response() {
		return date_response;
	}
	public void setDate_response(String date_response) {
		this.date_response = date_response;
	}
	public MassiveOfAnswers[] getQuestions_list() {
		return questions_list;
	}
	public void setQuestions_list(MassiveOfAnswers[] questions_list) {
		this.questions_list = questions_list;
	}
	@Override
	public String toString() {
		return "QuestionResult [date_response=" + date_response + ", questions_list=" + Arrays.toString(questions_list)
				+ "]";
	}
		
}


class MassiveOfAnswers{
	
	Integer id;
	Integer question;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuestion() {
		return question;
	}
	public void setQuestion(Integer question) {
		this.question = question;
	}
	
	@Override
	public String toString() {
		return "MassiveOfAnswers [id=" + id + ", question=" + question + "]";
	}
}