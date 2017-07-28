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
import javax.ws.rs.PathParam;
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
	
	
	/**
	 * Одновременный поиск в ГЭР и РСЕРЗ
	 * @param personmodel критерии поиска
	 * @return Коллекция, где первым элементом является объект из базы застрахованных, вторым элементом объект из гэра  
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_person_ger")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchGer(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoProphylactic(personmodel);
		
		return df;

	}
	
	
	/**
	 * Поиск в базе застрахованных (РС ЕРЗ)
	 * @param personmodel критерии поиска
	 * @return объект из базы застрахованных.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_person_insur")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchInsur(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoInsur(personmodel);
		
		return	df;
		

	}
	
	
	
	/**
	 * Поиск в базе АИС ГЭР 
	 * @param personmodel критерии поиска
	 * @return объект из базы АИС ГЭР.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_ger")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchG(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoG(personmodel);
		
		return df;

	}

	/**
	 * Поиск в базе информированных
	 * @param personmodel критерии поиска
	 * @return объект из базы информированных
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@POST
	@Path("/search_informed")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchInform(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		return prophylacticDAO.getInfoInform(personmodel);
	}
	
	
	@POST
	@Path("/search_plan_informed/{adressid}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchPlanInform(@PathParam("adressid") String adressid) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoPlanInform(Integer.valueOf(adressid));
		
		return df;

	}

}
