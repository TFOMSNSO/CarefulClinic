package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;

public class СontentmentDisp  extends AbstractDataPmA{
	
	public СontentmentDisp(OPCPackage pkg, String fileName) throws IOException{
		super.set(pkg,fileName);
	}
	
	public СontentmentDisp(){
	}

	@Override
	public void checkOutTypizine() throws IOException, CheckTypizineExcelException, ParseException {

		
		System.out.println("Process Typizine (СontentmentDisp) "+this.getClass().getName());
		
		DataFormatter formatter = new DataFormatter();
		XSSFWorkbook workbook = super.getXSSFWorkbook();
		Sheet sheet =  workbook.getSheetAt(0);
		Row row = null;
		StringBuilder strb = new StringBuilder();
		
		
		
		// добавить проверку типа смо и даты подачи
		
		strb = super.checkDataFormat(sheet.getRow(1),1) ? strb.append("") : strb.append("ERROR Неверный формат даты Поле 'Дата формирования'. "+"\n");
		strb = sheet.getRow(2).getCell(1).getNumericCellValue() > 4 || sheet.getRow(2).getCell(1).getNumericCellValue() < 1 ? strb.append("ERROR Неверно указан id смо. Поле Смо. "+"\n") : strb.append("");
		
		for(int j=4; j< sheet.getPhysicalNumberOfRows(); j++){
			
			row = sheet.getRow(j);
			//TODO если в эксель и int поле стоит string то вылетает IllegalStateException во время вызова getNumericCellValue()			
			strb = super.checkRequredFild(formatter,row) ? strb.append("") : strb.append("ERROR Не указано обязательное поле. Строка "+ (j+1)+"\n");
			strb = super.processNumericCell(row,new Integer[]{5})  ? strb.append("") : strb.append("ERROR Поле 'Результат опроса'  является не числом типа int. Строка "+(j+1)+"\n");
			strb = row.getCell(5).getNumericCellValue() > 111 ? strb.append("ERROR В поле 'Результат опроса' используются несоответствующие id ответов для анкеты ОПРОС УДОВЛЕТВОРЕННОСТИ ДИСПАНСЕРИЗАЦИИ. Строка "+ (j+1)+"\n"): strb.append("");
			strb = row.getCell(5).getNumericCellValue() < 101 ? strb.append("ERROR В поле 'Результат опроса' используются несоответствующие id ответов для анкеты ОПРОС УДОВЛЕТВОРЕННОСТИ ДИСПАНСЕРИЗАЦИИ. Строка "+ (j+1)+"\n"): strb.append("");
			
			strb = super.checkDataFormat(row, new Integer[]{3,4}) ? strb.append("") : strb.append("ERROR Неверный формат даты. Строка "+ (j+1)+"\n");
			
			
			boolean bl = super.isLastRowCustom(formatter,row);
			if(bl) break;
			
		}
		
		if(strb.toString().contains("ERROR")) { System.out.println(strb.toString()); throw new CheckTypizineExcelException(strb.toString()); }
		
	
		
	
		
	}

	@Override
	public void checkSinchronizeData(List<String> listquery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	IDataUploadType getInstansUploadData() {
		// TODO Auto-generated method stub
		return null;
	}

}
