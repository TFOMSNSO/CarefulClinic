package com.careful.clinic.dao.sp3.expertise;


import java.util.List;

import javax.ejb.Local;

@Local
public interface ISp3ExpertiseDao {

	List<?> getResalt3a_expertise(String string, String string2, String string3, int i) throws Exception;

	List<?> getListExperiseReport(Integer id);

	
}
