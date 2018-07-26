package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.exceptions.ParseDataExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;

public abstract class AbstractDataUploadType implements IDataUploadType {

	private SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
	private SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");
	private SimpleDateFormat df3 = new SimpleDateFormat("dd.MM.yyyy");
	private OPCPackage pkg;
	private String fileName;
	private XSSFWorkbook  xssfWorkbook; 
	private List<String> insert_queriues; 
	
	 public  List<String> orderingParsingProcess() throws ParseException, IOException, CheckStructureExcelException, ParseDataExcelException, CheckTypizineExcelException{
		 try{
				  getInstansUploadData();
				  
				  // this в данном случае будет экземпляром класса который инстанцировался в фабричном методе
				 this.checkOutStructure();
				 this.checkOutTypizine();
				 this.constructQuery();
				 this.checkSinchronizeData();
				 
			     List<String> ls = this.getListInsertQueries();
				 return ls;
		 }finally {
			closeXSSFWorkbook();
		}
		  
	 }
	 
	 public List<String> getListInsertQueries(){
			if(this.insert_queriues == null){
				this.insert_queriues =  new ArrayList<String>();
				return this.insert_queriues;}
			else{ return this.insert_queriues;}
		}
		public void set(OPCPackage pkg, String fileName) throws IOException{
			this.pkg = pkg;
			this.fileName = fileName;
			setXSSFWorkbook(this.pkg);
		}
		
		private void setXSSFWorkbook(OPCPackage pkg) throws IOException{
			this.xssfWorkbook = new XSSFWorkbook(pkg);
		}
		
		public XSSFWorkbook getXSSFWorkbook(){
			return this.xssfWorkbook;
		}
		
		public void  closeXSSFWorkbook() throws IOException{
			//this.pkg = null;
			System.out.println("PKG close");
			this.pkg.close();
		}
	 
	 
	 // сделал ради проверки  слабой связи. Никакой логической полезности он не несет 
	 abstract IDataUploadType getInstansUploadData();
	
	 
	 public boolean checkDataFormat(Row row,int column) {
			try {
				df2.format(df1.parse(row.getCell(column).toString()));
			} catch (ParseException e) {
				try {
					df2.format(df3.parse(row.getCell(column).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
					return false;
				}
			}
		return true;
	}
	 
		public boolean checkDataFormat(Row row, Integer[] mas) {
			//Integer[] mas = {3,5};
			for(int i =0; i < mas.length; i++){
				try {
					df2.format(df1.parse(row.getCell(mas[i]).toString()));
				} catch (ParseException e) {
					try {
						df2.format(df3.parse(row.getCell(mas[i]).toString()));
					} catch (ParseException e1) {
						e1.printStackTrace();
						return false;
					}
				}
			}
			return true;
		}
		
		
		
		public boolean processNumericCell(Row row, Integer[] mas) {
			// столбцы которые участвуют в проверке
			//Integer[] mas = {4,6};
			for(int i = 0; i < mas.length; i ++){
				try{
					row.getCell(mas[i]).getNumericCellValue();
				}catch(NullPointerException ex){
					row.createCell(mas[i], Cell.CELL_TYPE_NUMERIC).setCellValue(1000);
				}catch(IllegalStateException ex){
					return false;
				}	
			}
			
			return true;
		}
		
		public boolean isLastRowCustom(DataFormatter formatter, Row row) {
			
			if(formatter.formatCellValue(row.getCell(0)).equals("") &&
					  formatter.formatCellValue(row.getCell(1)).equals("")  &&
					  formatter.formatCellValue(row.getCell(2)).equals("")  &&
					  formatter.formatCellValue(row.getCell(3)).equals("")  &&
					  formatter.formatCellValue(row.getCell(4)).equals("")  &&
					  formatter.formatCellValue(row.getCell(5)).equals("")  &&
					  formatter.formatCellValue(row.getCell(6)).equals("")  &&
					  formatter.formatCellValue(row.getCell(7)).equals("")){
				
				return true;
			}

		return false;
		}
}
