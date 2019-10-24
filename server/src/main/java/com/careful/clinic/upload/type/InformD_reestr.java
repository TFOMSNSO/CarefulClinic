package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;
import org.apache.xmlbeans.impl.regex.Match;

import javax.validation.constraints.Null;

public class InformD_reestr extends AbstractDataPmI {

	public InformD_reestr(){

	}

	public InformD_reestr(OPCPackage pkg, String fileName) throws IOException{
		super.set(pkg,fileName);
	}

	@Override
	public void checkOutTypizine() throws IOException, CheckTypizineExcelException, ParseException {
		
		System.out.println("Process Typizine (InformD_reestr) "+this.getClass().getName());
		String pattern = "\\d\\d.\\d\\d.\\d\\d+-\\d\\d.\\d\\d.\\d\\d+";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher;

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
		try{
			strb = sheet.getRow(2).getCell(1).getNumericCellValue() > 4 || sheet.getRow(2).getCell(1).getNumericCellValue() < 1 ? strb.append("ERROR Неверно указан id смо. Поле Смо. "+"\r\n") : strb.append("");
		}catch (IllegalStateException ex){
			try {
				int smo = Integer.parseInt(sheet.getRow(2).getCell(1).getStringCellValue());
				if (smo > 4 || smo < 1) {
					strb.append("ERROR Неверно указан id смо. Поле Смо. " + "\r\n");
				}
			}catch (NumberFormatException e){
				strb.append("ERROR Неверно указан id смо(не число). Поле Смо. " + "\r\n");
			}
		}

//		System.out.println("rows: " + sheet.getPhysicalNumberOfRows());
		for(int j=4; j < sheet.getPhysicalNumberOfRows(); j++){
			try {
				row = sheet.getRow(j);
				//TODO если в эксель и int поле стоит string то вылетает IllegalStateException во время вызова getNumericCellValue()
				strb = super.checkRequredFild(formatter, row) ? strb.append("") : strb.append("ERROR Не указано обязательное поле. Строка " + (j + 1) + "\r\n");
				strb = super.processNumericCell(row, new Integer[]{4, 6}) ? strb.append("") : strb.append("ERROR Поле 'Этап информирования' или 'Тип информирования' является не числом типа int. Строка " + (j + 1) + "\r\n");
				try {
					strb = row.getCell(6).getNumericCellValue() > 7 ? strb.append("ERROR Неверно указано поле 'Тип информирования' . Строка " + (j + 1) + "\r\n") : strb.append("");
				} catch (Exception e) {
					strb.append("ERROR Непредвиденная ошибка. Строка " + (j + 1) + "\r\n");
				}
				strb = super.checkDataFormat(row, new Integer[]{3, 5}) ? strb.append("") : strb.append("ERROR Неверный формат даты. Строка " + (j + 1) + "\r\n");
				try {
					if (((row.getCell(5).getDateCellValue()).after(date)) || ((row.getCell(5).getDateCellValue().before(date2)))) {
						strb.append("ERROR В поле 'Дата информирования' некорректная дата. Строка " + (j + 1) + "\r\n. ");
					}
				} catch (IllegalStateException e) {
					strb.append("ERROR Неверный формат ячеек в поле	 'Дата информирования'. Строка " + (j + 1) + "\r\n");
				} catch (Exception e) {
					strb.append("ERROR Непредвиденная ошибка. Строка " + (j + 1) + "\r\n");
				}
			} catch (NullPointerException e){
				System.out.println("npe: J:" + j);
			}
			try {
				String prim = row.getCell(7).getStringCellValue();
				if (prim != null) {
					matcher = regex.matcher(prim);
					if (matcher.find()) {
						strb.append("ERROR В поле примечание стоит период вместо даты. Строка " + (j + 1));
					}
				}
			} catch (NullPointerException ex){
				System.out.println("ex:j-" + j);
			}

			boolean bl = super.isLastRowCustom(formatter,row);
			if(bl) break;
		}
		
		if(strb.toString().contains("ERROR")) { System.out.println(strb.toString()); throw new CheckTypizineExcelException(strb.toString()); }
	}

	

	@Override
	public void checkSinchronizeData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkSinchronizeData(List<String> listquery) {
		// TODO Auto-generated method stub
		
	}



	@Override
	IDataUploadType getInstansUploadData() {
		System.out.println("Отдаю инстанс getInstansUploadData (InformD_reestr) "+this.getClass().getName());
		return this;
	}


}

