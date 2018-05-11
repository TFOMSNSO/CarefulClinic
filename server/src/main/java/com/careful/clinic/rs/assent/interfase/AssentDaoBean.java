package com.careful.clinic.rs.assent.interfase;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import com.careful.clinic.model.ListExcelFiles;

@Stateless
public class AssentDaoBean implements AssentDAO {

	@Override
	public List<?> getListAssentReports(Integer id) {

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\assent\\stat_assent\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\assent\\stat_assent\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\assent\\stat_assent\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\assent\\stat_assent\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		//TODO создать компонент utils в ejb и сделать метод сортировки по дата изменения файла 
		File path = new File(directoryDestination);
		File[] files = path.listFiles();
		Arrays.sort(files, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
		
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < files.length;i++){
			ls.add(new ListExcelFiles(files[i].getName(),directoryDestination+File.separator+files[i].getName()));
		}
		
		return ls;
	
	}

}
