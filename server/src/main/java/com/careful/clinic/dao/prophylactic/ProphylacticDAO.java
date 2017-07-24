package com.careful.clinic.dao.prophylactic;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.careful.clinic.model.Person;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.ResponseGer;

@Stateless
@LocalBean
public class ProphylacticDAO {

	
	@PersistenceContext(unitName="OracleDream2DS")
    private EntityManager em_dream2;
	@PersistenceContext(unitName="OracleDSDeveloper")
    private EntityManager em_developer;
	
	
	public Collection<?> getInfoInsur(PersonModel personmodel) throws ParseException{
		
		TypedQuery<Person> query = em_developer.createNamedQuery("Person.findByFIOD", Person.class)
        		
				.setParameter("personSurname", personmodel.getSurname().toUpperCase())
				.setParameter("personKindfirstname", personmodel.getFirstname().toUpperCase())
				.setParameter("personKindlastname", personmodel.getLastname().toUpperCase())
				.setParameter("personBirthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
		
		return query.getResultList();
		
	}
	
	public Collection<?> getInfoG(PersonModel personmodel) throws ParseException, ParserConfigurationException, SAXException, IOException{
		
		StoredProcedureQuery storedProcedure =  em_dream2.createStoredProcedureQuery("sys.connect_mis.disp_fiod");
        
        storedProcedure.registerStoredProcedureParameter("response",String.class, ParameterMode.OUT);
        
        storedProcedure.registerStoredProcedureParameter("surname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("firstname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("lastname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("datebythday",String.class, ParameterMode.IN);
        
        storedProcedure.setParameter("surname", personmodel.getSurname());
        storedProcedure.setParameter("firstname", personmodel.getFirstname());
        storedProcedure.setParameter("lastname", personmodel.getLastname());
        storedProcedure.setParameter("datebythday", personmodel.getBithday());

        storedProcedure.execute();

        String respXml = (String)storedProcedure.getOutputParameterValue("response");
        //em_dream2.getTransaction().commit();
        // java.lang.NullPointerException
        ResponseGer rGer = parseResponse(respXml);
        List<ResponseGer> ls = new ArrayList<ResponseGer>(1);
        ls.add(rGer);

		
		return ls;
		
	}
	
	
	public Collection<?> getInfoProphylactic(PersonModel personmodel) throws ParserConfigurationException, SAXException, IOException, ParseException{
		

        TypedQuery<Person> query = em_developer.createNamedQuery("Person.findByFIOD", Person.class)
        		
        							.setParameter("personSurname", personmodel.getSurname().toUpperCase())
        							.setParameter("personKindfirstname", personmodel.getFirstname().toUpperCase())
        							.setParameter("personKindlastname", personmodel.getLastname().toUpperCase())
        							.setParameter("personBirthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
        List<Person> results = query.getResultList();
        
        /*
         *******************************************************
        */
        
        //em_dream2.getTransaction();
        StoredProcedureQuery storedProcedure =  em_dream2.createStoredProcedureQuery("sys.connect_mis.disp_fiod");
        
        storedProcedure.registerStoredProcedureParameter("response",String.class, ParameterMode.OUT);
        
        storedProcedure.registerStoredProcedureParameter("surname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("firstname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("lastname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("datebythday",String.class, ParameterMode.IN);
        
        storedProcedure.setParameter("surname", personmodel.getSurname());
        storedProcedure.setParameter("firstname", personmodel.getFirstname());
        storedProcedure.setParameter("lastname", personmodel.getLastname());
        storedProcedure.setParameter("datebythday", personmodel.getBithday());

        storedProcedure.execute();

        String respXml = (String)storedProcedure.getOutputParameterValue("response");
        //em_dream2.getTransaction().commit();
        
        ResponseGer rGer = parseResponse(respXml);
        List<ResponseGer> ls = new ArrayList<ResponseGer>(1);
        ls.add(rGer);
        
        
        
        /*
           *******************************************************
        */
        List<?>  obj = Stream.concat(results.stream(), ls.stream()).collect(Collectors.toList());

		return (Collection<?>)obj;
	}
	
	
	/**
	 * Метол парсит xml строку (ответ) ГЭР'а о диспансеризации
	 * 
	 * @param xml строка в формате xml 
	 * @return объект ответа распарсеного xml 
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private ResponseGer parseResponse(String xml) throws ParserConfigurationException, SAXException, IOException{
		
		ResponseGer resp = new ResponseGer();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		 InputSource is = new InputSource(new StringReader(xml));
		 Document doc = dBuilder.parse(is);
		 doc.getDocumentElement().normalize();
		
		 NodeList nl = doc.getElementsByTagName("start_date_etap1");
		 Element movieElement = (Element) nl.item(0);
		 resp.setStart_date_etap1(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("end_date_etap1");
		 movieElement = (Element) nl.item(0);
		 resp.setEnd_date_etap1(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("start_date_etap2");
		 movieElement = (Element) nl.item(0);
		 resp.setStart_date_etap2(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("end_date_etap2");
		 movieElement = (Element) nl.item(0);
		 resp.setEnd_date_etap2(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("ref_id_person");
		 movieElement = (Element) nl.item(0);
		 resp.setRef_id_person(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("pm_god");
		 movieElement = (Element) nl.item(0);
		 resp.setPm_god(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("pm_kvartal");
		 movieElement = (Element) nl.item(0);
		 resp.setPm_kvartal(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("PM_HOSPITAL_RESULT");
		 movieElement = (Element) nl.item(0);
		 resp.setPM_HOSPITAL_RESULT(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("adress");
		 movieElement = (Element) nl.item(0);
		 resp.setAdress(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("tel");
		 movieElement = (Element) nl.item(0);
		 resp.setTel(movieElement.getTextContent());
		 
		 nl = doc.getElementsByTagName("pm_result");
		 movieElement = (Element) nl.item(0);
		 resp.setPm_result(movieElement.getTextContent());

		
		return resp;
	}
}
