package com.careful.clinic.upload.interfase;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import org.apache.poi.openxml4j.opc.OPCPackage;


public interface IDataUploadType {

	/**
	 * Метод проверяет структуру запроса. Количество столбцов. Наименование столбцов и тд 
	 * 
	 */
	void checkOutStructure();
	
	/**
	 *  Проверка типизации каждой строки в загружаемых данных
	 */
	void checkOutTypizine();
	/**
	 * Метод создает insert sql запрос и сохраняет его в коллекцию 
	 * @return Коллецию сформированных запросов типа insert
	 * @throws IOException 
	 * @throws ParseException 
	 */
	List<String> constructQuery() throws  ParseException, IOException;

	List<String> orderingParsingProcess() throws ParseException, IOException;

	void checkSinchronizeData();

	void checkSinchronizeData(List<String> listquery);
	
	/**
	 * @return возвращает путь файла
	 */
	String getFileName();
	
	/** Формирование запроса типа select на основе входящих данных. 
	 *   Select необходим для проверки наличия в базе данных уже имеющихся данных. В зависимости от наличия/отсутствия данных 
	 *   выполняется дальнейшая логика.
	 * @param str  - запрос insert на основе которых создаются select'ы
	 * @return сформированный запрос
	 */
	String construct_querySelect(String str);

	
}
