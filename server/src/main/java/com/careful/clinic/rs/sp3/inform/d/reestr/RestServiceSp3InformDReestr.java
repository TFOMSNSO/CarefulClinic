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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.InformDReestr;
import com.careful.clinic.rs.dao.sp3.inform.d.reestr.D_reestr;
import com.careful.clinic.rs.sp3.inform.d.reestr.report.D_reestr_Report;

@Path("/sp3/d_reestr")
public class RestServiceSp3InformDReestr {

	@EJB
	private D_reestr d_reestr_dao;
	@EJB
	D_reestr_Report d_reestr_Report;

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


	@POST
	@Path("/d_report")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response report_d(String x)  {

		Response.ResponseBuilder builder = null;
		String m[] = x.split("\":\"");
		String user = Integer.parseInt(m[3].substring(0, 3).replaceAll("[\\D]", ""))+"";
		try{
			String date1 = m[1].substring(0, 10).substring(6,10)+m[1].substring(0, 10).substring(3,5);
			String date2 = m[2].substring(0, 10).substring(6,10)+m[2].substring(0, 10).substring(3,5);
			List<InformDReestr> ls = null;
			// смещение для запроса
			int iter = 0;
			// порядковый номер файла
			int i =1;
			String [] mm = {"reports/sp3/d_reestr_report/d_reestr_report.jrxml", "\\content\\report\\sp3\\d_reestr_report\\"+user+"\\Список диспансерного наблюдения_"};
			while(true){
				ls = (List<InformDReestr>) d_reestr_dao.getResalt_D_reestrCollect2018(date1, date2, user, iter);
				if (ls.size() != 0) d_reestr_Report.executeJasperReportD_reestr(ls, "_"+i, user, date1, date2, mm);
				else break;

				i++;
				iter = iter + 60_000;
			}
/*			
			List<Sp3RateMo> ls_ =  (List<Sp3RateMo>) d_reestr_dao.getResalt_D_reestrRateMo(date1, date2, user);
			String [] c ={"reports/sp3/d_reestr_report/d_reestr_report_rateMO.jrxml","\\content\\report\\sp3\\d_reestr_report\\"+user+"\\Список диспансерного наблюдения_рейтингМО_"};
			// выполняем формирование отчета
			if (ls_.size() != 0) d_reestr_Report.executeJasperReportRateMo_D_reestr(ls_, user, date1, date2,c);
*/
			builder = Response.status(Response.Status.OK);
			builder.entity("Отчет успещно сформирован");
			//throw new Exception();
			return builder.build();
		}catch (Exception e) {
			e.printStackTrace();
			builder = Response.status(Response.Status.OK);
			builder.entity("Произошла ошибка формирования отчета");
			return builder.build();
		}

	}

	/**
	 * Формирование списка на информирование чезез форму (<form>.)
	 * @param id
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParseException
	 */
	@GET
	@Path("/list_files/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesD_reestr(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

		List<?> df = (List<?>) d_reestr_dao.getListFiles(id);

		return df;
	}


	@GET
	@Path("/download/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/vnd.ms-excel")
	public Response getdownloadFile(@PathParam("id") Integer id, @PathParam("namefile") String namefile) {

		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\4";


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
