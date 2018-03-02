package com.careful.clinic.dao.inform;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

@Local
public interface InformDAO {
	
	public Collection<?>  getListInformSecondStage(Integer id);

	/**
	 * Метод берет из определенной папки файлы для информирования по кварталам
	 * @param id
	 * @return
	 */
	Collection<?> getListInformKvartal(Integer id);

	public List<?> getListInformMedOsmotri(Integer id); 
}
