package com.careful.clinic.dao.sp3.expertise;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.transform.Transformers;

import com.careful.clinic.model.ListExcelFiles;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.Wrap3a_b_Expertise;

@Stateless
public class Sp3ExpertiseDaoBean implements ISp3ExpertiseDao {
	
	@PersistenceContext(unitName="OracleMUR2017")
    private EntityManager mur_collect2017;
	
	@PersistenceContext(unitName="OracleMUR2018")
    private EntityManager mur_collect2018;
	
	@PersistenceContext(unitName="NONXAMUR2018")
    private EntityManager non_mur_collect2018;
	
	
	
	 private SimpleDateFormat sdf = null;
	 
	 @Override
	 public List<Wrap3a_b_Expertise> getResalt3b_expertise(String date1,String date2,String user, int firrstResult) {
		 String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		 String sb = "select "
					+ " p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, f_mkb_usl_z(p.id) as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id"
					+ " from demand d, pat p, pat_usl pu where "
					+ "d.id_demand = p.demand_id"
					+ " and "
					+ " pu.id = p.id "
					+ " and "
					+ " p.caretype = 30 "
					+" and "
					+ "  p.REZOBR in (21, 23, 14, 15)"
					+ " and "
					+ " d.period between ' "+date1+" ' and ' "+date2+" '"
					+ "  and "
					+ " p.dat_end >= '01.01.2018' "
					+ g
					+" and "
					+"("
					+"(pu.ds between 'I20' and 'I25.99')"
					+" or "
					+"(pu.ds between 'I60' and 'I69.99')" 
					+" or "
					+"(substr(pu.ds,0,1) = 'C')" 
					+" or "
					+"(pu.ds between 'E10' and 'E13.99')" 
					+" or "
					+"(pu.ds between 'J44' and 'J47.99')" 
					 +")";
		 
		// TODO сделать выбор базы на сайте
			Query q = non_mur_collect2018.createNativeQuery(sb);
			
			q.setFirstResult(firrstResult);
			q.setMaxResults(60_000);
			List<Object[]> res = q.getResultList();
			// for processed data
			List<Wrap3a_b_Expertise> ls = new ArrayList<Wrap3a_b_Expertise>(res.size());
			
			res.stream().forEach((record) -> {
				
				String _0 = (String) record[0];
				Date _1 = (Date) record[1];
				Long _2 = ((BigDecimal) record[2]).longValue();
				String _3 = (String) record[3];
				String _4 = (String) record[4];
				String _5 = (String)  record[5];
				String _6 = (String) record[6];
				Date _7 = (Date) record[7];
				Date _8 = (Date) record[8];
				Long _9 = ((BigDecimal)  record[9]).longValue();
				Long _10 = ((BigDecimal) record[10]).longValue();
				Long _11 = ((BigDecimal) record[11]).longValue();
				Date _12 = (Date) record[12];
				String _13 = (String) record[13];
				String _14 = (String) record[14];
				String _tel = (String) record[15];
				Long _id = ((BigDecimal)  record[16]).longValue();
			
				ls.add(new Wrap3a_b_Expertise(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _tel, _id));
			
		});
			
			res=null;
		    return ls;
		 
	 }
	
	 @Override
	public List<Wrap3a_b_Expertise> getResalt3a_expertise(String date1,String date2,String user, int firrstResult) throws Exception{
		
		//TODO полных лет
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select "
				+ " p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, f_mkb_usl(p.id) as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id"
				+ " from demand d, pat p, pat_usl pu where "
				+ "d.id_demand = p.demand_id"
				+ " and "
				+ " pu.id = p.id "
				+ " and "
				+ " p.caretype = 30 "
				+" and "
				+ "  p.REZOBR in (20, 22)"
				+ " and "
				+ " d.period between ' "+date1+" ' and ' "+date2+" '"
				+ "  and "
				+ " p.dat_end >= '01.01.2018' "
				+ g
				+" and "
				+"("
				+"(pu.ds not between 'I20' and 'I25.99')"
				+"and"
				+"(pu.ds not between 'I60' and 'I69.99')" 
				+"and"
				+"(substr(pu.ds,0,1) <> 'C')" 
				+"and"
				+"(pu.ds not between 'E10' and 'E13.99')" 
				+"and"
				+"(pu.ds not between 'J44' and 'J47.99')" 
				 +")";
				//+ " p.fam='"+personmodel.getSurname()+"' and p.im='"+personmodel.getFirstname()+"' and p.ot='"+personmodel.getLastname()+"' and p.dr='"+personmodel.getBithday()+"' ";
		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);
		
		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<Wrap3a_b_Expertise> ls = new ArrayList<Wrap3a_b_Expertise>(res.size());
		
		res.stream().forEach((record) -> {
			
			String _0 = (String) record[0];
			Date _1 = (Date) record[1];
			Long _2 = ((BigDecimal) record[2]).longValue();
			String _3 = (String) record[3];
			String _4 = (String) record[4];
			String _5 = (String)  record[5];
			String _6 = (String) record[6];
			Date _7 = (Date) record[7];
			Date _8 = (Date) record[8];
			Long _9 = ((BigDecimal)  record[9]).longValue();
			Long _10 = ((BigDecimal) record[10]).longValue();
			Long _11 = ((BigDecimal) record[11]).longValue();
			Date _12 = (Date) record[12];
			String _13 = (String) record[13];
			String _14 = (String) record[14];
			String _tel = (String) record[15];
			Long _id = ((BigDecimal)  record[16]).longValue();
		
			ls.add(new Wrap3a_b_Expertise(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _tel, _id));
		
	});
		
		res=null;
	    return ls;
	}

	@Override
	public List<?> getListExperiseReport(Integer id) {

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\sp3\\expert\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\sp3\\expert\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\sp3\\expert\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\sp3\\expert\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		//TODO создать компонент utils в ejb и сделать метод сортировки по дата изменения файла 
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
			
		}
		
		return ls;
	}

	@Override
	public List<?> getResalt3a3b_expertise(String date1,String date2,String user, int firrstResult) {
		//TODO полных лет
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select "
				+ " p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, f_mkb_usl(p.id) as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id"
				+ " from demand d, pat p, pat_usl pu where "
				+ "d.id_demand = p.demand_id"
				+ " and "
				+ " pu.id = p.id "
				+ " and "
				+ " p.caretype = 30 "
				+" and "
				+ "  p.REZOBR in (20, 21, 22, 23) "
				+ " and "
				+ " (p.nazr <> 5 and p.nazr <> 7)  and p.pr_d_n <> 1"
				+ " and "
				+ " d.period between ' "+date1+" ' and ' "+date2+" '"
				+ "  and "
				+ " p.dat_end >= '01.01.2018' "
				+ g ;
				//+ " p.fam='"+personmodel.getSurname()+"' and p.im='"+personmodel.getFirstname()+"' and p.ot='"+personmodel.getLastname()+"' and p.dr='"+personmodel.getBithday()+"' ";
		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);
		
		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<Wrap3a_b_Expertise> ls = new ArrayList<Wrap3a_b_Expertise>(res.size());
		
		res.stream().forEach((record) -> {
			
			String _0 = (String) record[0];
			Date _1 = (Date) record[1];
			Long _2 = ((BigDecimal) record[2]).longValue();
			String _3 = (String) record[3];
			String _4 = (String) record[4];
			String _5 = (String)  record[5];
			String _6 = (String) record[6];
			Date _7 = (Date) record[7];
			Date _8 = (Date) record[8];
			Long _9 = ((BigDecimal)  record[9]).longValue();
			Long _10 = ((BigDecimal) record[10]).longValue();
			Long _11 = ((BigDecimal) record[11]).longValue();
			Date _12 = (Date) record[12];
			String _13 = (String) record[13];
			String _14 = (String) record[14];
			String _tel = (String) record[15];
			Long _id = ((BigDecimal)  record[16]).longValue();
		
			ls.add(new Wrap3a_b_Expertise(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _tel, _id));
		
	});
		
		res=null;
	    return ls;
	}

	
	
}
