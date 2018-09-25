package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;

public abstract class AbstractDataPmI extends AbstractDataUploadType {
	
	private SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
	private SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
	private SimpleDateFormat df3 = new SimpleDateFormat("dd.MM.yyyy");
	
	

	@Override
	public void constructQuery() throws ParseException, IOException {
		
		System.out.println("Метод constructQuery (AbstractDataPmI) "+this.getClass().getName());
		DataFormatter formatter = new DataFormatter();
		XSSFWorkbook workbook = super.getXSSFWorkbook();
		Sheet sheet =  workbook.getSheetAt(0);
		Row row = null;
		StringBuilder sb = new StringBuilder();
		
		String smo_id = String.valueOf((int)sheet.getRow(2).getCell(1).getNumericCellValue());

		for(int j=4; j< sheet.getPhysicalNumberOfRows(); j++){
			row = sheet.getRow(j);
			sb.append("insert into pm_i p        values('',");
			for(int i=0;  i < 8; i++){
				sb.append("'");

				if(i == 3 || i == 5){
					try{
						sb.append(df2.format(df1.parse(row.getCell(i).toString())));
					}catch ( java.text.ParseException e) {
						sb.append(df2.format(df3.parse(row.getCell(i).toString())));
					}
					
				}else{
					sb.append(formatter.formatCellValue(row.getCell(i)).toUpperCase());
				}
				
				sb.append("'");
				sb.append(",");
			}
			sb.append("");
			
			sb.append(""+smo_id+",trunc(sysdate),sysdate)");
			
			super.getListInsertQueries().add(sb.toString());
			sb.delete(0, sb.length());
			
			
			boolean bl = isLastRowCustom(formatter,row);
			if(bl) break;
			System.out.println("AbstractDataPmI inserted row");
		}
		
		
	}
	
	@Override
	public String construct_querySelect(String str) {

		
		StringBuilder  sb = new StringBuilder();
		String tmp_m[] = null, tmp = null;
		tmp = str.replace("insert into", "select  count(*) from");
		sb.append(tmp.substring(0, 34));
		
		tmp_m = tmp.substring(29).split(",");
		sb.append(" where ");
		sb.append("p.fam=");
		sb.append(tmp_m[1]);
		sb.append(" and p.im=");
		 sb.append(tmp_m[2]);
		 sb.append(" and p.ot=");
		 sb.append(tmp_m[3]);
		 sb.append(" and p.dr=");
		 sb.append(tmp_m[4]);
		 sb.append(" and p.n_stage=");
		 sb.append(tmp_m[5]);
		 sb.append(" and p.d_info=");
		 sb.append(tmp_m[6]);
		 sb.append(" and p.t_info=");
		 sb.append(tmp_m[7]);
		 sb.append(" and p.smo=");
		 sb.append(tmp_m[9]);
		System.out.println("AbstractDataPmI count rows and search double");
		return sb.toString();
	}

		@Override
		public void checkOutStructure() throws IOException, CheckStructureExcelException {
			
			System.out.println("Process structures (AbstractDataPmI) "+this.getClass().getName());
			XSSFWorkbook workbook = super.getXSSFWorkbook();
			Sheet sheet =  workbook.getSheetAt(0);
			Row row = null;
			StringBuilder strb = new StringBuilder();
			
			strb  = sheet.getPhysicalNumberOfRows() < 50_000 ? strb.append("VALID Количество строк допустимо. \n") : strb.append("ERROR Превышено допустимое количество строк в загружаемом файле. Не более 50 000 строк. \n");
			strb  = sheet.getRow(0).getCell(0).getStringCellValue().trim().equalsIgnoreCase("Тип запроса") ? strb.append("VALID Тэг 'Тип запроса' - корректно. \n") : strb.append("ERROR Тэг 'Тип запроса' - некорректно. \n");
			strb  = sheet.getRow(1).getCell(0).getStringCellValue().trim().equalsIgnoreCase("Дата формирования") ? strb.append("VALID Тэг 'Дата формирования' - корректно \n") : strb.append("ERROR Тэг 'Дата формирования' - некорректно \n");
			strb  = sheet.getRow(2).getCell(0).getStringCellValue().trim().equalsIgnoreCase("Смо") ? strb.append("VALID Тэг 'Смо'  - корректно.  \n") : strb.append("ERROR Тэг 'Смо' - некорректно. \n");
			
			strb  = sheet.getRow(3).getCell(0).getStringCellValue().trim().equalsIgnoreCase("Фамилия") ? strb.append("VALID Тэг 'Фамилия' - корректно.  \n") : strb.append("ERROR Тэг 'Фамилия' - некорректно. \n");
			strb  = sheet.getRow(3).getCell(1).getStringCellValue().trim().equalsIgnoreCase("Имя") ? strb.append("VALID Тэг 'Имя' - корректно.  \n") : strb.append("ERROR Тэг 'Имя' - некорректно. \n");
			strb  = sheet.getRow(3).getCell(2).getStringCellValue().trim().equalsIgnoreCase("Отчество") ? strb.append("VALID Тэг 'Отчество' - корректно.  \n") : strb.append("ERROR Тэг 'Отчество' - некорректно. \n");
			strb  = sheet.getRow(3).getCell(3).getStringCellValue().trim().equalsIgnoreCase("Дата рождения") ? strb.append("VALID Тэг 'Дата рождения' - корректно.  \n") : strb.append("ERROR Тэг 'Дата рождения' - некорректно. \n");
			strb  = sheet.getRow(3).getCell(4).getStringCellValue().trim().equalsIgnoreCase("Этап информирования") ? strb.append("VALID Тэг 'Этап информирования' - корректно.  \n") : strb.append("ERROR Тэг 'Этап информирования' - некорректно. \n");
			strb  = sheet.getRow(3).getCell(5).getStringCellValue().trim().equalsIgnoreCase("Дата информирования") ? strb.append("VALID Тэг 'Дата информирования' - корректно.  \n") : strb.append("ERROR Тэг 'Дата информирования' - некорректно. \n");
			strb  = sheet.getRow(3).getCell(6).getStringCellValue().trim().equalsIgnoreCase("Тип информирования") ? strb.append("VALID Тэг 'Тип информирования' - корректно.  \n") : strb.append("ERROR Тэг 'Тип информирования' - некорректно. \n");
			strb  = sheet.getRow(3).getCell(7).getStringCellValue().trim().equalsIgnoreCase("Примечание") ? strb.append("VALID Тэг 'Примечание' - корректно.  \n") : strb.append("ERROR Тэг 'Примечание' - некорректно. \n");
			
			if(strb.toString().contains("ERROR")) { System.out.println(strb.toString()); throw new CheckStructureExcelException(strb.toString()); }
			
		}

		/**
		 *  Проверка окончания записей. Так как native метод определяет неточно (попадают непечатаемые символы)
		 * @param formatter форматирование
		 * @param row  строка подлежащая проверке
		 * @return true если окончание записей иначе false
		 */
		public boolean checkRequredFild(DataFormatter formatter, Row row) {
			
			if(formatter.formatCellValue(row.getCell(0)).equals("") ||
					  formatter.formatCellValue(row.getCell(1)).equals("")  ||
					  formatter.formatCellValue(row.getCell(2)).equals("")  ||
					  formatter.formatCellValue(row.getCell(3)).equals("")  ||
					  formatter.formatCellValue(row.getCell(4)).equals("")  ||
					  formatter.formatCellValue(row.getCell(5)).equals("")  ||
					  formatter.formatCellValue(row.getCell(6)).equals("")
							) return false;
			
			return true;
		}
	
}
