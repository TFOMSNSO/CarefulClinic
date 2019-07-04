package com.careful.clinic.dao.inform;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

@Local
public interface InformDAO {
	
//TODO: Refactor a methods with share logic
//пы сы зачем тут столько методов я не знаю. не я писал.
    public Collection<?>  getListInformSecondStage(Integer id);

	/**
	 * Метод берет из определенной папки файлы для информирования по кварталам
	 * @param id
	 * @return
	 */
	Collection<?> getListInformKvartal(Integer id);

	public List<?> getListInformMedOsmotri(Integer id);

	/**
	 * Метод формирует список (названия) файлом из определенной директории файлового хранения 
	 * @param id
	 * @return
	 */
	Collection<?> getListInformReinform(Integer id);

	/**
	 * @param id
	 * @return Список имен файлов которые являются актульным планом информирования за определеееый квартал
	 */
	Collection<?> getListInformKvartalActual(Integer id);


	Collection<?> getListInformBrig(Integer id);
}
