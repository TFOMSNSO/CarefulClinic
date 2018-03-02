package com.careful.clinic.dao.sp3.expertise;


import java.util.List;

import javax.ejb.Local;

import com.careful.clinic.model.Wrap3a_b_Expertise;

@Local
public interface ISp3ExpertiseDao {

	List<?> getResalt3a_expertise(String string, String string2, String string3, int i) throws Exception;

	List<?> getListExperiseReport(Integer id);

	List<?> getResalt3b_expertise(String date1, String date2, String user, int iter);

	List<?> getResalt3a3b_expertise(String date1, String date2, String user, int iter);

	
}
