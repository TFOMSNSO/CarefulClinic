package com.careful.clinic.upload.type;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.careful.clinic.exceptions.CheckStructureExcelException;
import com.careful.clinic.exceptions.CheckTypizineExcelException;
import com.careful.clinic.exceptions.ParseDataExcelException;
import com.careful.clinic.upload.interfase.IDataUploadType;

public class SurveyRenouncementDisp extends AbstractDataUploadType {

	@Override
	public void checkOutStructure() throws IOException, ParseDataExcelException, CheckStructureExcelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkOutTypizine() throws IOException, CheckTypizineExcelException, ParseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void constructQuery() throws ParseException, IOException {
		// TODO Auto-generated method stub
		
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
	public String construct_querySelect(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	IDataUploadType getInstansUploadData() {
		// TODO Auto-generated method stub
		return null;
	}

}
