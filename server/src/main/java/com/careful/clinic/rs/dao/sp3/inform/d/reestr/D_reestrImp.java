package com.careful.clinic.rs.dao.sp3.inform.d.reestr;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import com.careful.clinic.model.ListExcelFiles;

@Stateless
public class D_reestrImp  implements D_reestr{

	@Override
	public List<?> getListDReestr(Integer id) {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\4";
		
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
