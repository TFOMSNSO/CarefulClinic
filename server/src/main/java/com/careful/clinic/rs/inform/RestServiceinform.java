package com.careful.clinic.rs.inform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.careful.clinic.dao.inform.InformDAO;
import com.careful.clinic.model.ListExcelFiles;


@javax.ws.rs.Path("/inform")
public class RestServiceinform {

	@EJB
	InformDAO informDAO;

	/**
	 * Метод проверяет наличие файлов на каталоге.
	 * @param id каталога
	 * @return список файлов
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@GET
	@Path("/listFilesSecondStageInform/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesSecondStageInform(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

		List<?> df = (List<?>) informDAO.getListInformSecondStage(id);

		return df;
	}

	@GET
	@Path("/listFilesProfMedOsmotri/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesProfMedOsmotri(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

		List<?> df = (List<?>) informDAO.getListInformMedOsmotri(id);

		return df;
	}

	@GET
	@Path("/listFilesInformKvartals/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesInformKvartals(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

		List<?> df = (List<?>) informDAO.getListInformKvartal(id);

		return df;
	}

	@GET
	@Path("/listFilesInformKvartalsActual/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesInformKvartalsActual(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

		List<?> df = (List<?>) informDAO.getListInformKvartalActual(id);

		return df;
	}

	@GET
	@Path("/listFilesreinform/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesInformReinform(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

		List<?> df = (List<?>) informDAO.getListInformReinform(id);

		return df;
	}

	@GET
	@Path("/download/{place}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/x-rar-compressed")
	public Response getdownloadFile(@PathParam("place") String place, @PathParam("id") Integer id) {

		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";

		if(place.equals("inform_for_all_year")){
			if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_for_all_year\\777";
			if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_for_all_year\\1";
			if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_for_all_year\\2";
			if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_for_all_year\\4";
		}

		directoryDestination = directoryServer+directoryDestination;

		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
		}

		//todo: throw exeption if in list inteface more than 1 file

		directoryDestination = directoryDestination+File.separator+ls.get(0).getNamefile();

		File file = new File(directoryDestination);
		try {
			String contentType = "application/x-rar-compressed";
			Files.probeContentType(file.toPath());

			Response.ResponseBuilder response = Response.ok(file);
			response.header("Content-Disposition", "attachment; filename="+file.getName());
			response.header("Content-Type", contentType);
			response.header("Content-Length", file.length());
			response.header("file_n", file.getName());
			return response.build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	// TODO  refactor  methods with 	/listFilesSecondStageInform. Shared logic
	@GET
	@Path("/listFilesSecondStageInform/{place}/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/vnd.ms-excel")
	public Response getdownloadFile(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";

		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\4";



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

	@GET
	@Path("/listFilesActualInform/{place}/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/vnd.ms-excel")
	public Response getdownloadFileActualInform(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";

		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\"+ place +"\\4";



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


	@GET
	@Path("/listFilesInformReinfrom/{place}/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/x-rar-compressed")
	public Response listFilesInformReiform(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";

		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year+"\\reinform\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\reinform\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\reinform\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\reinform\\4";


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



	@GET
	@Path("/listFilesInformKvartals/{place}/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/x-rar-compressed")
	public Response listFilesInformKvartals(@PathParam("place") String place, @PathParam("id") Integer id, @PathParam("namefile") String namefile) {

		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(place.equals("inform_about_second_stage")){
			if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_kvartals\\777";
			if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_kvartals\\1";
			if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_kvartals\\2";
			if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year+"\\inform_kvartals\\4";
		}


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
