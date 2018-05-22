package com.careful.clinic.rs.dao.sp3.inform.d.reestr;

import java.util.List;

import javax.ejb.Local;

@Local
public interface D_reestr {

	List<?> getListDReestr(Integer id);

}
