package com.careful.clinic.rs.questions;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.careful.clinic.dao.questions.QuestionsDAO;
import com.careful.clinic.model.questions.Questions;

@javax.ws.rs.Path("/questions")
public class RestQuestons {

	@EJB
	QuestionsDAO questionsDao;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> getCustomerObj() {
		List<Questions> df = (List<Questions>)questionsDao.getAll();
		System.out.println("DDDDDDD "+df.get(0).getQuestion());
		
        return questionsDao.getAll();
    }
}
