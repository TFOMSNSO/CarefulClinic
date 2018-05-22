package com.careful.clinic.rs.sp3.inform.d.reestr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.careful.clinic.rs.dao.sp3.inform.d.reestr.D_reestr;

@Path("/sp3/d_reestr")
public class RestServiceSp3InformDReestr {
	
	@EJB
	private D_reestr d_reestr_dao;
	
	@GET
	@Path("/d_reestr/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesDReestri(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) d_reestr_dao.getListDReestr(id);
		
		return df;
	}
	
	
	@GET
	@Path("/info_d_reestr/{place}/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/vnd.ms-excel")
	public Response getdownloadFile(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {
		
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		
		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		
		if(id == 777) directoryDestination = "\\content\\report\\sp3\\"+place+"\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\sp3\\"+place+"\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\sp3\\"+place+"\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\sp3\\"+place+"\\4";	
		
		
		
		directoryDestination = directoryServer+directoryDestination+File.separator+namefile;
		
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
