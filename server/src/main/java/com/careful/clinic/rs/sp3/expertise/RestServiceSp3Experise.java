package com.careful.clinic.rs.sp3.expertise;

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

import com.careful.clinic.model.AfterDisp3Group;
import org.xml.sax.SAXException;

import com.careful.clinic.dao.sp3.expertise.ISp3ExpertiseDao;
import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.WrapSp3;
import com.careful.clinic.report.sp3.expertise.Sp3ExpertiseReport;

@javax.ws.rs.Path("/sp3/expertise")
public class RestServiceSp3Experise {

	@EJB
	ISp3ExpertiseDao sp3ExpertiseDAO;
	@EJB
	Sp3ExpertiseReport sp3ExpertiseReport;



	@GET
	@Path("/Sp3ReportExpertiseFiles/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesExpertiseReport(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {

		List<?> df = (List<?>) sp3ExpertiseDAO.getListExperiseReport(id);

		return df;
	}

	@POST
	@Path("/3a_report")
	//@Consumes("application/x-www-form-urlencoded")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response report_3a(String x)  {

		Response.ResponseBuilder builder = null;
		String m[] = x.split("\":\"");
		String user = Integer.parseInt(m[3].substring(0, 3).replaceAll("[\\D]", ""))+"";
		try{
			String date1 = m[1].substring(0, 10).substring(6,10)+m[1].substring(0, 10).substring(3,5);
			String date2 = m[2].substring(0, 10).substring(6,10)+m[2].substring(0, 10).substring(3,5);
			System.out.print("date1:" + date1 + "\ndate2:" + date2);
			List<WrapSp3> ls = null;
			// смещение для запроса
			int iter = 0;
			// порядковый номер файла
			int i =1;
			String [] mm = {"reports/sp3/expertise/3a_expertise.jrxml", "\\content\\report\\sp3\\expert\\"+user+"\\3А_список_экспертиза_"};
			while(true){
				ls = (List<WrapSp3>) sp3ExpertiseDAO.getResalt3a_expertise(date1, date2, user, iter);
				if (ls.size() != 0) sp3ExpertiseReport.executeJasperReportExpertise(ls, "_"+i, user, date1, date2, mm);
				else break;

				i++;
				iter = iter + 60_000;
			}

			List<Sp3RateMo> ls_ =  (List<Sp3RateMo>) sp3ExpertiseDAO.getResalt3a_expertiseRateMo(date1, date2, user);
			String [] c ={"reports/sp3/expertise/3a_expertise_rateMO.jrxml","\\content\\report\\sp3\\expert\\"+user+"\\3А_экспертиза_рейтингМО_"};
			// выполняем формирование отчета
			if (ls_.size() != 0) sp3ExpertiseReport.executeJasperReportRateMoExpertise(ls_, user, date1, date2,c);


			builder = Response.status(Response.Status.OK);
			builder.entity("Отчет успешно сформирован");
			//throw new Exception();
			return builder.build();
		}catch (Exception e) {
			e.printStackTrace();
			builder = Response.status(Response.Status.OK);
			builder.entity("Произошла ошибка формирования отчета");
			return builder.build();
		}
	}


	@GET
	@Path("/downloadFile/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/vnd.ms-excel")
	public Response getdownloadFile(@PathParam("id") Integer id, @PathParam("namefile") String namefile) {

		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\sp3\\expert\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\sp3\\expert\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\sp3\\expert\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\sp3\\expert\\4";


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
	@Path("/3a3b_report")
	//@Consumes("application/x-www-form-urlencoded")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response report_3a3b(String x)  {

		Response.ResponseBuilder builder = null;
		String m[] = x.split("\":\"");
		String user = Integer.parseInt(m[3].substring(0, 3).replaceAll("[\\D]", ""))+"";
		try{
			String date1 = m[1].substring(0, 10).substring(6,10)+m[1].substring(0, 10).substring(3,5);
			String date2 = m[2].substring(0, 10).substring(6,10)+m[2].substring(0, 10).substring(3,5);
			List<WrapSp3> ls = null;
			// смещение для запроса
			int iter = 0;
			// порядковый номер файла
			int i =1;
			String [] mm = {"reports/sp3/expertise/3a3b_expertise.jrxml", "\\content\\report\\sp3\\expert\\"+user+"\\Экспертиза_3_группа_без_назначения_лечебно-диагностических_мероприятий_"};
			while(true){
				ls = (List<WrapSp3>) sp3ExpertiseDAO.getResalt3a3b_expertise(date1, date2, user, iter);
				if (ls.size() != 0) sp3ExpertiseReport.executeJasperReportExpertise(ls, "_"+i, user, date1, date2, mm);
				else break;

				i++;
				iter = iter + 60_000;
			}

			List<Sp3RateMo> ls_ =  (List<Sp3RateMo>) sp3ExpertiseDAO.getResalt3a3b_expertiseRateMo(date1, date2, user);
			String [] c ={"reports/sp3/expertise/3a3b_expertise_rateMO.jrxml","\\content\\report\\sp3\\expert\\"+user+"\\Экспертиза_3_группа_без_назначения_лечебно-диагностических_мероприятий _рейтинг_МО_"};
			// выполняем формирование отчета
			if (ls_.size() != 0) sp3ExpertiseReport.executeJasperReportRateMoExpertise(ls_, user, date1, date2,c);

			builder = Response.status(Response.Status.OK);
			builder.entity("Отчет успешно сформирован");
			//throw new Exception();
			return builder.build();
		}catch (Exception e) {
			e.printStackTrace();
			builder = Response.status(Response.Status.OK);
			builder.entity("Произошла ошибка формирования отчета");
			return builder.build();
		}
	}

	@POST
	@Path("/3a3b_noNazrNoGosp_report")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response report_3a3b_noNazrNoGosp(String x)  {

		Response.ResponseBuilder builder = null;
		String m[] = x.split("\":\"");
		String user = Integer.parseInt(m[3].substring(0, 3).replaceAll("[\\D]", ""))+"";
		try{
			String date1 = m[1].substring(0, 10).substring(6,10)+m[1].substring(0, 10).substring(3,5);
			String date2 = m[2].substring(0, 10).substring(6,10)+m[2].substring(0, 10).substring(3,5);
			List<WrapSp3> ls = null;
			// смещение для запроса
			int iter = 0;
			// порядковый номер файла
			int i =1;
			String [] mm = {"reports/sp3/expertise/3a3b_expertise_noNazrNoGosp.jrxml", "\\content\\report\\sp3\\expert\\"+user+"\\Экспертиза_3_группа_без_признака_Д-учета_"};
			while(true){
				ls = (List<WrapSp3>) sp3ExpertiseDAO.getResalt3a3b_expertise_noNazrNoGosp(date1, date2, user, iter);
				if (ls.size() != 0) sp3ExpertiseReport.executeJasperReportExpertise(ls, "_"+i, user, date1, date2, mm);
				else break;

				i++;
				iter = iter + 60_000;
			}

			List<Sp3RateMo> ls_ =  (List<Sp3RateMo>) sp3ExpertiseDAO.getResalt3a3b_expertiseRateMo_noNazrNoGosp(date1, date2, user);
			String [] c ={"reports/sp3/expertise/3a3b_expertise_rateMO_noNazrNoGosp.jrxml","\\content\\report\\sp3\\expert\\"+user+"\\Экспертиза_3_группа_без_признака_Д-учета_рейтинг_МО_"};
			// выполняем формирование отчета
			if (ls_.size() != 0) sp3ExpertiseReport.executeJasperReportRateMoExpertise(ls_, user, date1, date2,c);

			builder = Response.status(Response.Status.OK);
			builder.entity("Отчет успешно сформирован");
			//throw new Exception();
			return builder.build();
		}catch (Exception e) {
			e.printStackTrace();
			builder = Response.status(Response.Status.OK);
			builder.entity("Произошла ошибка формирования отчета");
			return builder.build();
		}
	}

	@POST
	@Path("/after_disp_3_group_report")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response report_after_disp_3_group(String x)  {

		Response.ResponseBuilder builder = null;
		String m[] = x.split("\":\"");
		String user = Integer.parseInt(m[3].substring(0, 3).replaceAll("[\\D]", ""))+"";
		try{
			String date1 = m[1].substring(0, 10).substring(6,10)+m[1].substring(0, 10).substring(3,5);
			String date2 = m[2].substring(0, 10).substring(6,10)+m[2].substring(0, 10).substring(3,5);

			List<AfterDisp3Group> ls_ =  (List<AfterDisp3Group>) sp3ExpertiseDAO.getResalt_after_disp_3_group(date1, date2, user);
			String [] c ={"reports/sp3/expertise/after_disp_3_group_expertise.jrxml", "\\content\\report\\sp3\\expert\\"+user+"\\По_итогам_диспансеризации_установлена_3_группа_"};
			// выполняем формирование отчета
			if (ls_.size() != 0) sp3ExpertiseReport.executeJasperAfterDisp3Group(ls_, user, date1, date2,c);

			builder = Response.status(Response.Status.OK);
			builder.entity("Отчет успешно сформирован");
			//throw new Exception();
			return builder.build();
		}catch (Exception e) {
			e.printStackTrace();
			builder = Response.status(Response.Status.OK);
			builder.entity("Произошла ошибка формирования отчета");
			return builder.build();
		}
	}


	@POST
	@Path("/3b_report")
	//@Consumes("application/x-www-form-urlencoded")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response report_3b(String x)  {

		Response.ResponseBuilder builder = null;
		String m[] = x.split("\":\"");
		String user = Integer.parseInt(m[3].substring(0, 3).replaceAll("[\\D]", ""))+"";
		try{
			String date1 = m[1].substring(0, 10).substring(6,10)+m[1].substring(0, 10).substring(3,5);
			String date2 = m[2].substring(0, 10).substring(6,10)+m[2].substring(0, 10).substring(3,5);
			List<WrapSp3> ls = null;
			// смещение для запроса
			int iter = 0;
			// порядковый номер файла
			int i =1;
			String [] mm = {"reports/sp3/expertise/3b_expertise.jrxml", "\\content\\report\\sp3\\expert\\"+user+"\\3Б_список_экспертиза_"};
			while(true){
				ls = (List<WrapSp3>) sp3ExpertiseDAO.getResalt3b_expertise(date1, date2, user, iter);
				if (ls.size() != 0) sp3ExpertiseReport.executeJasperReportExpertise(ls, "_"+i, user, date1, date2, mm);
				else break;

				i++;
				iter = iter + 60_000;
			}

			List<Sp3RateMo> ls_ =  (List<Sp3RateMo>) sp3ExpertiseDAO.getResalt3b_expertiseRateMo(date1, date2, user);
			String [] c ={"reports/sp3/expertise/3b_expertise_rateMO.jrxml","\\content\\report\\sp3\\expert\\"+user+"\\3Б_экспертиза_рейтингМО_"};
			// выполняем формирование отчета
			if (ls_.size() != 0) sp3ExpertiseReport.executeJasperReportRateMoExpertise(ls_, user, date1, date2, c);

			builder = Response.status(Response.Status.OK);
			builder.entity("Отчет успешно сформирован");
			//throw new Exception();
			return builder.build();
		}catch (Exception e) {
			e.printStackTrace();
			builder = Response.status(Response.Status.OK);
			builder.entity("Произошла ошибка формирования отчета");
			return builder.build();
		}
	}
}