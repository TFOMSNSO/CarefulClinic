package com.careful.clinic.rs.questions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.careful.clinic.dao.questions.QuestionsDAO;
import com.careful.clinic.model.QuestionResult;
import com.careful.clinic.model.TestModel;
import com.careful.clinic.model.questions.Questions;
import com.careful.clinic.model.questions.TypeOfResponse;

@javax.ws.rs.Path("/questions")
public class RestQuestons {

	@EJB
	QuestionsDAO questionsDao;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<?> getCustomerObj() {
		
		List<Questions> df = (List<Questions>)questionsDao.getAll();
		List<Questions> neww = new ArrayList<Questions>(df.size());
        for (Questions currentObj : df){
        	try {
				currentObj.setQuestion(new String(currentObj.getQuestion().getBytes("windows-1251"), "UTF-8"));
				List<TypeOfResponse> df2 =  currentObj.getTypeOfResponse();
				List<TypeOfResponse> neww2 =  new ArrayList<TypeOfResponse>(df2.size());
				for (TypeOfResponse currentObj2 : df2){
					currentObj2.setType_response(new String(currentObj2.getType_response().getBytes("windows-1251"), "UTF-8"));
					neww2.add(neww2.size() == 0 ? 0 : (neww2.size()), currentObj2);
					}
				currentObj.setTypeOfResponse(neww2);
				neww.add(neww.size() == 0 ? 0 : (neww.size()),currentObj);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        
		
		
        //return questionsDao.getAll();
        return neww;
    }
	
	
	@POST
	@Path("add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public QuestionResult add(QuestionResult questionresult) {
		System.out.println("QuestionResult "+questionresult);
		return new QuestionResult();
        //return dao.create(wine);
    }
 
}
