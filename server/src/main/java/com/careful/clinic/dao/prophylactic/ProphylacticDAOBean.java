package com.careful.clinic.dao.prophylactic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import com.careful.clinic.model.ListExcelFiles;
import com.careful.clinic.model.Person;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.ResponseGer;
import com.careful.clinic.model.ResponsePmMo17;
import com.careful.clinic.model.SearchKeysModel;
import com.careful.clinic.model.WrapRespSerarchKeys;
import com.careful.clinic.upload.interfase.factory.UploadDataFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

@Stateless
public class ProphylacticDAOBean implements ProphylacticDAO{

	@EJB
	public XA_Dream2Dao xa_Dream2Dao;
	// TODO Сделать фабрику как синглетон. 
	@EJB
	public UploadDataFactory  uploadFactory;
	
	@PersistenceContext(unitName="OracleDSDeveloper")
    private EntityManager em_developer;
	@PersistenceContext(unitName="NONXASDAME")
	private EntityManager EM_NONXASDAME;

	private int count = 0;
	
	public Collection<?> getInfoInsur(PersonModel personmodel) throws ParseException{
		System.out.println("getInfoInsur");
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
	 * 			
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public Collection<?> getInfoInsurkeys(SearchKeysModel personmodel) throws Exception{
		
		  		StringBuilder sb = new StringBuilder();
		  		sb.append("SELECT");
				sb.append(" p.person_surname,");
				sb.append(" p.PERSON_KINDFIRSTNAME,");
				sb.append(" p.PERSON_KINDLASTNAME,");
				sb.append(" p.PERSON_BIRTHDAY,");
				sb.append(" p.person_linksmoestablishmentid,");
				sb.append(" p.person_establishmentambul, ");
				sb.append(" pa.tele2, pa.teledom, pa.telework ");
				//if(personmodel.getPlan_disp() > 0){
					sb.append(", pl2.tfoms_id, pl2.god, pl2.kv, pl2.date_begin, pl2.date_end ");
					
				//}
				//if(personmodel.getFirststate().equals("true") || personmodel.getSecondstate().equals("true") || personmodel.getThridstate().equals("true") || personmodel.isNo_thridstate()
				//		|| personmodel.isNo_firststate() || personmodel.isNo_secondstate()){
					sb.append(", pmi.stage1, pmi.stage2, pmi.stage3 ");
					sb.append(", pma.d_info, pma.type_info, pma.smo ");
				//}
				sb.append(" FROM Person p  ");
				//if(personmodel.getPlan_disp() > 0){
					sb.append(" LEFT JOIN ");
					sb.append(" (select pl.tfoms_id, pl.god, pl.kv, pl.date_begin, pl.date_end from PM_MO_2017@ofoms pl where pl.kv="+personmodel.getPlan_disp()+" and '31.12.2017' between trunc(DATE_BEGIN) and trunc(DATE_END)) pl2");
					sb.append(" ON  p.PERSON_ADDRESSID = pl2.TFOMS_ID ");
				//}
				//if(personmodel.getFirststate().equals("true") || personmodel.getSecondstate().equals("true") || personmodel.getThridstate().equals("true") || personmodel.isNo_thridstate()
//						|| personmodel.isNo_firststate() || personmodel.isNo_secondstate()){
					sb.append(" LEFT JOIN ");
					//sb.append(" (select t.fam, t.im, t.ot, t.dr, sum(case when t.n_stage = 1 then 1 else 0 end) stage1,sum(case when t.n_stage = 2 then 1 else 0 end) stage2, sum(case when t.n_stage = 3 then 1 else 0 end) stage3 from pm_i@ofoms t  group by t.fam, t.im, t.ot, t.dr order by t.fam, t.im, t.ot, t.dr");
					sb.append(" (select * from view_pm_i_v1 ");
					/*if(personmodel.getFirststate().equals("true")){
						sb.append("(t.n_stage=1 ");
						List<String> conditions = new ArrayList<String>();
						if(personmodel.getFirstsms().equals("true")){ conditions.add("1");}
						if(personmodel.getFirsttel().equals("true")){conditions.add("2");}
						if(personmodel.getFirstemail().equals("true")){conditions.add("3");}
						if(personmodel.getFirstmail().equals("true")){conditions.add("4");}
						if(personmodel.getFirstpersonal_information().equals("true")){conditions.add("5");}
						if(personmodel.getFirstinforming_inadmissible().equals("true")){conditions.add("6");}
						if(personmodel.getFirstmedicalexamination_conducted().equals("true")){conditions.add("7");}
						
						if(!conditions.isEmpty()){ sb.append(" and t.t_info in "); sb.append(conditions.toString().replace("[", "(").replaceAll("]", ")"));}
						
						if(!personmodel.getFirstdatefirststate().equals("") && !personmodel.getSeconddatefirststate().equals("")){
							sb.append(" and trunc(t.d_info) between ");
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
					if(personmodel.getFirststate().equals("true") && personmodel.getSecondstate().equals("true")){
						sb.append(" or ");				
					}
					if(personmodel.getSecondstate().equals("true")){
						sb.append("(t.n_stage=2 ");
						List<String> conditions = new ArrayList<String>();
						if(personmodel.getSecondsms().equals("true")){ conditions.add("1");}
						if(personmodel.getSecondtel().equals("true")){conditions.add("2");}
						if(personmodel.getSecondemail().equals("true")){conditions.add("3");}
						if(personmodel.getSecondmail().equals("true")){conditions.add("4");}
						if(personmodel.getSecondpersonal_information().equals("true")){conditions.add("5");}
						if(personmodel.getSecondinforming_inadmissible().equals("true")){conditions.add("6");}
						if(personmodel.getSecondmedicalexamination_conducted().equals("true")){conditions.add("7");}
						
						if(!conditions.isEmpty()){ sb.append(" and t.t_info in "); sb.append(conditions.toString().replace("[", "(").replaceAll("]", ")"));}
						
						if(!personmodel.getFirstdatesecondstate().equals("") && !personmodel.getSeconddatesecondstate().equals("")){
							sb.append(" and trunc(t.d_info) between ");
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
					if((personmodel.getFirststate().equals("true") || personmodel.getSecondstate().equals("true")) && personmodel.getThridstate().equals("true")){
						sb.append(" or ");				
					}
					if(personmodel.getThridstate().equals("true")){
						sb.append("(t.n_stage=3 ");
						List<String> conditions = new ArrayList<String>();
						if(personmodel.getThridsms().equals("true")){ conditions.add("1");}
						if(personmodel.getThridtel().equals("true")){conditions.add("2");}
						if(personmodel.getThridemail().equals("true")){conditions.add("3");}
						if(personmodel.getThridmail().equals("true")){conditions.add("4");}
						if(personmodel.getThridpersonal_information().equals("true")){conditions.add("5");}
						if(personmodel.getThridinforming_inadmissible().equals("true")){conditions.add("6");}
						if(personmodel.getThridmedicalexamination_conducted().equals("true")){conditions.add("7");}
						
						if(!conditions.isEmpty()){ sb.append(" and t.t_info in "); sb.append(conditions.toString().replace("[", "(").replaceAll("]", ")"));}
						
						if(!personmodel.getFirstdatethridstate().equals("") && !personmodel.getSeconddatethridstate().equals("")){
							sb.append(" and trunc(t.d_info) between ");
							sb.append("'");
								sb.append(personmodel.getFirstdatethridstate());
							sb.append("'");
									sb.append(" and ");
									sb.append("'");
										sb.append(personmodel.getSeconddatethridstate());
									sb.append("'");
											
						}
						sb.append(" ) ");
					}*/
					sb.append(") pmi ");
					sb.append(" ON (p.person_surname=pmi.fam and p.PERSON_KINDFIRSTNAME=pmi.im and p.PERSON_KINDLASTNAME=pmi.ot and p.PERSON_BIRTHDAY = pmi.dr ) ");					
	//			}
//				if(!personmodel.getSurvey_stat().equals("")){
					sb.append(" LEFT JOIN ");
					sb.append(" (select distinct q.fam, q.im, q.ot, q.dr, q.d_info, q.type_info, q.prim, q.smo from pm_a@ofoms q ) pma ");
					sb.append(" ON (p.person_surname=pma.fam and p.PERSON_KINDFIRSTNAME=pma.im and p.PERSON_KINDLASTNAME=pma.ot and p.PERSON_BIRTHDAY = pma.dr ) ");
//				}
				sb.append(" LEFT JOIN (select j.PERSONADD_ADDRESSID, j.tele2, j.teledom, j.telework  from Personadd j ) pa ON  p.PERSON_ADDRESSID = pa.PERSONADD_ADDRESSID");
				sb.append(" where ");
				if(personmodel.getPlan_disp() > 0){
					sb.append(" pl2.TFOMS_ID is not null and ");
				}
				String stage1 = "";
				String stage2 = "";
				String stage3="";
				if(personmodel.getFirststate().equals("true") || personmodel.getSecondstate().equals("true") || personmodel.getThridstate().equals("true")){
					
					if(personmodel.getFirststate().equals("true")){
						stage1="(pmi.stage1 > 0) and ";
					}else{	stage1="(pmi.stage1 = 0 or pmi.stage1 is null) and ";	}
					if(personmodel.getSecondstate().equals("true")){
						stage2="(pmi.stage2 > 0) and ";
					}else{	stage2="(pmi.stage2 = 0 or pmi.stage2 is null) and ";	}
					if(personmodel.getThridstate().equals("true")){
						stage3="(pmi.stage3 > 0) and ";
					}else{	stage3="(pmi.stage3 = 0 or pmi.stage3 is null) and ";	}
				}else{
					if(personmodel.isNo_firststate()){
						/*( (n_stage_1 = 0 или n_stage_1 =1) и n_stage_2 = 0  и ( n_stage_3 = 3 или n_stage_3 = 0 ) )*/
						stage1="(pmi.stage1 >= 0 or pmi.stage1 is null) and ";
						stage2="(pmi.stage2 = 0 or pmi.stage2 is null) and ";
						stage3="(pmi.stage3 >= 0 or pmi.stage3 is null) and ";
					}
					if(personmodel.isNo_secondstate()){
						/*( n_stage_1 =1 и n_stage_2 = 0  и ( n_stage_3 = 3 или n_stage_3 = 0 ) )*/
						stage1="(pmi.stage1 > 0) and ";
						stage2="(pmi.stage2 = 0 or pmi.stage2 is null) and ";
						stage3="(pmi.stage3 >= 0 or pmi.stage3 is null) and ";
					}
					if(personmodel.isNo_thridstate()){
						/*( (n_stage_1 = 0 или n_stage_1 =1) и n_stage_2 = 0  и ( n_stage_3 = 3 или n_stage_3 = 0 ) )*/
						stage1="(pmi.stage1 > 0) and ";
						stage2="(pmi.stage2 = 0 or pmi.stage2 is null) and ";
						stage3="(pmi.stage3 >= 0 or pmi.stage3 is null) and ";
					}
				}
				
				sb.append(stage1+stage2+stage3);
				
				if(!personmodel.getSurvey_stat().equals("")){
					if(personmodel.getSurvey_stat().equals("1")){ sb.append(" pma.fam is null ");}
					if(personmodel.getSurvey_stat().equals("2")){ sb.append(" pma.fam is not null ");}
					sb.append(" and ");
				}
				if(personmodel.getIsTelefon().equals("true")){
					sb.append("( pa.tele2 is not null or pa.teledom  is not null or pa.telework is not null) and ");
				}
				String g = " p.person_linksmoestablishmentid  in (1,2,4) ";
/* ==== линксмо==== */
				{	// 		isSimaz()  и тд... доступны только пользувателю тфомс то есть этот блок не будет отрабатывать если пользователь НЕ тфомс)
					 if(personmodel.isSimaz()){		g = " p.person_linksmoestablishmentid =1 ";	}
					 if(personmodel.isVtb()){		g = " p.person_linksmoestablishmentid =2 ";	}
					 if(personmodel.isIngos()){	g = " p.person_linksmoestablishmentid =4 ";	}
				}
		 if(personmodel.getCurrentUser() != 777){	g = " p.person_linksmoestablishmentid  ="+personmodel.getCurrentUser();	}  
		 
		 sb.append(g);
		 
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
	sb.append(" order by p.person_addressid desc fetch first ");
/*==== ВЫВОД КОЛИЧЕСТВА ЗАПИСЕЙ ====*/		
		sb.append(personmodel.getCount_notes());
		sb.append(" rows with ties");
		
	
		System.out.println(sb.toString());
		//String tmp1= "select p.person_surname, p.PERSON_KINDFIRSTNAME, p.PERSON_KINDLASTNAME, p.PERSON_BIRTHDAY, (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19)   from person p fetch first 400  rows with ties";
		Query q = EM_NONXASDAME.createNativeQuery(sb.toString());
	    List<Object[]> ls = q.getResultList();
	    
	    PersonModel p = null;
	    Map<PersonModel,ResponseGer> map = new HashMap<PersonModel,ResponseGer>();
	    List<WrapRespSerarchKeys> result = new ArrayList<WrapRespSerarchKeys>();
	    ResponsePmMo17 responsePmMo17  = null;
	    WrapRespSerarchKeys wrsk = null;
	    String tele2="",telework="",teledom="",f="";
	    StringBuilder sb1 = new StringBuilder();
	    List<ResponseGer> tmp = null;
	    //if(!personmodel.getPm_result().equals("")){
	    	
	    	for(int i=0; i<ls.size()-1;i++){
	    		Object[] obj = ls.get(i);
	    		
	    		sb1.append(obj[3].toString().substring(8,10));
	    		sb1.append(".");
	    		sb1.append(obj[3].toString().substring(5, 7));
	    		sb1.append(".");
	    		sb1.append(obj[3].toString().substring(0, 4));
	    		
	    		f =  sb1.toString();
	    		sb1.delete(0,sb1.length());
	    		
	    		if(obj[6] != null)   tele2 = obj[6].toString() ;
	    		if(obj[7] != null)   telework = obj[7].toString();
	    		if(obj[8] != null)  teledom =  obj[8].toString();
	    		
	    		wrsk = new WrapRespSerarchKeys(obj[0].toString(), obj[1].toString(), obj[2].toString(), f,Integer.valueOf(obj[4].toString()),obj[5].toString(), tele2,telework,teledom,personmodel.getCurrentUser());
	    		
	    		p = new PersonModel(obj[0].toString(),obj[1].toString(),obj[2].toString(),f,2018);
	    		
	    		//if(personmodel.getPlan_disp() > 0 && personmodel.getCurrentUser() == 777){
	    			responsePmMo17 = new ResponsePmMo17(obj[9] != null ? obj[9].toString() : "", obj[10] != null ? obj[10].toString() : "",obj[11] != null ?  obj[11].toString() : "" ,obj[12] != null ?  obj[12].toString() : "",obj[13] != null ?  obj[13].toString() : "");
	    			wrsk.setRespPlan(responsePmMo17);
	    			wrsk.setCount_pmIstages(obj[14] != null ? obj[14].toString() : "", obj[15] != null ?  obj[15].toString() : "" ,obj[16] != null ?  obj[16].toString() : "");
	    			wrsk.setPma_d_info(obj[17] != null ?  obj[17].toString() : "");
	    			wrsk.setPma_type_info(obj[18] != null ?  obj[18].toString() : "");
	    			wrsk.setPma_smo(obj[19] != null ?  obj[19].toString() : "");
	    		//}
	    			
		    	if(map.containsKey(p)){
		    		wrsk.updateRespGerl(map.get(p));
		    	}else{
		    		if(!personmodel.getPm_result().equals("")){
		    			 tmp =(List<ResponseGer>) xa_Dream2Dao.getInfoG(p);
			    		// не прошел диспасеризацию (без привязке ко времени)
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
		    			 tmp =(List<ResponseGer>) xa_Dream2Dao.getInfoG(p);
		    			//List<ResponseGer> tmp = new ArrayList<ResponseGer>(1);
		    			//tmp.add(new ResponseGer());
		    			map.put(p, tmp.get(0));
		    			wrsk.updateRespGerl(tmp.get(0));
		    		}
		    	}
		    	
		    	result.add(wrsk);
	    	}
	    	System.out.println("> 5000?? "+personmodel.isExportExcel()+" - "+result.size());
	    	
	    	
	    	if(personmodel.isExportExcel()){
	    		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
	    		String current_time_str = time_formatter.format(System.currentTimeMillis());
	    		int shag = 65_000, end = result.size();
	    		Float koef = (float) end/shag;
	    		
	    		for(int i = 0;; i = i+65_000){
	    			if(i > end && Integer.valueOf(koef.toString().split("\\.")[1]) > 0){ // koef - наличие дробной части говорит о использовании не 65_000 сктрок в экселе. (то есть используем по end )
						writeListToFile("org_" + personmodel.getCurrentUser()+"_"+current_time_str+".xlsx",result.subList(i-65000, end),sb.toString());
						break;
					}
	    			if((i+65000) <= end) writeListToFile("org_" + personmodel.getCurrentUser()+"_"+current_time_str+".xlsx",result.subList(i, i+65_000),sb.toString());
	    			
	    			 if(i > end) break; // если текущая итерация цикла больше строки окончания экселя
	    		}
	    		
	    				
	    				
	    		
	    	}
	    	
	    	if(result.size() > 5000) { System.out.println("> 5000 return "+result.size()); return result.subList(0, 5001);}
	    	
	      return result;
	}
	
	public Collection<?> exportToExcel(ArrayList<WrapRespSerarchKeys> wrapRespSerarchKeys) throws Exception{
		
		SimpleDateFormat time_formatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
		String current_time_str = time_formatter.format(System.currentTimeMillis());
		
		writeListToFile("org_" + wrapRespSerarchKeys.get(0).getCurrentUser()+"_"+current_time_str+".xlsx", wrapRespSerarchKeys,"запрос отсутствует"); 
		
		return new ArrayList<>();
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
/*        StoredProcedureQuery storedProcedure =  em_dream2.createStoredProcedureQuery("sys.connect_mis.disp_fiod");
        
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
        ls.add(rGer);*/
        
        List<ResponseGer> ls =   (List<ResponseGer>) xa_Dream2Dao.getInfoG(personmodel);
        
        /*
           *******************************************************
        */
        List<?>  obj = Stream.concat(results.stream(), ls.stream()).collect(Collectors.toList());

		return (Collection<?>)obj;
	}
	
	public  void writeListToFile(String fileName, List<WrapRespSerarchKeys> wrapRespSerarchKeys, String querytext) throws Exception{
		
		SXSSFWorkbook workbook = null;
		
		if(fileName.endsWith("xlsx")){
			workbook = new SXSSFWorkbook(-1);
		}else{
			throw new Exception("invalid file name, should be xls or xlsx");
		}
		
		 SXSSFSheet  sheet = (SXSSFSheet) workbook.createSheet("Данные");
		
		 SXSSFSheet  sheet_query = (SXSSFSheet)  workbook.createSheet("Запрос");
		Row row_query = sheet_query.createRow(0);
		row_query.createCell(0).setCellValue(querytext);
		
		
		{
			Row row_title = sheet.createRow(0);
			Cell cell0 = row_title.createCell(0);
			cell0.setCellValue("Фамилия");
			Cell cell1 = row_title.createCell(1);
			cell1.setCellValue("Имя");
			Cell cell2 = row_title.createCell(2);
			cell2.setCellValue("Отчество");
			Cell cell3 = row_title.createCell(3);
			cell3.setCellValue("Дата рождения");
			Cell cell4 = row_title.createCell(4);
			cell4.setCellValue("Полных лет");
			Cell cell5 = row_title.createCell(5);
			cell5.setCellValue("Тел РС ЕРЗ");
			Cell cell6 = row_title.createCell(6);
			cell6.setCellValue("Тел ГЭР");
			Cell cell7 = row_title.createCell(7);
			cell7.setCellValue("Адрес ГЭР");
			Cell cell8 = row_title.createCell(8);
			cell8.setCellValue("Адрес РС ЕРЗ");
			Cell cell9 = row_title.createCell(9);
			cell9.setCellValue("№ МО прикрепления");
			
			Cell cell11 = row_title.createCell(11);
			cell11.setCellValue("GER_Pm_result");
			Cell cell12 = row_title.createCell(12);
			cell12.setCellValue("GER_Tel");
			Cell cell13 = row_title.createCell(13);
			cell13.setCellValue("GER_Adress");
			Cell cell14 = row_title.createCell(14);
			cell14.setCellValue("RS ERZ LINK_SMO");
			Cell cell15 = row_title.createCell(15);
			
				cell15.setCellValue("PMNo2017_KV");
				Cell cell16 = row_title.createCell(16);
				cell16.setCellValue("PMNo2017_DATE_BEGIN");
				Cell cell17 = row_title.createCell(17);
				cell17.setCellValue("PMNo2017_DATE_END");
				Cell cell18 = row_title.createCell(18);
				cell18.setCellValue("PMNo2017_GOD");
			
			Cell cell19 = row_title.createCell(19);
			cell19.setCellValue("Count_stages_PmI");
			Cell cell20 = row_title.createCell(20);
			cell20.setCellValue("PmA_dateInfo");
			Cell cell21 = row_title.createCell(21);
			cell21.setCellValue("PmA_smo");
			Cell cell22 = row_title.createCell(22);
			cell22.setCellValue("PmA_typeInfo");
			
		}
		Iterator<WrapRespSerarchKeys> iterator = wrapRespSerarchKeys.iterator();
		int rowIndex = 1;
		WrapRespSerarchKeys Keys = null;
		Row row = null;
		while(iterator.hasNext()){
			Keys = iterator.next();
			row = sheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(Keys.getPersonSurname());
			row.createCell(1).setCellValue(Keys.getPersonKindfirstname());
			row.createCell(2).setCellValue(Keys.getPersonKindlastname());
			row.createCell(3).setCellValue(Keys.getPersonBirthday());
			row.createCell(4).setCellValue(Keys.getYears());
			row.createCell(5).setCellValue(Keys.getTele2()+"; "+Keys.getTeleDom() +"; "+Keys.getTeleWork());
			row.createCell(6).setCellValue(Keys.getRespGerl().get(0).getTel());
			row.createCell(7).setCellValue(Keys.getRespGerl().get(0).getAdress());
			row.createCell(8).setCellValue("адрес рс");
			row.createCell(9).setCellValue(Keys.getPersonEstablishmentambul());
			
			row.createCell(11).setCellValue(Keys.getRespGerl().get(0).getPm_result());
			row.createCell(12).setCellValue(Keys.getRespGerl().get(0).getTel());
			row.createCell(13).setCellValue(Keys.getRespGerl().get(0).getAdress());
			row.createCell(14).setCellValue(Keys.getPersonLinksmoestablishmentid());
			
			if(Keys.getRespPlan().size() > 0 ){
				row.createCell(15).setCellValue(Keys.getRespPlan().get(0).getKv());
				row.createCell(16).setCellValue(Keys.getRespPlan().get(0).getDate_begin());
				row.createCell(17).setCellValue(Keys.getRespPlan().get(0).getDate_end());
				row.createCell(18).setCellValue(Keys.getRespPlan().get(0).getGod());
			}
				row.createCell(19).setCellValue(Keys.getCount_pmIstages());
				row.createCell(20).setCellValue(Keys.getPma_d_info());
				row.createCell(21).setCellValue(Keys.getPma_smo());
				row.createCell(22).setCellValue(Keys.getPma_type_info());
				
				// manually control how rows are flushed to disk 
		           if(rowIndex % 100 == 0) {
		                ((SXSSFSheet)sheet).flushRows(100); // retain 100 last rows and flush all others
		           }
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
		
		// dispose of temporary files backing this workbook on disk
		workbook.dispose();
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
		public Collection<?> getListUploadedFiles(Integer id){
			String directoryServer = System.getProperty("jboss.home.dir");
			String directoryDestination = "";
			if(id == 777) directoryDestination = "\\content\\upload\\777\\process";
			if(id == 1)	directoryDestination = "\\content\\upload\\1\\process";
			if(id == 2)	directoryDestination = "\\content\\upload\\2\\process";
			if(id == 4)	directoryDestination = "\\content\\upload\\4\\process";
			
			directoryDestination = directoryServer+directoryDestination;
			
			File path = new File(directoryDestination);
			File[] files = path.listFiles();
			Arrays.sort(files, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
			List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
			for(int i=0;i < files.length;i++){
				ls.add(new ListExcelFiles(files[i].getName(),directoryDestination+File.separator+files[i].getName()));
			}
		
		return ls;
	}
		//TODO разобраться как это работает.
	@Override
	public Integer countStrProphylactic() {
		int csp = count;
		count = 0;
		return csp;
	}
}
