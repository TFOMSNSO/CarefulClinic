package com.careful.clinic.rs.prophylactic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.careful.clinic.dao.prophylactic.ProphylacticDAO;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.SearchKeysModel;
import com.careful.clinic.model.WrapRespSerarchKeys;


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
	 * Поиск застрахованных по ключам
	 * @param personmodel - объект с заданными параметрами для поиска в базе
	 * @return - застрахованные лица которые удавлетворяют поиску
	 * @throws Exception 
	 */
	@POST
	@Path("/search_person_keys")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchInsurkeys(SearchKeysModel personmodel) throws Exception {
		List<?> df = (List<?>) prophylacticDAO.getInfoInsurkeys(personmodel);
		
		return	df;
	}
	
	/**
	 * Экспорт в эксель через кнопку выгрузить в эксель
	 * @param wrapRespSerarchKeys
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/exportToexcel")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> exportToExcel(ArrayList<WrapRespSerarchKeys> wrapRespSerarchKeys) throws Exception {
		
		List<?> df = (List<?>) prophylacticDAO.exportToExcel(wrapRespSerarchKeys);
		
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
	@Path("/survey_inform")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> surveyInform(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		return prophylacticDAO.getSurveyInform(personmodel);
	}
	
	
	@POST
	@Path("/search_plan_informed/{adressid}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<?> searchPlanInform(@PathParam("adressid") String adressid) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) prophylacticDAO.getInfoPlanInform(Integer.valueOf(adressid));
		
		return df;

	}



/**
 * 
 * @param id
 * @return возвращает список имен файлов xlsx
 * @throws ParserConfigurationException
 * @throws SAXException
 * @throws IOException
 * @throws ParseException
 */
@GET
@Path("/listExcelFiles/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Collection<?> listExcelFiles(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {
	
	List<?> df = (List<?>) prophylacticDAO.getListExcelFiles(id);
	
	return df;
}

@GET
@Path("/download/{id}/{namefile}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
public Response getdownloadFile(@PathParam("id") Integer id, @PathParam("namefile") String namefile) {
	
	String directoryServer = System.getProperty("jboss.home.dir");
	String directoryDestination = "";
	if(id == 777) directoryDestination = "\\content\\donwload\\777";
	if(id == 1)	directoryDestination = "\\content\\donwload\\1";
	if(id == 2)	directoryDestination = "\\content\\donwload\\2";
	if(id == 4)	directoryDestination = "\\content\\donwload\\4";
	
	directoryDestination = directoryServer+directoryDestination+File.separator+namefile+".xlsx";
	
    File file = new File(directoryDestination);
    try {
        String contentType = Files.probeContentType(file.toPath());
        
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment; filename="+file.getName());
        response.header("Content-Type", contentType);
        response.header("Content-Length", file.length());
        return response.build();
    } catch (IOException e) {
        e.printStackTrace();
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
}


}