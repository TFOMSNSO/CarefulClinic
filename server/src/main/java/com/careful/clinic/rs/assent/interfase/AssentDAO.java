package com.careful.clinic.rs.assent.interfase;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AssentDAO {

	List<?> getListAssentReports(Integer id);

}
