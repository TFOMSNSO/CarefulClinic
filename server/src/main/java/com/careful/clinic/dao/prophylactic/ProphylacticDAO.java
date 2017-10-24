package com.careful.clinic.dao.prophylactic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

import com.careful.clinic.model.ListExcelFiles;
import com.careful.clinic.model.Person;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.PmMo2017;
import com.careful.clinic.model.ResponseGer;
import com.careful.clinic.model.SearchKeysModel;
import com.careful.clinic.model.WrapPmI;
import com.careful.clinic.model.WrapRespSerarchKeys;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Stateless
@LocalBean
public class ProphylacticDAO {

	
	@PersistenceContext(unitName="OracleDream2DS")
    private EntityManager em_dream2;
	@PersistenceContext(unitName="OracleDSDeveloper")
    private EntityManager em_developer;
	@PersistenceContext(unitName="NONXASDAME")
    private EntityManager EM_NONXASDAME;
	
	
	public Collection<?> getInfoInsur(PersonModel personmodel) throws ParseException{
		
		TypedQuery<Person> query = em_developer.createNamedQuery("Person.findByFIOD", Person.class)
        		
				.setParameter("personSurname", personmodel.getSurname().toUpperCase())
				.setParameter("personKindfirstname", personmodel.getFirstname().toUpperCase())
				.setParameter("personKindlastname", personmodel.getLastname().toUpperCase())
				.setParameter("personBirthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
		
		List<?> ls = query.getResultList();
		return ls;
		
	}
	
	/**
	 * @param personmodel
	 * @return
	 * @throws Exception 
	 */
	public Collection<?> getInfoInsurkeys(SearchKeysModel personmodel) throws Exception{
		
/*		SELECT * FROM Person p  
		LEFT JOIN Personadd pa ON  p.PERSON_ADDRESSID = pa.PERSONADD_ADDRESSID
		LEFT JOIN
		(select distinct t.id, t.fam, t.im, t.ot, t.dr, t.n_stage, t.d_info, t.t_info, t.smo from pm_i@ofoms t) pmi
		ON (p.person_surname=pmi.fam and p.PERSON_KINDFIRSTNAME=pmi.im and p.PERSON_KINDLASTNAME=pmi.ot and p.PERSON_BIRTHDAY = pmi.dr ) 
		
		where ( pa.tele2 is not null or pa.teledom  is not null or pa.telework is not null) and
		p.person_linksmoestablishmentid > 0 --and p.person_surname='КУЗНЕЦОВА' AND p.person_kindfirstname='ИРИНА' AND P.PERSON_KINDLASTNAME='НИКОЛАЕВНА' AND P.PERSON_BIRTHDAY='11.05.1975'
		and
		p.person_establishmentambul > 0
		and
		--(p.person_sex>= 0  AND ((floor(months_between(sysdate,p.person_birthday))/12) > 0 and (floor(months_between(sysdate,p.person_birthday))/12) < 150))
		( (p.person_sex=0   AND ((floor(months_between(sysdate,p.person_birthday))/12) > 0 and (floor(months_between(sysdate,p.person_birthday))/12) < 150))
		or
		(p.person_sex=1   AND ((floor(months_between(sysdate,p.person_birthday))/12) > 0 and (floor(months_between(sysdate,p.person_birthday))/12) < 150))
		)
		and
		(
		--pmi.n_stage in (0,1,2,3) and pmi.t_info in (1,2,3,4,5,6,7) and trunc(pmi.d_info) between '01.01.1017' and '31.12.3017'
		(pmi.n_stage=0 and pmi.t_info in (1,2,3,4,5,6,7) and trunc(pmi.d_info) between '01.01.1017' and '31.12.3017')
		or
		(pmi.n_stage=1 and pmi.t_info in (1,2,3,4,5,6,7) and trunc(pmi.d_info) between '01.01.1017' and '31.12.3017')
		or
		(pmi.n_stage=2 and pmi.t_info in (1,2,3,4,5,6,7) and trunc(pmi.d_info) between '01.01.1017' and '31.12.3017')
		or
		(pmi.n_stage=3 and pmi.t_info in (1,2,3,4,5,6,7) and trunc(pmi.d_info) between '01.01.1017' and '31.12.3017')
		)
		order by p.person_addressid desc
		fetch first 1000 rows with ties

*/
		
		  		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
				sb.append(" p.person_surname,");
				sb.append(" p.PERSON_KINDFIRSTNAME,");
				sb.append(" p.PERSON_KINDLASTNAME,");
				sb.append(" p.PERSON_BIRTHDAY,");
				sb.append(" p.person_linksmoestablishmentid,");
				sb.append(" p.person_establishmentambul, ");
				sb.append(" pa.tele2, pa.teledom, pa.telework ");
				sb.append(" FROM Person p  ");
				sb.append(" LEFT JOIN Personadd pa ON  p.PERSON_ADDRESSID = pa.PERSONADD_ADDRESSID");
				sb.append(" LEFT JOIN ");
				sb.append(" (select distinct t.id, t.fam, t.im, t.ot, t.dr, t.n_stage, t.d_info, t.t_info, t.smo from pm_i@ofoms t) pmi");
				sb.append(" ON (p.person_surname=pmi.fam and p.PERSON_KINDFIRSTNAME=pmi.im and p.PERSON_KINDLASTNAME=pmi.ot and p.PERSON_BIRTHDAY = pmi.dr ) ");
				sb.append(" where ");
				if(personmodel.getIsTelefon().equals("true")){
					sb.append("( pa.tele2 is not null or pa.teledom  is not null or pa.telework is not null) and ");
				}
				sb.append(" p.person_linksmoestablishmentid ");
/* ==== линксмо==== */
		sb.append(" > 0");
		sb.append(" and  p.person_establishmentambul");
		if(personmodel.getTypeMo().equals("")) {sb.append(" > 0 ");}else{sb.append(" ="+personmodel.getTypeMo()+" ");}
		
	{
		if(!(personmodel.getMansAge().equals("false") && personmodel.getMaleAge().equals("false"))){
			sb.append(" and");  
			sb.append(" ( ");
		}
		
		if((personmodel.getMansAge().equals("true") && personmodel.getMaleAge().equals("false")) ||
				(personmodel.getMansAge().equals("true") && personmodel.getMaleAge().equals("true"))){
			
			sb.append(" (p.person_sex=0   AND ((floor(months_between(sysdate,p.person_birthday))/12) >= ");
			if(personmodel.getMansAge().equals("false")){
				sb.append(" 0 ");
			}else{
				if(personmodel.getMansAge().equals("true") && !personmodel.getManAgemin().equals("")){
					sb.append(personmodel.getManAgemin());
				}
			}
			sb.append(" and (floor(months_between(sysdate,p.person_birthday))/12) <= ");
			if(personmodel.getMansAge().equals("false")){
				sb.append(" 150 "); 
			}else{
				if(personmodel.getMansAge().equals("true") && !personmodel.getManAgemax().equals("")){
					sb.append(personmodel.getManAgemax());
				}
			}
			sb.append(" ))");
		}
	}
	{
		if(personmodel.getMansAge().equals("true") && personmodel.getMaleAge().equals("true")){
					sb.append(" or ");
		}
		if((personmodel.getMansAge().equals("false") && personmodel.getMaleAge().equals("true")) ||
				(personmodel.getMansAge().equals("true") && personmodel.getMaleAge().equals("true"))){
			
					sb.append(" (p.person_sex=1   AND ((floor(months_between(sysdate,p.person_birthday))/12) >= ");
					if(personmodel.getMaleAge().equals("false")){
						sb.append(" 0 ");
					}else{
						if(personmodel.getMaleAge().equals("true") && !personmodel.getMaleAgemin().equals("")){
							sb.append(personmodel.getMaleAgemin());
						}
					}
					sb.append(" and (floor(months_between(sysdate,p.person_birthday))/12) <= ");
					if(personmodel.getMaleAge().equals("false")){
						sb.append(" 150 ");
					}else{
						if(personmodel.getMaleAge().equals("true") && !personmodel.getMaleAgemax().equals("")){
							sb.append(personmodel.getMaleAgemax());
						}
					}
					sb.append(" )) ");
		}
	}	
	if(!(personmodel.getMansAge().equals("false") && personmodel.getMaleAge().equals("false"))){
		sb.append(" )"); // для закрытия or ммежду мужским и женским полом	
	}
	
	if(personmodel.isNullstate() == true || personmodel.getFirststate().equals("true") || personmodel.getSecondstate().equals("true") || personmodel.getThridstate().equals("true"))
	{	
			sb.append(" and ");
			sb.append(" ( ");
		{
			// добавить обязательно условие в валидацию что даты должны быть указаны обе
			if(personmodel.isNullstate() == true && personmodel.isNullmedicalexamination_conducted() == false){
				sb.append(" (pmi.n_stage=0 ");}
			if(personmodel.isNullstate() == true && personmodel.isNullmedicalexamination_conducted() == true){
				sb.append(" (pmi.n_stage=0 and pmi.t_info =7");}
				if(personmodel.isNullstate() == true && !personmodel.getFirstdatenullstate().equals("") && !personmodel.getSeconddatenullstate().equals("")){
					sb.append(" and trunc(pmi.d_info) between ");
						sb.append("'");
							sb.append(personmodel.getFirstdatenullstate());				
						sb.append("'");
							sb.append(" and ");
								sb.append("'");
									sb.append(personmodel.getSeconddatenullstate());
								sb.append("'");
									
				}
				if(personmodel.isNullstate() == true){sb.append(" ) ");}
		}
		{
			if(personmodel.isNullstate() == true && personmodel.getFirststate().equals("true")){
				sb.append(" or ");				
			}
			if(personmodel.getFirststate().equals("true")){
				sb.append("(pmi.n_stage=1 ");
				List<String> conditions = new ArrayList<String>();
				if(personmodel.getFirstsms().equals("true")){ conditions.add("1");}
				if(personmodel.getFirsttel().equals("true")){conditions.add("2");}
				if(personmodel.getFirstemail().equals("true")){conditions.add("3");}
				if(personmodel.getFirstmail().equals("true")){conditions.add("4");}
				if(personmodel.getFirstpersonal_information().equals("true")){conditions.add("5");}
				if(personmodel.getFirstinforming_inadmissible().equals("true")){conditions.add("6");}
				if(personmodel.getFirstmedicalexamination_conducted().equals("true")){conditions.add("7");}
				
				if(!conditions.isEmpty()){ sb.append(" and pmi.t_info in "); sb.append(conditions.toString().replace("[", "(").replaceAll("]", ")"));}
				
				if(!personmodel.getFirstdatefirststate().equals("") && !personmodel.getSeconddatefirststate().equals("")){
					sb.append(" and trunc(pmi.d_info) between ");
						sb.append("'");
							sb.append(personmodel.getFirstdatefirststate());
						sb.append("'");
							sb.append(" and ");
							sb.append("'");
								sb.append(personmodel.getSeconddatefirststate());
							sb.append("'");
									
				}
				sb.append(" ) ");
			}
		}
		{
			if((personmodel.isNullstate() == true || personmodel.getFirststate().equals("true")) && personmodel.getSecondstate().equals("true")){
				sb.append(" or ");				
			}
			if(personmodel.getSecondstate().equals("true")){
				sb.append("(pmi.n_stage=2 ");
				List<String> conditions = new ArrayList<String>();
				if(personmodel.getSecondsms().equals("true")){ conditions.add("1");}
				if(personmodel.getSecondtel().equals("true")){conditions.add("2");}
				if(personmodel.getSecondemail().equals("true")){conditions.add("3");}
				if(personmodel.getSecondmail().equals("true")){conditions.add("4");}
				if(personmodel.getSecondpersonal_information().equals("true")){conditions.add("5");}
				if(personmodel.getSecondinforming_inadmissible().equals("true")){conditions.add("6");}
				if(personmodel.getSecondmedicalexamination_conducted().equals("true")){conditions.add("7");}
				
				if(!conditions.isEmpty()){ sb.append(" and pmi.t_info in "); sb.append(conditions.toString().replace("[", "(").replaceAll("]", ")"));}
				
				if(!personmodel.getFirstdatesecondstate().equals("") && !personmodel.getSeconddatesecondstate().equals("")){
					sb.append(" and trunc(pmi.d_info) between ");
						sb.append("'");
							sb.append(personmodel.getFirstdatesecondstate());
						sb.append("'");
							sb.append(" and ");
							sb.append("'");
								sb.append(personmodel.getSeconddatesecondstate());
							sb.append("'");
									
				}
				sb.append(" ) ");
			}
			
		}	
		{
			if((personmodel.isNullstate() == true || personmodel.getFirststate().equals("true") ||
					personmodel.getSecondstate().equals("true")) && personmodel.getThridstate().equals("true")){
				sb.append(" or ");				
			}
			if(personmodel.getThridstate().equals("true")){
				sb.append("(pmi.n_stage=3 ");
				List<String> conditions = new ArrayList<String>();
				if(personmodel.getThridsms().equals("true")){ conditions.add("1");}
				if(personmodel.getThridtel().equals("true")){conditions.add("2");}
				if(personmodel.getThridemail().equals("true")){conditions.add("3");}
				if(personmodel.getThridmail().equals("true")){conditions.add("4");}
				if(personmodel.getThridpersonal_information().equals("true")){conditions.add("5");}
				if(personmodel.getThridinforming_inadmissible().equals("true")){conditions.add("6");}
				if(personmodel.getThridmedicalexamination_conducted().equals("true")){conditions.add("7");}
				
				if(!conditions.isEmpty()){ sb.append(" and pmi.t_info in "); sb.append(conditions.toString().replace("[", "(").replaceAll("]", ")"));}
				
				if(!personmodel.getFirstdatethridstate().equals("") && !personmodel.getSeconddatethridstate().equals("")){
					sb.append(" and trunc(pmi.d_info) between ");
					sb.append("'");
						sb.append(personmodel.getFirstdatethridstate());
					sb.append("'");
							sb.append(" and ");
							sb.append("'");
								sb.append(personmodel.getSeconddatethridstate());
							sb.append("'");
									
				}
				sb.append(" ) ");
			}
		}
			
		sb.append(" ) ");
	}
		sb.append(" order by p.person_addressid desc fetch first ");
/*==== ВЫВОД КОЛИЧЕСТВА ЗАПИСЕЙ ====*/		
		sb.append(personmodel.getCount_notes());
		sb.append(" rows with ties");
	
		System.out.println(sb.toString());
		Query q = EM_NONXASDAME.createNativeQuery(sb.toString());
	    List<Object[]> ls = q.getResultList();
	    System.out.println("TEST "+ls);
	    System.out.println("TEST "+ls.size());
	    
	    PersonModel p = null;
	    Map<PersonModel,ResponseGer> map = new HashMap<PersonModel,ResponseGer>();
	    List<WrapRespSerarchKeys> result = new ArrayList<WrapRespSerarchKeys>();
	    
	    //if(!personmodel.getPm_result().equals("")){
	    	WrapRespSerarchKeys wrsk = null;
	    	for(int i=0; i<ls.size()-1;i++){
	    		Object[] obj = ls.get(i);
	    		String f =  obj[3].toString().substring(8,10)+"."+obj[3].toString().substring(5, 7)+"."+obj[3].toString().substring(0, 4);
	    		String tele2="",telework="",teledom="";
	    		if(obj[6] != null)   tele2 = obj[6].toString() ;
	    		if(obj[7] != null)   telework = obj[7].toString();
	    		if(obj[8] != null)  teledom =  obj[8].toString();
	    		
	    		wrsk = new WrapRespSerarchKeys(obj[0].toString(), obj[1].toString(), obj[2].toString(), f,obj[4].toString(),obj[5].toString(), tele2,telework,teledom,personmodel.getCurrentUser());
	    		
	    		/*подумать над выборкой если человек информирован оба этапа а поиск хочет вытянуть только кто один этап
	    		делать вали
	    		 * */
	    		p = new PersonModel(obj[0].toString(),obj[1].toString(),obj[2].toString(),f);
		    	
		    	if(map.containsKey(p)){
		    		wrsk.updateRespGerl(map.get(p));
		    	}else{
		    		if(!personmodel.getPm_result().equals("")){
		    			List<ResponseGer> tmp =(List<ResponseGer>) getInfoG(p);
			    		// не прошел диспасеризацию (без привязке ко времени)
		    			System.out.println(wrsk.getPersonSurname()+" - "+tmp.get(0).getPm_result());
			    		if(personmodel.getPm_result().equals("0") && (tmp.get(0).getPm_result().equals("0") || tmp.get(0).getPm_result().equals("нет данных")) ){
			    				map.put(p, tmp.get(0));
					    		wrsk.updateRespGerl(tmp.get(0));
			    		// начал прохождение первого этапа (без привязке ко времени)
			    		}else if(personmodel.getPm_result().equals("1") && tmp.get(0).getPm_result().equals("1")){
			    			map.put(p, tmp.get(0));
				    		wrsk.updateRespGerl(tmp.get(0));
			    		// прошел первый этап (без привязке ко времени)
			    		}else if(personmodel.getPm_result().equals("2") && tmp.get(0).getPm_result().equals("2")){
			    			map.put(p, tmp.get(0));
				    		wrsk.updateRespGerl(tmp.get(0));
			    		// начал прохождение второго этапа (без привязке ко времени)
			    		}else if(personmodel.getPm_result().equals("3") && tmp.get(0).getPm_result().equals("3")){
			    			map.put(p, tmp.get(0));
				    		wrsk.updateRespGerl(tmp.get(0));
			    		// начал прохождение второго этапа (без привязке ко времени)
			    		}else if(personmodel.getPm_result().equals("5") && tmp.get(0).getPm_result().equals("5")){
			    			map.put(p, tmp.get(0));
				    		wrsk.updateRespGerl(tmp.get(0));
			    		}else{
			    			continue;
			    		}
			    		
		    		}else{
		    			List<ResponseGer> tmp = new ArrayList<ResponseGer>(1);
		    			tmp.add(new ResponseGer());
		    			wrsk.updateRespGerl(tmp.get(0));
		    		}
		    	}
		    	
		    	result.add(wrsk);
	    	}
	    	
	    	if(personmodel.isExportExcel()){
	    		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
	    		String current_time_str = time_formatter.format(System.currentTimeMillis());
	    		
	    		writeListToFile("org_" + result.get(0).getCurrentUser()+"_"+current_time_str+".xlsx",result);
	    	}
	    	
	    System.out.println("dd "+result);
	      return result;
		
		
		
	}
	
	public Collection<?> getSurveyInform(PersonModel personmodel){
		
		String sb = "select distinct p.fam, p.im, p.ot, p.d_info, p.type_info, p.prim, p.smo from pm_a p where p.fam='"+personmodel.getSurname()+"' and p.im='"+personmodel.getFirstname()+"' and p.ot='"+personmodel.getLastname()+"' and p.dr='"+personmodel.getBithday()+"' ";
		Query q = em_dream2.createNativeQuery(sb);
	    List<Object[]> ls = q.getResultList();
	    
		return ls;
	}
	
	public Collection<?> getInfoInform(PersonModel personmodel) throws ParseException{
		
		String queryStr = "SELECT NEW com.careful.clinic.model.WrapPmI(c.fam, c.im, c.ot, c.dr, c.nStage, c.dInfo, c.tInfo, c.smo) FROM PmI c WHERE c.fam = :fam "
												     + "and c.im =:im and"
												     + " c.ot =:ot and "
												     + "c.dr =:dr order by c.dInfo desc";
												     
			  TypedQuery <WrapPmI> query = em_dream2.createQuery(queryStr, WrapPmI.class)
					  .setParameter("fam", personmodel.getSurname().toUpperCase())
						.setParameter("im", personmodel.getFirstname().toUpperCase())
						.setParameter("ot", personmodel.getLastname().toUpperCase())
						.setParameter("dr", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
			  
			  List<?> results = query.getResultList();
		/*TypedQuery<PmI> query = em_dream2.createNamedQuery("PmI.findByFIOD", PmI.class)
        		
				.setParameter("fam", personmodel.getSurname().toUpperCase())
				.setParameter("im", personmodel.getFirstname().toUpperCase())
				.setParameter("ot", personmodel.getLastname().toUpperCase())
				.setParameter("dr", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));

		List<PmI> ls = query.getResultList();
		System.out.println("LSSSS "+ls);
*/
			  
			  Set s = new HashSet<>(results);
			  System.out.println("LSSSS "+s);
		return s;
		
	}
	
	public Collection<?> exportToExcel(ArrayList<WrapRespSerarchKeys> wrapRespSerarchKeys) throws Exception{
		
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
		String current_time_str = time_formatter.format(System.currentTimeMillis());
		
		writeListToFile("org_" + wrapRespSerarchKeys.get(0).getCurrentUser()+"_"+current_time_str+".xlsx", wrapRespSerarchKeys); 
		
		
		return new ArrayList<>();
		
	}
	
	public Collection<?> getInfoPlanInform(Integer adressid) throws ParseException{
		
		TypedQuery<PmMo2017> query = em_dream2.createNamedQuery("PmMo2017.findByAdressid", PmMo2017.class)
        		
				.setParameter("tfomsId", adressid);
		
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
	
	public  void writeListToFile(String fileName, List<WrapRespSerarchKeys> wrapRespSerarchKeys) throws Exception{
		Workbook workbook = null;
		
		if(fileName.endsWith("xlsx")){
			workbook = new XSSFWorkbook();
		}else if(fileName.endsWith("xls")){
			workbook = new HSSFWorkbook();
		}else{
			throw new Exception("invalid file name, should be xls or xlsx");
		}
		
		Sheet sheet = workbook.createSheet("Данные");
		
		Iterator<WrapRespSerarchKeys> iterator = wrapRespSerarchKeys.iterator();
		
		int rowIndex = 0;
		while(iterator.hasNext()){
			WrapRespSerarchKeys Keys = iterator.next();
			Row row = sheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(Keys.getPersonSurname());
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(Keys.getPersonBirthday());
		}
		
		//lets write the excel data to file now
		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(wrapRespSerarchKeys.get(0).getCurrentUser() == 777) directoryDestination = "\\content\\donwload\\777";
		if(wrapRespSerarchKeys.get(0).getCurrentUser() == 1)	directoryDestination = "\\content\\donwload\\1";
		if(wrapRespSerarchKeys.get(0).getCurrentUser() == 2)	directoryDestination = "\\content\\donwload\\2";
		if(wrapRespSerarchKeys.get(0).getCurrentUser() == 4)	directoryDestination = "\\content\\donwload\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		String filepath = Paths.get(directoryDestination, fileName).toString();
		
		FileOutputStream fos = new FileOutputStream(filepath);
		workbook.write(fos);
		fos.close();
		System.out.println(fileName + " written successfully");
	}
	
	public Collection<?> getListExcelFiles(Integer id){
		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\donwload\\777";
		if(id == 1)	directoryDestination = "\\content\\donwload\\1";
		if(id == 2)	directoryDestination = "\\content\\donwload\\2";
		if(id == 4)	directoryDestination = "\\content\\donwload\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
		}
		
		
		
		return ls;
	}
	
}
