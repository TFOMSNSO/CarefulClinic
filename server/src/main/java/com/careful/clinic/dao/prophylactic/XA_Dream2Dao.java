package com.careful.clinic.dao.prophylactic;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.ResponseGer;

@Local
public interface XA_Dream2Dao {

	public void pasteResultPm_a(String sql);
	public Collection<?> getSurveyInform(PersonModel personmodel);
	public Collection<?> getInfoInform(PersonModel personmodel) throws ParseException;	
	public Collection<?> getInfoPlanInform(Integer adressid) throws ParseException;
	public Collection<?> getInfoG(PersonModel personmodel) throws ParseException, ParserConfigurationException, SAXException, IOException;	
	public boolean insertDataFromExcel(List<String> listOfQueryies) throws Exception;
	ResponseGer parseResponse(String xml) throws ParserConfigurationException, SAXException, IOException;
}
