package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.exceptions.ParseDataExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;

public abstract class AbstractDataUploadType implements IDataUploadType {

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
				 
				 
				 return this.getListInsertQueries();
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
	
}
