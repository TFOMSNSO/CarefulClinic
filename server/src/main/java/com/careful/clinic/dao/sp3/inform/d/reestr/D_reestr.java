package com.careful.clinic.dao.sp3.inform.d.reestr;

import java.util.List;

import javax.ejb.Local;

import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.InformDReestr;

@Local
public interface D_reestr {

	List<?> getListDReestr(Integer id);


	List<Sp3RateMo> getResalt_D_reestrRateMo(String date1, String date2, String user);

	List<InformDReestr> getResalt_D_reestrCollect2018(String date1, String date2, String user, int firrstResult);


	List<?> getListFiles(Integer id);

}