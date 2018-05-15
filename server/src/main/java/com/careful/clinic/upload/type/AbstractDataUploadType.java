package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;

import com.careful.clinic.upload.interfase.IDataUploadType;

public abstract class AbstractDataUploadType implements IDataUploadType {

	OPCPackage pkg;
	String fileName;
	
	public void set(OPCPackage pkg, String fileName){
		this.pkg = pkg;
		this.fileName = fileName;
	}
	
	 public  List<String> orderingParsingProcess() throws ParseException, IOException{
		 List<String> result = null;
		  getInstansUploadData();
		  
		  // this в данном случае будет экземпляром класса который инстанцировался в фабричном методе
		 this.checkOutStructure();
		 this.checkOutTypizine();
		 result = this.constructQuery();
		 this.checkSinchronizeData(); 
		 return result; 
	 }
	 
	 // сделал ради проверки  слабой связи. Никакой логической полезности он не несет 
	 abstract IDataUploadType getInstansUploadData();
	
}
