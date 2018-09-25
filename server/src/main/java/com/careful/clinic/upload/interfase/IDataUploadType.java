package com.careful.clinic.upload.interfase;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import org.apache.poi.openxml4j.opc.OPCPackage;

import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.exceptions.ParseDataExcelException;


public interface IDataUploadType {

	/**
	 * Метод проверяет структуру запроса. Количество столбцов. Наименование столбцов и тд 
	 * @throws IOException 
	 * @throws CheckStructureExcelException 
	 * 
	 */
	void checkOutStructure() throws IOException, ParseDataExcelException, CheckStructureExcelException;
	
	/**
	 *  Проверка типизации каждой строки в загружаемых данных
	 * @throws IOException 
	 * @throws CheckTypizineExcelException 
	 * @throws ParseException 
	 */
	void checkOutTypizine() throws IOException, CheckTypizineExcelException, ParseException;
	/**
	 * Метод создает insert sql запрос и сохраняет его в коллекцию 
	 * @return Коллецию сформированных запросов типа insert
	 * @throws IOException 
	 * @throws ParseException 
	 */
	void constructQuery() throws  ParseException, IOException;

	List<String> orderingParsingProcess() throws ParseException, IOException, CheckStructureExcelException, ParseDataExcelException, CheckTypizineExcelException;

	/**
	 * Метод проверяет наличие в базе уже существуюшей записи (полной копии)
	 * Цель - исключить дублирование записей в базе 
	 */
	void checkSinchronizeData();

	void checkSinchronizeData(List<String> listquery);
	
	
	/** Формирование запроса типа select на основе входящих данных. 
	 *   Select необходим для проверки наличия в базе данных уже имеющихся данных. В зависимости от наличия/отсутствия данных 
	 *   выполняется дальнейшая логика.
	 * @param str  - запрос insert на основе которых создаются select'ы
	 * @return сформированный запрос
	 */
	String construct_querySelect(String str);


}
