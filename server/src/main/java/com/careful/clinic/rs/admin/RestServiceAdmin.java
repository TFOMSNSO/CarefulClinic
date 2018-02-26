package com.careful.clinic.rs.admin;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.careful.clinic.model.ListExcelFiles;

@javax.ws.rs.Path("/admin")
public class RestServiceAdmin {
	
	@GET
	@Path("/listFiles")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFiles() throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		//List<?> df = (List<?>) informDAO.getListInformSecondStage(id);
		String directoryServer = System.getProperty("jboss.home.dir");
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		
		ls.add(new ListExcelFiles("Загруженные данные", "/content/upload"));
		ls.add(new ListExcelFiles("Выгруженные данные", "/content/donwload"));
		ls.add(new ListExcelFiles("Информирование", "/content/report/informing"));
		
		
		return ls;
	}
	
	@POST
	@Path("/listFilesBrowser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesBrowser(String place)  {
		
		//List<?> df = (List<?>) informDAO.getListInformSecondStage(id);
		String directoryServer = System.getProperty("jboss.home.dir");
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		
		System.out.println("directoryServer_place "+directoryServer+place.replaceAll("/", "\\\\").replaceAll("\"", ""));
		File path = new File(directoryServer+place.replaceAll("/", "\\\\").replaceAll("\"", ""));
		String ob[] = path.list();
		
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i], place.replaceAll("\"", "")+"/"+ob[i]));
		}
		
		/*ls.add(new ListExcelFiles("СимазМед", "/content/upload"));
		ls.add(new ListExcelFiles("Втб Страхование", "/content/donwload"));
		ls.add(new ListExcelFiles("ИнгосСтрах", "/content/report/informing"));
		ls.add(new ListExcelFiles("ТФОМС НСО", "/content/report/informing"));*/
		
		
		return ls;
	}

}
