package com.careful.clinic.dao.prophylactic;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.xml.sax.SAXException;

import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.SearchKeysModel;
import com.careful.clinic.model.WrapRespSerarchKeys;

@Local
public interface ProphylacticDAO {

	public Collection<?> getInfoInsur(PersonModel personmodel) throws ParseException;
	public Collection<?> getInfoInsurkeys(SearchKeysModel personmodel) throws Exception;	
	public Collection<?> exportToExcel(ArrayList<WrapRespSerarchKeys> wrapRespSerarchKeys) throws Exception;	
	public  void writeListToFile(String fileName, List<WrapRespSerarchKeys> wrapRespSerarchKeys, String querytext) throws Exception;
	public Collection<?> getListUploadedFiles(Integer id);
	public Collection<?> getInfoProphylactic(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException, SOAPException;
	public Collection<?> getListExcelFiles(Integer id);



	Integer countStrProphylactic();
}
