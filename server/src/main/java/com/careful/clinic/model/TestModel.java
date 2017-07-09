package com.careful.clinic.model;

import java.util.Arrays;

public class TestModel {
	String id;
	WrapTest2[] ques;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public WrapTest2[] getQues() {
		return ques;
	}
	public void setQues(WrapTest2[] ques) {
		this.ques = ques;
	}
	@Override
	public String toString() {
		return "TestModel [id=" + id + ", ques=" + Arrays.toString(ques) + "]";
	}
	
	
}

class WrapTest2{
	String id;
	String question;
	
	@Override
	public String toString() {
		return "WrapTest2 [id=" + id + ", question=" + question + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
