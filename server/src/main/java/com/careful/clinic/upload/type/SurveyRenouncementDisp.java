package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;

public class SurveyRenouncementDisp extends AbstractDataPmA {

	
	public SurveyRenouncementDisp() {}
	
	public SurveyRenouncementDisp(OPCPackage pkg, String fileName) throws IOException{
		super.set(pkg,fileName);
	}
	
	@Override
	IDataUploadType getInstansUploadData() {
		System.out.println("Отдаю инстанс getInstansUploadData (SurveyRenouncementDisp) "+this.getClass().getName());
		return this;
	}
	
	@Override
	public void checkOutTypizine() throws IOException, CheckTypizineExcelException, ParseException {

		System.out.println("Process Typizine (SurveyRenouncementDisp) "+this.getClass().getName());
		
		DataFormatter formatter = new DataFormatter();
		XSSFWorkbook workbook = super.getXSSFWorkbook();
		Sheet sheet =  workbook.getSheetAt(0);
		Row row = null;
		StringBuilder strb = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		Date date = new Date();
		Date date2 = new Date(System.currentTimeMillis() - 5184000000l);

		// добавить проверку типа смо и даты подачи

		strb = super.checkDataFormat(sheet.getRow(1),1) ? strb.append("") : strb.append("ERROR Неверный формат даты Поле 'Дата формирования'. "+"\r\n");
		try{strb = sheet.getRow(2).getCell(1).getNumericCellValue() > 4 || sheet.getRow(2).getCell(1).getNumericCellValue() < 1 ? strb.append("ERROR Неверно указан id смо. Поле Смо. "+"\r\n") : strb.append("");}
        catch (IllegalStateException e){
            strb.append("ERROR В Поле 'СМО'.Проверьте формат данных. Строка  3\r\n");
        }

		for(int j=4; j< sheet.getPhysicalNumberOfRows(); j++){

			row = sheet.getRow(j);
			//TODO если в эксель и int поле стоит string то вылетает IllegalStateException во время вызова getNumericCellValue()
			strb = super.checkRequredFild(formatter,row) ? strb.append("") : strb.append("ERROR Не указано обязательное поле. Строка "+ (j+1)+"\r\n");
			strb = super.processNumericCell(row,new Integer[]{5})  ? strb.append("") : strb.append("ERROR Поле 'Результат опроса'  является не числом типа int. Строка "+(j+1)+"\r\n");
			try{strb = row.getCell(5).getNumericCellValue() > 20 ? strb.append("ERROR В поле 'Результат опроса' используются несоответствующие id ответов для анкеты ОТКАЗ ДИСПАНСЕРИЗАЦИИ. Строка "+ (j+1)+"\r\n"): strb.append("");}
			catch (IllegalStateException e){
				strb.append("ERROR В Поле 'Результат опроса'.Проверьте формат данных. Строка  " + (j+1) + "\r\n");
			}
			strb = super.checkDataFormat(row, new Integer[]{3,4}) ? strb : strb.append("ERROR Неверный формат даты. Строка "+ (j+1)+"\r\n");
			try{if(((row.getCell(4).getDateCellValue()).after(date))||((row.getCell(4).getDateCellValue().before(date2))))
			{
				strb.append("ERROR В поле 'Дата опроса' некорректная дата. Строка "+ (j+1)+"\r\n");
			}}catch (IllegalStateException e){
				strb.append("ERROR Неверный формат ячеек в поле 'Дата опроса'. Строка "+ (j+1)+"\r\n");
            }
			catch (Exception e){
				strb.append("ERROR Непредвиденная ошибка. Строка "+ (j+1)+"\r\n");
			}
			boolean bl = super.isLastRowCustom(formatter,row);
			if(bl) break;
		}
		if(strb.toString().contains("ERROR")) { System.out.println(strb.toString()); throw new CheckTypizineExcelException(strb.toString()); }
	}
	
	@Override
	public void checkSinchronizeData(List<String> listquery) {
		// TODO Auto-generated method stub
		
	}
}
