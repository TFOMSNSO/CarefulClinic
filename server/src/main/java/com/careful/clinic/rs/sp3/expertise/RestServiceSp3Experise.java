package com.careful.clinic.rs.sp3.expertise;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.careful.clinic.dao.sp3.expertise.ISp3ExpertiseDao;
import com.careful.clinic.model.ListExcelFiles;
import com.careful.clinic.model.Wrap3aExpertise;
import com.careful.clinic.report.sp3.expertise.Sp3ExpertiseReport;

import net.sf.jasperreports.engine.JRException;

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
	public Response listFilesBrowser(String x)  {
		
		Response.ResponseBuilder builder = null;
		String m[] = x.split("\":\"");
		try{
			
			List<Wrap3aExpertise> ls = null;
			// смещение для запроса
			int iter = 0;
			// порядковый номер файла
			int i =1;
			while(true){
				ls = (List<Wrap3aExpertise>) sp3ExpertiseDAO.getResalt3a_expertise(m[1].substring(0, 10), m[2].substring(0, 10), m[3].substring(0, 3), iter);
				if (ls.size() != 0) sp3ExpertiseReport.executeJasperReport3aExpertise(ls, "_"+i);
				else break;
				
				i++;
				iter = iter + 60_000;
			}
			
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

}
