package com.careful.clinic.dao.prophylactic;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

import com.careful.clinic.exceptions.ParseDataExcelException;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.PmMo2017;
import com.careful.clinic.model.ResponseGer;
import com.careful.clinic.model.WrapPmI;
import com.careful.clinic.upload.interfase.IDataUploadType;

@Stateless
public class XA_Dream2DaoBean implements XA_Dream2Dao{

	@PersistenceContext(unitName="OracleDream2DS")
    private EntityManager em_dream2;
	private int countDouble = 0;
	private int countStr = 0;
    private ArrayList doubleList = new ArrayList();
	//private int countRows = 0;




	public void pasteResultPm_a(String sql){
		sql = sql.replaceAll("\"", "");
        sql = "insert into  pm_a (ID,FAM,IM,OT,DR,D_INFO,TYPE_INFO,PRIM,SMO,DATA,D_INSERT)  values"+sql;
		
		Query q = em_dream2.createNativeQuery(sql);
		 q.executeUpdate();
		 //em_dream2.getTransaction().commit();
	}
	
	public Collection<?> getSurveyInform(PersonModel personmodel){
		
		String sb = "select distinct p.fam, p.im, p.ot, p.d_info, p.type_info, p.prim, p.smo, p.stat, p.error from pm_a p where p.fam='"+personmodel.getSurname()+"' and p.im='"+personmodel.getFirstname()+"' and p.ot='"+personmodel.getLastname()+"' and p.dr='"+personmodel.getBithday()+"' order by d_info desc, type_info ";
		Query q = em_dream2.createNativeQuery(sb);
	    List<Object[]> ls = q.getResultList();
	    
		return ls;
	}
	
	public Collection<?> getInfoInform(PersonModel personmodel) throws ParseException{
		
		String queryStr = "SELECT NEW com.careful.clinic.model.WrapPmI(c.fam, c.im, c.ot, c.dr, c.nStage, c.dInfo, c.tInfo, c.smo) FROM PmI c WHERE c.fam = :fam "
												     + "and c.im =:im and"
												     + " c.ot =:ot and "
												     +" c.dInfo between '01.01."+personmodel.getYear()+"' and '31.12."+personmodel.getYear()+"' and "
												     + "c.dr =:dr order by c.dInfo desc";
												     
			  TypedQuery <WrapPmI> query = em_dream2.createQuery(queryStr, WrapPmI.class)
					  .setParameter("fam", personmodel.getSurname().toUpperCase())
						.setParameter("im", personmodel.getFirstname().toUpperCase())
						.setParameter("ot", personmodel.getLastname().toUpperCase())
						//.setParameter("year_start", personmodel.getYear())
						.setParameter("dr", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
			  
			  List<?> results = query.getResultList();
		/*TypedQuery<PmI> query = em_dream2.createNamedQuery("PmI.findByFIOD", PmI.class)
        		
				.setParameter("fam", personmodel.getSurname().toUpperCase())
				.setParameter("im", personmodel.getFirstname().toUpperCase())
				.setParameter("ot", personmodel.getLastname().toUpperCase())
				.setParameter("dr", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));

		List<PmI> ls = query.getResultList();
*/
			  
			  Set s = new HashSet<>(results);
		return s;
		
	}
	
	public Collection<?> getInfoPlanInform(Integer adressid) throws ParseException{
		
		TypedQuery<PmMo2017> query = em_dream2.createNamedQuery("PmMo2017.findByAdressid", PmMo2017.class)
        		
				.setParameter("tfomsId", adressid);
		
		return query.getResultList();
		
	}
	//ToDo in branch "mis" create a select from MIS DB
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Collection<?> getInfoG(PersonModel personmodel) throws ParseException, ParserConfigurationException, SAXException, IOException{
		
		
		StoredProcedureQuery storedProcedure =  em_dream2.createStoredProcedureQuery("sys.connect_mis.disp_fiod");
        
        storedProcedure.registerStoredProcedureParameter("response",String.class, ParameterMode.OUT);
        
        storedProcedure.registerStoredProcedureParameter("surname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("firstname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("lastname",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("datebythday",String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("year",Integer.class, ParameterMode.IN);
        
        storedProcedure.setParameter("surname", personmodel.getSurname());
        storedProcedure.setParameter("firstname", personmodel.getFirstname());
        storedProcedure.setParameter("lastname", personmodel.getLastname());
        storedProcedure.setParameter("datebythday", personmodel.getBithday());
        storedProcedure.setParameter("year", personmodel.getYear());

        storedProcedure.execute();

        String respXml = (String)storedProcedure.getOutputParameterValue("response");
        ResponseGer rGer = parseResponse(respXml);
        List<ResponseGer> ls = new ArrayList<ResponseGer>(1);
        ls.add(rGer);
        
        
		
		return ls;
		
	}
	
	
	/*
	 * Вставка результатов опроса или информирования. Сам запрос  формируется при парсинге Excel и передается в коллекнцию (т.е. в коллекции уже готовые запросы)
	 * @param listOfQueryies
	 * 
	 */
	public boolean insertDataFromExcel(List<String> listOfQueryies,IDataUploadType data ) throws ParseDataExcelException{
		Query q = null;
		// TODO разграничить логику проверок для каждого 'фасона' загрузок. Условие if(data !=null){ временно пока не переду все под паттерн
		if(data !=null){
			doubleList.clear();
		for(String str : listOfQueryies){
			q = em_dream2.createNativeQuery(data.construct_querySelect(str));
			List f = q.getResultList();
			// если в базе нет полного дубля  то делаем вставку (т.е. избегаем дублирование записей в базе)
			if(Integer.valueOf(f.get(0).toString()) == 0 )
			{
				q = em_dream2.createNativeQuery(str);
				countStr++;
				q.executeUpdate();
			}
			else
            {
                doubleList.add(str);
				countDouble++;
            }
		}
		System.out.println("count double = "+countDouble);
		}else{
			for(String str : listOfQueryies){
					q = em_dream2.createNativeQuery(str);
					q.executeUpdate();
			}
		}
		return true;
	}

	@Override
	public String doubleValue() {
		String dv = String.valueOf(countDouble);
		countDouble = 0;
		return dv;
	}

	@Override
	public String doubleValueStr() {
		String ds = String.valueOf(countStr);
		countStr = 0;
		return ds;
	}

    @Override
    public String doubleStr() {
        ArrayList doubleListOut = new ArrayList();
        doubleListOut.add(doubleList);

	    return String.valueOf(doubleListOut);
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
	public ResponseGer parseResponse(String xml) throws ParserConfigurationException, SAXException, IOException{
		
		ResponseGer resp = new ResponseGer();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		 InputSource is = new InputSource(new StringReader(xml));
		 Document doc = dBuilder.parse(is);
		 doc.getDocumentElement().normalize();
		
		 
				 
				 NodeList nl = doc.getElementsByTagName("start_date_etap1");
				 Element movieElement = (Element) nl.item(0);
				 if(movieElement != null){  resp.setStart_date_etap1(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setStart_date_etap1("нет данных");}
				 
				 nl = doc.getElementsByTagName("end_date_etap1");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setEnd_date_etap1(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setEnd_date_etap1("нет данных");}
				 
				 nl = doc.getElementsByTagName("start_date_etap2");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setStart_date_etap2(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setStart_date_etap2("нет данных");}
				 
				 nl = doc.getElementsByTagName("end_date_etap2");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setEnd_date_etap2(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setEnd_date_etap2("нет данных");}
				 
				 nl = doc.getElementsByTagName("ref_id_person");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setRef_id_person(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setRef_id_person("нет данных");}
				 
				 nl = doc.getElementsByTagName("pm_god");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setPm_god(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setPm_god("нет данных");}
				 
				 nl = doc.getElementsByTagName("pm_kvartal");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setPm_kvartal(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setPm_kvartal("нет данных");}
				 
				 nl = doc.getElementsByTagName("PM_HOSPITAL_RESULT");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setPm_HOSPITAL_RESULT(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setPm_HOSPITAL_RESULT("нет данных");}
				 
				 nl = doc.getElementsByTagName("adress");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setAdress(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setAdress("нет данных");}
				 
				 nl = doc.getElementsByTagName("tel");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setTel(movieElement.getTextContent().replace("null", "нет данных"));}
				 else{resp.setTel("нет данных");}
				 
				 nl = doc.getElementsByTagName("pm_result");
				 movieElement = (Element) nl.item(0);
				 if(movieElement != null){ resp.setPm_result(movieElement.getTextContent().replace("-1", "нет данных"));}
				 else{resp.setPm_result("нет данных");}
		
		
		return resp;
	}
}
