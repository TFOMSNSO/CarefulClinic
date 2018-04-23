package com.careful.clinic.dao.inform;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;

import com.careful.clinic.model.ListExcelFiles;

@Stateless
public class InformDAOBean implements InformDAO {

// TODO: variable 'current year' is hard code. Replace them.	
	
	@Override
	public Collection<?> getListInformSecondStage(Integer id) {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_about_second_stage\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_about_second_stage\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_about_second_stage\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_about_second_stage\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
			
		}
		
		return ls;
		
	}
	
	@Override
	public Collection<?> getListInformKvartal(Integer id) {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_kvartals\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_kvartals\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_kvartals\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_kvartals\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
			
		}
		
		return ls;
		
	}
	
	@Override
	public Collection<?> getListInformKvartalActual(Integer id) {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_actual\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_actual\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_actual\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\inform_actual\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
			
		}
		
		return ls;
		
	}

	
	@Override
	public Collection<?> getListInformReinform(Integer id) {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year +"\\reinform\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\reinform\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\reinform\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\reinform\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
			
		}
		
		return ls;
		
	}
	

	@Override
	public List<?> getListInformMedOsmotri(Integer id) {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\informing\\"+current_year +"\\ProfMedOsmotri\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\ProfMedOsmotri\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\ProfMedOsmotri\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\informing\\"+current_year +"\\ProfMedOsmotri\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
			
		}
		
		return ls;
	}

}
