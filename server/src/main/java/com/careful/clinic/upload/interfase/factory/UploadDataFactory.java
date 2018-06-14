package com.careful.clinic.upload.interfase.factory;

import java.io.File;
import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.careful.clinic.upload.interfase.IDataUploadType;
import com.careful.clinic.upload.type.Agreement;
import com.careful.clinic.upload.type.BreakAgreement;
import com.careful.clinic.upload.type.InformD_reestr;

// TODO убрать метод  getInstansUploadData и сдалать его слабосвязанным как  abstract IDataUploadType getInstansUploadData();
@Stateless
@LocalBean
public class UploadDataFactory {
	
	public IDataUploadType getInstansUploadData(String fileName) throws IOException, InvalidFormatException{
		IDataUploadType dut = null;
		
		OPCPackage pkg = OPCPackage.open(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(pkg);
		
		DataFormatter formatter = new DataFormatter();
		Sheet sheet =  workbook.getSheetAt(0);
		Row row = sheet.getRow(0);
		// из загружаемого файла обпределяем тип данных которые грузятся.
		String  type = row.getCell(1).getStringCellValue().trim().toUpperCase();
		
		System.out.println("TYPE: " +type);
		
		
		if(type.trim().equalsIgnoreCase("ПОДАЧА СОГЛАСИЯ ЗЛ")){
			dut = new Agreement(pkg,fileName);
		}else if(type.trim().equalsIgnoreCase("АННУЛИРОВАНИЕ&ОТЗЫВ СОГЛАСИЯ ЗЛ")){
			dut = new BreakAgreement(pkg,fileName);
		}else if(type.trim().equalsIgnoreCase("ИНФОД-УЧЕТ")){
			dut = new InformD_reestr(pkg,fileName);
		}else{
			// временно. До тех пор пока не непеведу все под паттерн фабрика AbstractDataUploadType 
			pkg.close();
		}
		
		
		return dut;
	}

}
