package com.careful.clinic.rs.prophylactic;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.careful.clinic.dao.prophylactic.ProphylacticDAO;
import com.careful.clinic.dao.questions.QuestionsDAO;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.questions.Questions;


@javax.ws.rs.Path("/prophylactic")
public class RestServiceProphylactic {
	
	@EJB
	ProphylacticDAO prophylacticDAO;
	
	
	@POST
	@Path("/search_person_ger")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchGer(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoProphylactic(personmodel);
		
		return df;

	}

}
