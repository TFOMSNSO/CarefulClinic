package com.careful.clinic.upload.interfase;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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

	
}
