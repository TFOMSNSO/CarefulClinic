package com.careful.clinic.rs.assent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.sql.DataSource;
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

import com.careful.clinic.rs.assent.interfase.AssentDAO;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@javax.ws.rs.Path("/assent")
public class Assent {
	
	@EJB
	private AssentDAO assentDAO;


	@POST
	@Path("/stat_assent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response assent_report(String x)  {
		
		Response.ResponseBuilder builder = null;
		
		try{

		String m[] = x.split("\":\"");
		
		String directoryServer = System.getProperty("jboss.home.dir");
		String user = Integer.parseInt(m[3].substring(0, 3).replaceAll("[\\D]", ""))+"";
		String [] mm = {"reports/assent/state_assent.jrxml", "\\content\\report\\assent\\stat_assent\\"+user+"\\Статистика согласий_"};
		String str1 = m[1].substring(0, 10);
		String str2 = m[2].substring(0, 10);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		// если юзер тфомс то подставляем 1_2_4 (ид страховых)
		if(user.equals("777")){
			parameters.put("user", "1_2_4") ;
		} else{
			parameters.put("user", user);
		}
		parameters.put("date_start", str1);
        parameters.put("date_end", str2);
        
        String path = Thread.currentThread().getContextClassLoader().getResource(mm[0]).getPath();
        File f = new File(path);
		JasperDesign jasperDesign = JRXmlLoader.load(f);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		
		
		InitialContext initialContext = new InitialContext();
		DataSource dataSource = (DataSource)initialContext.lookup("java:/ds/OfomsDream2");
		Connection connection = dataSource.getConnection();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
		
		JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
       // TODO: сделать логирование выгрузки
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,  directoryServer+mm[1]+LocalDate.now().toString()+"_" + LocalTime.now().toString().substring(0, 8).replaceAll(":", "-")+".xlsx");
        exporter.exportReport();
		
		System.out.println("Done! "+path);
		System.out.println("Done!2 "+directoryServer);
		
		builder = Response.status(Response.Status.OK);
	     builder.entity("Отчет успешно сформирован");
	     return builder.build();
	     
		}catch(Exception e){
			e.printStackTrace();
			builder = Response.status(Response.Status.OK);
		     builder.entity("Произошла ошибка формирования отчета");
		     return builder.build();
		}
	}
	
	
	@GET
	@Path("/list_reports_assent/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<?> listFilesAssentReport(@PathParam("id") Integer id) throws ParserConfigurationException, SAXException, IOException, ParseException {
		
		List<?> df = (List<?>) assentDAO.getListAssentReports(id);
		
		return df;
	}
	
	@GET
	@Path("/downloadFile/{id}/{namefile}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/vnd.ms-excel")
	public Response getdownloadFile(@PathParam("id") Integer id, @PathParam("namefile") String namefile) {
		
		int current_year = Calendar.getInstance().get(Calendar.YEAR);
		
		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
			if(id == 777) directoryDestination = "\\content\\report\\assent\\stat_assent\\777";
			if(id == 1)	directoryDestination = "\\content\\report\\assent\\stat_assent\\1";
			if(id == 2)	directoryDestination = "\\content\\report\\assent\\stat_assent\\2";
			if(id == 4)	directoryDestination = "\\content\\report\\assent\\stat_assent\\4";	
		
		
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
