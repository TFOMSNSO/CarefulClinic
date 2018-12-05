package com.careful.clinic.dao.sp3.expertise;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.transform.Transformers;

import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.ListExcelFiles;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.WrapSp3;

@Stateless
public class Sp3ExpertiseDaoBean implements ISp3ExpertiseDao {

	@PersistenceContext(unitName="OracleMUR2017")
	private EntityManager mur_collect2017;

	@PersistenceContext(unitName="OracleMUR2018")
	private EntityManager mur_collect2018;

	@PersistenceContext(unitName="NONXAMUR2018")
	private EntityManager non_mur_collect2018;




	@Override
	public List<WrapSp3> getResalt3b_expertise(String date1,String date2,String user, int firrstResult) {

		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (14,21) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" '  "+
				"and  p.dat_end >= '01.01.2018' "+
				g +
				" and ((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99') or p.ds1 is null)  "+
				"and  p.mes between '401048' and '401071' "+
				// пункт 6
				// если данные пролеченные были на втором этапе с месами 401079 401080 (то есть если на втором этапе была просто беседа то берем первый этап)
				"and (p.fio,p.dr)  in ( "+
				// оставляем только тех в второэтапников у которых первый этап по месам 1-го этапа (401048 - 401071) и проведена на втором этапе проф беседа по месам 401079 401080

				"select zz.fio,zz.dr "+
				"from "+
				"( "+
				"select p.fio,p.dr,p.mes "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
//		 							 "and  d.period between ' 201801 ' and ' 201802 ' "+
				"and  p.dat_end >= '01.01.2018' "+
				"and  p.mes between '401048' and '401080' "+
				"and exists (select 1 from pat pp where pp.caretype = 30 and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes in (401079,401080) "+
				") "
				+ " and "
				+ " p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				"union all "+
				// присоединям второэтапников по пункту  5 алгоритма
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (15,23) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "
				+ " and "
				+ "  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)  "+
				g+
				" and ((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99') or p.ds1 is null)  "+
				"and  p.mes between '401048' and '401080' "+
				"and exists (select 1 from pat pp where pp.caretype = 30  and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes='401072' "+
				"union all "+
				// пункт 7
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (15,23) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "
				+ " and "
				+ " p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				g+
				" and ((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99') or p.ds1 is null)  "+
				"and  p.mes between '401048' and '401080' "+
				"and not exists (select 1 from pat pp where pp.caretype = 30  and mes in(401072,401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "
				+ " where zz.mes between '401048' and '401071'  ";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<WrapSp3> ls = new ArrayList<WrapSp3>(res.size());

		res.stream().forEach((record) ->  {

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
			String _15 = (String) record[15];
			String _tel = (String) record[16];
			Long _id = ((BigDecimal)  record[17]).longValue();

			ls.add(new WrapSp3(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _15,  _tel, _id));

		});

		res=null;
		return ls;

	}

	@Override
	public List<WrapSp3> getResalt3a_expertise(String date1,String date2,String user, int firrstResult) throws Exception{





		//TODO полных лет
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;

		String sb ="select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (20,22) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" '  "+
				"and  p.dat_end >= '01.01.2018' "+
				g +
				" and ((substr(p.ds1,0,1) <> 'I')and(substr(p.ds1,0,1) <> 'C')and(p.ds1 not between 'E10' and 'E13.99')and(p.ds1 not between 'J44' and 'J47.99')) "+
				"and  p.mes between '401048' and '401071' "+
				// пункт 6
				// если данные пролеченные были на втором этапе с месами 401079 401080 (то есть если на втором этапе была просто беседа то берем первый этап)
				"and (p.fio,p.dr)  in ( "+
				// оставляем только тех в второэтапников у которых первый этап по месам 1-го этапа (401048 - 401071) и проведена на втором этапе проф беседа по месам 401079 401080

				"select zz.fio,zz.dr "+
				"from "+
				"( "+
				"select p.fio,p.dr,p.mes "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
//		 							 "and  d.period between ' 201801 ' and ' 201802 ' "+
				"and  p.dat_end >= '01.01.2018' "+
				"and  p.mes between '401048' and '401080' "+
				"and exists (select 1 from pat pp where pp.caretype = 30 and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes in (401079,401080) "+
				") "
				+ " and "
				+ " p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				"union all "+
				// присоединям второэтапников по пункту  5 алгоритма
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (22) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "+
				g+
				" and ((substr(p.ds1,0,1) <> 'I')and(substr(p.ds1,0,1) <> 'C')and(p.ds1 not between 'E10' and 'E13.99')and(p.ds1 not between 'J44' and 'J47.99')) "+
				"and  p.mes between '401048' and '401080' "
				+ " and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				"and exists (select 1 from pat pp where pp.caretype = 30  and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes='401072' "+
				"union all "+
				// пункт 7
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (22) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "+
				g+
				" and ((substr(p.ds1,0,1) <> 'I')and(substr(p.ds1,0,1) <> 'C')and(p.ds1 not between 'E10' and 'E13.99')and(p.ds1 not between 'J44' and 'J47.99')) "+
				"and  p.mes between '401048' and '401080' "
				+ " and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				"and not exists (select 1 from pat pp where pp.caretype = 30  and mes in(401072,401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "
				+ " where zz.mes between '401048' and '401071'  ";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<WrapSp3> ls = new ArrayList<WrapSp3>(res.size());

		res.stream().forEach((record) ->  {

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
			String _15 = (String) record[15];
			String _tel = (String) record[16];
			Long _id = ((BigDecimal)  record[17]).longValue();

			ls.add(new WrapSp3(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _15,  _tel, _id));

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
		File[] files = path.listFiles();
		Arrays.sort(files, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));

		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < files.length;i++){
			ls.add(new ListExcelFiles(files[i].getName(),directoryDestination+File.separator+files[i].getName()));
		}

		return ls;
	}

	@Override
	public List<?> getResalt3a3b_expertise(String date1,String date2,String user, int firrstResult) {

		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		// АЛГОРИТМ лижит в папке src\main\resources\reports\sp3\expertise
		String sb = "select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id"
		+" from"
				+" pat p left join gosp_eir_view@link_webofoms gosp on"
				+" p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)"
				+" and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)"
				+" and (gosp.d_np) >= '01.01.2018'"
				+" left join demand d on d.id_demand = p.demand_id"
				+" where gosp.fam is  null"
				+" and  p.caretype = 30"
				+" and   p.REZOBR in (20,21)"

				+" and"
				+" 	(((rezobr = 20)"
				+" 		and	("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 									or substr(ds1,1,3) between 'D00' and ' D09'"
				+" 	or substr(ds1,1,3) between 'E10' and 'E14'"
				+" 	or substr(ds1,1,3) between 'J43' and 'J47'"
				+"                )"
				+"               )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 21)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )              "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and (p.fio,p.dr)  in ("
				+" 			select fio, dr"
				+" 			from demand d, pat p"
				+" 			where d.id_demand = p.demand_id"
				+" 		and  p.caretype = 30"
				+" 			and  p.dat_end >= '01.01.2018'"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+" 		and  p.mes in (401079,401080)"
				+" )"
				+" and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				// 22,23 '401072' наличие 1 этапа
				+" union"
				+" select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 		from pat p left join gosp_eir_view@link_webofoms gosp on"
				+" p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)"
				+" and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)"
				+" and (gosp.d_np) >= '01.01.2018'"
				+" 	left join demand d on d.id_demand = p.demand_id"
				+" 	where gosp.fam is  null"
				+" 	and  p.caretype = 30"
				+" and   p.REZOBR in (22,23)"
				+" 	and"
				+" 		("
				+" 				("
				+" 						(rezobr = 22)"
				+" 				and"
				+" 						("
				+" 								substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"               )"
				+"              )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 23)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )                      "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  p.mes='401072'"
				+" 	and exists (select 1 from pat pp where pp.caretype = 30"
				+" 	and (pp.mes between '401048' and '401071' or pp.mes between '401081' and '401083')"
				+" 	and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                ) zz"

				// (22,23)  '401048' and '401071'  '401081' and '401083'  нет (401072,401079,401080)

				+" 			union"
				+" 			select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" 	from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 			from pat p left join gosp_eir_view@link_webofoms gosp on"
				+" p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)"
				+" and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)"
				+" and (gosp.d_np) >= '01.01.2018'"
				+" 	left join demand d on d.id_demand = p.demand_id"
				+" 	where gosp.fam is  null"
				+" 	and  p.caretype = 30"
				+" 	and  p.REZOBR in (22,23)"
				+" 	and"
				+" 			("
				+" 					("
				+" 							(rezobr = 22)"
				+" 					and"
				+" 							("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"           )"
				+"            )"
				+" or"
				+" ("
				+" 		(rezobr = 23)"
				+" 		and"
				+" 				("
				+" 						substr(ds1,1,3) between 'J41' and 'J42'"
				+" or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"              )                "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and not exists (select 1 from pat pp where pp.caretype = 30"
				+" 			and mes in (401072)"
				+" 			and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                  ) zz";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<WrapSp3> ls = new ArrayList<WrapSp3>(res.size());

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
			String _15 = (String) record[15];
			String _tel = (String) record[16];
			Long _id = ((BigDecimal)  record[17]).longValue();

			ls.add(new WrapSp3(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _15,  _tel, _id));

		});

		res=null;
		return ls;
	}

	@Override
	public List<Sp3RateMo> getResalt3b_expertiseRateMo(String date1, String date2, String user) {

		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select "
				+" tmp1.lp as code_mo , "
				+" tmp.name as name_mo, "
				+" tmp1.total, "
				+" tmp1.total_1, "
				+" tmp1.total_2, "
				+" tmp1.total_4 "
				+" from "
				+" (  select  z_tmp.lpu as lp,  count(*) total,  count ( case when z_tmp.smoid = 1 then 1 end ) total_1,  count ( case when z_tmp.smoid = 2 then 1 end ) total_2,  count ( case when z_tmp.smoid = 4 then 1 end ) total_4  from "
				+" ( "
				+"select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (14,21) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" '  "+
				"and  p.dat_end >= '01.01.2018' "
				+ " and "
				+ "  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)  "+
				g +
				" and ((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99') or p.ds1 is null)  "+
				"and  p.mes between '401048' and '401071' "+
				// пункт 6
				// если данные пролеченные были на втором этапе с месами 401079 401080 (то есть если на втором этапе была просто беседа то берем первый этап)
				"and (p.fio,p.dr)  in ( "+
				// оставляем только тех в второэтапников у которых первый этап по месам 1-го этапа (401048 - 401071) и проведена на втором этапе проф беседа по месам 401079 401080

				"select zz.fio,zz.dr "+
				"from "+
				"( "+
				"select p.fio,p.dr,p.mes "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
//						 							 "and  d.period between ' 201801 ' and ' 201802 ' "+
				"and  p.dat_end >= '01.01.2018' "+
				"and  p.mes between '401048' and '401080' "+
				"and exists (select 1 from pat pp where pp.caretype = 30 and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes in (401079,401080) "+
				") "+
				"union all "+
				// присоединям второэтапников по пункту  5 алгоритма
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (15,23) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "
				+ " and "
				+ "  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)  "+
				g+
				" and ((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99') or p.ds1 is null)  "+
				"and  p.mes between '401048' and '401080' "+
				"and exists (select 1 from pat pp where pp.caretype = 30  and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes='401072' "+
				"union all "+
				// пункт 7
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (15,23) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "
				+ " and "
				+ "  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)  "+
				g+
				" and ((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99') or p.ds1 is null)  "+
				"and  p.mes between '401048' and '401080' "+
				"and not exists (select 1 from pat pp where pp.caretype = 30  and mes in(401072,401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "
				+ " where zz.mes between '401048' and '401071'  "
				+" )     z_tmp "
				+" group  by z_tmp.lpu  "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc ";
		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		List<Object[]> res = q.getResultList();
		// for processed data
		List<Sp3RateMo> ls = new ArrayList<Sp3RateMo>(res.size());

		res.stream().forEach((record) -> {

			String _0 = (String) record[0];
			String _1 = (String) record[1];
			Long _2 = ((BigDecimal) record[2]).longValue();
			Long _3 = ((BigDecimal) record[3]).longValue();
			Long _4 = ((BigDecimal) record[4]).longValue();
			Long _5 = ((BigDecimal) record[5]).longValue();

			ls.add(new Sp3RateMo(_0, _1, _2, _3,_4,_5));

		});

		res=null;

		return ls;

	}

	@Override
	public List<Sp3RateMo> getResalt3a3b_expertiseRateMo(String date1, String date2, String user) {

		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select "
				+" tmp1.lp as code_mo , "
				+" tmp.name as name_mo, "
				+" tmp1.total, "
				+" tmp1.total_1, "
				+" tmp1.total_2, "
				+" tmp1.total_4 "
				+" from "
				+" (  select  z_tmp.lpu as lp,  count(*) total,  count ( case when z_tmp.smoid = 1 then 1 end ) total_1,  count ( case when z_tmp.smoid = 2 then 1 end ) total_2,  count ( case when z_tmp.smoid = 4 then 1 end ) total_4  from "
				+" ( "

				+"select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id"
				+" from"
				+" pat p left join gosp_eir_view@link_webofoms gosp on"
				+" p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)"
				+" and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)"
				+" and (gosp.d_np) >= '01.01.2018'"
				+" left join demand d on d.id_demand = p.demand_id"
				+" where gosp.fam is  null"
				+" and  p.caretype = 30"
				+" and   p.REZOBR in (20,21)"

				+" and"
				+" 	(((rezobr = 20)"
				+" 		and	("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 									or substr(ds1,1,3) between 'D00' and ' D09'"
				+" 	or substr(ds1,1,3) between 'E10' and 'E14'"
				+" 	or substr(ds1,1,3) between 'J43' and 'J47'"
				+"                )"
				+"               )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 21)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )              "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and (p.fio,p.dr)  in ("
				+" 			select fio, dr"
				+" 			from demand d, pat p"
				+" 			where d.id_demand = p.demand_id"
				+" 		and  p.caretype = 30"
				+" 			and  p.dat_end >= '01.01.2018'"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+" 		and  p.mes in (401079,401080)"
				+" )"
				+" and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				// 22,23 '401072' наличие 1 этапа
				+" union"
				+" select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 		from pat p left join gosp_eir_view@link_webofoms gosp on"
				+" p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)"
				+" and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)"
				+" and (gosp.d_np) >= '01.01.2018'"
				+" 	left join demand d on d.id_demand = p.demand_id"
				+" 	where gosp.fam is  null"
				+" 	and  p.caretype = 30"
				+" and   p.REZOBR in (22,23)"
				+" 	and"
				+" 		("
				+" 				("
				+" 						(rezobr = 22)"
				+" 				and"
				+" 						("
				+" 								substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"               )"
				+"              )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 23)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )                      "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  p.mes='401072'"
				+" 	and exists (select 1 from pat pp where pp.caretype = 30"
				+" 	and (pp.mes between '401048' and '401071' or pp.mes between '401081' and '401083')"
				+" 	and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                ) zz"

				// (22,23)  '401048' and '401071'  '401081' and '401083'  нет (401072,401079,401080)

				+" 			union"
				+" 			select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" 	from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 			from pat p left join gosp_eir_view@link_webofoms gosp on"
				+" p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)"
				+" and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)"
				+" and (gosp.d_np) >= '01.01.2018'"
				+" 	left join demand d on d.id_demand = p.demand_id"
				+" 	where gosp.fam is  null"
				+" 	and  p.caretype = 30"
				+" 	and  p.REZOBR in (22,23)"
				+" 	and"
				+" 			("
				+" 					("
				+" 							(rezobr = 22)"
				+" 					and"
				+" 							("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"           )"
				+"            )"
				+" or"
				+" ("
				+" 		(rezobr = 23)"
				+" 		and"
				+" 				("
				+" 						substr(ds1,1,3) between 'J41' and 'J42'"
				+" or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"              )                "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and not exists (select 1 from pat pp where pp.caretype = 30"
				+" 			and mes in (401072)"
				+" 			and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                  ) zz"

				+" )     z_tmp "
				+" group  by z_tmp.lpu  "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc ";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		List<Object[]> res = q.getResultList();
		// for processed data
		List<Sp3RateMo> ls = new ArrayList<Sp3RateMo>(res.size());

		res.stream().forEach((record) -> {

			String _0 = (String) record[0];
			String _1 = (String) record[1];
			Long _2 = ((BigDecimal) record[2]).longValue();
			Long _3 = ((BigDecimal) record[3]).longValue();
			Long _4 = ((BigDecimal) record[4]).longValue();
			Long _5 = ((BigDecimal) record[5]).longValue();

			ls.add(new Sp3RateMo(_0, _1, _2, _3,_4,_5));

		});

		res=null;

		return ls;


	}

	@Override
	public List<Sp3RateMo> getResalt3a_expertiseRateMo(String date1, String date2, String user) {

		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select "
				+" tmp1.lp as code_mo , "
				+" tmp.name as name_mo, "
				+" tmp1.total, "
				+" tmp1.total_1, "
				+" tmp1.total_2, "
				+" tmp1.total_4 "
				+" from "
				+" (  select  z_tmp.lpu as lp,  count(*) total,  count ( case when z_tmp.smoid = 1 then 1 end ) total_1,  count ( case when z_tmp.smoid = 2 then 1 end ) total_2,  count ( case when z_tmp.smoid = 4 then 1 end ) total_4  from "
				+" ( "
				+" select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (20,22) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" '  "+
				"and  p.dat_end >= '01.01.2018' "+
				g +
				" and ((substr(p.ds1,0,1) <> 'I')and(substr(p.ds1,0,1) <> 'C')and(p.ds1 not between 'E10' and 'E13.99')and(p.ds1 not between 'J44' and 'J47.99')) "+
				"and  p.mes between '401048' and '401071' "+
				// пункт 6
				// если данные пролеченные были на втором этапе с месами 401079 401080 (то есть если на втором этапе была просто беседа то берем первый этап)
				"and (p.fio,p.dr)  in ( "+
				// оставляем только тех в второэтапников у которых первый этап по месам 1-го этапа (401048 - 401071) и проведена на втором этапе проф беседа по месам 401079 401080

				"select zz.fio,zz.dr "+
				"from "+
				"( "+
				"select p.fio,p.dr,p.mes "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
//		 							 "and  d.period between ' 201801 ' and ' 201802 ' "+  
				"and  p.dat_end >= '01.01.2018' "+
				"and  p.mes between '401048' and '401080' "+
				"and exists (select 1 from pat pp where pp.caretype = 30 and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes in (401079,401080) "+
				")"
				+ " and "
				+ " p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				"union all "+
				// присоединям второэтапников по пункту  5 алгоритма
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (22) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "
				+ " and "
				+ " p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				g+
				" and ((substr(p.ds1,0,1) <> 'I')and(substr(p.ds1,0,1) <> 'C')and(p.ds1 not between 'E10' and 'E13.99')and(p.ds1 not between 'J44' and 'J47.99')) "+
				"and  p.mes between '401048' and '401080' "+
				"and exists (select 1 from pat pp where pp.caretype = 30  and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "+
				"where zz.mes='401072' "+
				"union all "+
				// пункт 7
				"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "+
				"from "+
				"( "+
				"select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "+
				"from "+
				"demand d, pat p "+
				"where "+
				"d.id_demand = p.demand_id "+
				"and  p.caretype = 30 "+
				"and   p.REZOBR in (22) "+
				"and  d.period between ' "+date1+" ' and ' "+date2+" ' "+
				"and  p.dat_end >= '01.01.2018' "
				+ " and "
				+ "  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "+
				g+
				" and ((substr(p.ds1,0,1) <> 'I')and(substr(p.ds1,0,1) <> 'C')and(p.ds1 not between 'E10' and 'E13.99')and(p.ds1 not between 'J44' and 'J47.99')) "+
				"and  p.mes between '401048' and '401080' "+
				"and not exists (select 1 from pat pp where pp.caretype = 30  and mes in(401072,401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) "+
				") zz "
				+ " where zz.mes between '401048' and '401071'  "


				+" )     z_tmp "
				+" group  by z_tmp.lpu "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		List<Object[]> res = q.getResultList();
		// for processed data
		List<Sp3RateMo> ls = new ArrayList<Sp3RateMo>(res.size());

		res.stream().forEach((record) -> {

			String _0 = (String) record[0];
			String _1 = (String) record[1];
			Long _2 = ((BigDecimal) record[2]).longValue();
			Long _3 = ((BigDecimal) record[3]).longValue();
			Long _4 = ((BigDecimal) record[4]).longValue();
			Long _5 = ((BigDecimal) record[5]).longValue();

			ls.add(new Sp3RateMo(_0, _1, _2, _3,_4,_5));

		});

		res=null;

		return ls;
	}

	@Override
	public List<?> getResalt3a3b_expertise_noNazrNoGosp(String date1, String date2, String user, int firrstResult) {
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		// АЛГОРИТМ лижит в папке src\main\resources\reports\sp3\expertise
		String sb = "select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id"
				+" from"
				+" pat p left join demand d on d.id_demand = p.demand_id"
				+" where p.caretype = 30"
				+" and   p.REZOBR in (20,21)"
				+" and"
				+" 	(((rezobr = 20)"
				+" 		and	("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 									or substr(ds1,1,3) between 'D00' and ' D09'"
				+" 	or substr(ds1,1,3) between 'E10' and 'E14'"
				+" 	or substr(ds1,1,3) between 'J43' and 'J47'"
				+"                )"
				+"               )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 21)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )              "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and (p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and (p.fio,p.dr)  in ("
				+" 			select fio, dr"
				+" 			from demand d, pat p"
				+" 			where d.id_demand = p.demand_id"
				+" 		and  p.caretype = 30"
				+" 			and  p.dat_end >= '01.01.2018'"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+" 		and  p.mes in (401079,401080)"
				+" )"
				+" and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				// 22,23 '401072' наличие 1 этапа
				+" union"
				+" select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 		from pat p left join demand d on d.id_demand = p.demand_id"
				+" 	where p.caretype = 30"
				+" and   p.REZOBR in (22,23)"
				+" 	and"
				+" 		("
				+" 				("
				+" 						(rezobr = 22)"
				+" 				and"
				+" 						("
				+" 								substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"               )"
				+"              )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 23)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )                      "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  p.mes='401072'"
				+" 	and exists (select 1 from pat pp where pp.caretype = 30"
				+" 	and (pp.mes between '401048' and '401071' or pp.mes between '401081' and '401083')"
				+" 	and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                ) zz"

				// (22,23)  '401048' and '401071'  '401081' and '401083'  нет (401072,401079,401080)

				+" 			union"
				+" 			select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" 	from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 			from pat p left join demand d on d.id_demand = p.demand_id"
				+" 	where p.caretype = 30"
				+" 	and  p.REZOBR in (22,23)"
				+" 	and"
				+" 			("
				+" 					("
				+" 							(rezobr = 22)"
				+" 					and"
				+" 							("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"           )"
				+"            )"
				+" or"
				+" ("
				+" 		(rezobr = 23)"
				+" 		and"
				+" 				("
				+" 						substr(ds1,1,3) between 'J41' and 'J42'"
				+" or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"              )                "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and not exists (select 1 from pat pp where pp.caretype = 30"
				+" 			and mes in (401072)"
				+" 			and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                  ) zz";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<WrapSp3> ls = new ArrayList<WrapSp3>(res.size());

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
			String _15 = (String) record[15];
			String _tel = (String) record[16];
			Long _id = ((BigDecimal)  record[17]).longValue();

			ls.add(new WrapSp3(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _15,  _tel, _id));

		});

		res=null;
		return ls;
	}

	@Override
	public List<Sp3RateMo> getResalt3a3b_expertiseRateMo_noNazrNoGosp(String date1, String date2, String user) {
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select "
				+" tmp1.lp as code_mo , "
				+" tmp.name as name_mo, "
				+" tmp1.total, "
				+" tmp1.total_1, "
				+" tmp1.total_2, "
				+" tmp1.total_4 "
				+" from "
				+" (  select  z_tmp.lpu as lp,  count(*) total,  count ( case when z_tmp.smoid = 1 then 1 end ) total_1,  count ( case when z_tmp.smoid = 2 then 1 end ) total_2,  count ( case when z_tmp.smoid = 4 then 1 end ) total_4  from "
				+" ( "

				+"select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id"
				+" from"
				+" pat p left join demand d on d.id_demand = p.demand_id"
				+" where p.caretype = 30"
				+" and   p.REZOBR in (20,21)"
				+" and"
				+" 	(((rezobr = 20)"
				+" 		and	("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 									or substr(ds1,1,3) between 'D00' and ' D09'"
				+" 	or substr(ds1,1,3) between 'E10' and 'E14'"
				+" 	or substr(ds1,1,3) between 'J43' and 'J47'"
				+"                )"
				+"               )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 21)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )              "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and (p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and (p.fio,p.dr)  in ("
				+" 			select fio, dr"
				+" 			from demand d, pat p"
				+" 			where d.id_demand = p.demand_id"
				+" 		and  p.caretype = 30"
				+" 			and  p.dat_end >= '01.01.2018'"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+" 		and  p.mes in (401079,401080)"
				+" )"
				+" and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				// 22,23 '401072' наличие 1 этапа
				+" union"
				+" select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 		from pat p left join demand d on d.id_demand = p.demand_id"
				+" 	where p.caretype = 30"
				+" and   p.REZOBR in (22,23)"
				+" 	and"
				+" 		("
				+" 				("
				+" 						(rezobr = 22)"
				+" 				and"
				+" 						("
				+" 								substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"               )"
				+"              )"
				+" 	or"
				+" 			("
				+" 					(rezobr = 23)"
				+" 					and"
				+" 							("
				+" 									substr(ds1,1,3) between 'J41' and 'J42'"
				+" 	or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"               )                      "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  p.mes='401072'"
				+" 	and exists (select 1 from pat pp where pp.caretype = 30"
				+" 	and (pp.mes between '401048' and '401071' or pp.mes between '401081' and '401083')"
				+" 	and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                ) zz"

				// (22,23)  '401048' and '401071'  '401081' and '401083'  нет (401072,401079,401080)

				+" 			union"
				+" 			select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id"
				+" 	from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id"
				+" 			from pat p left join demand d on d.id_demand = p.demand_id"
				+" 	where p.caretype = 30"
				+" 	and  p.REZOBR in (22,23)"
				+" 	and"
				+" 			("
				+" 					("
				+" 							(rezobr = 22)"
				+" 					and"
				+" 							("
				+" 							substr(ds1,1,1) in ('I', 'C')"
				+" 								or substr(ds1,1,3) between 'D00' and ' D09'"
				+" or substr(ds1,1,3) between 'E10' and 'E14'"
				+" or substr(ds1,1,3) between 'J43' and 'J47'"
				+"           )"
				+"            )"
				+" or"
				+" ("
				+" 		(rezobr = 23)"
				+" 		and"
				+" 				("
				+" 						substr(ds1,1,3) between 'J41' and 'J42'"
				+" or substr(ds1,1,3) between 'N17' and 'N18'"
				+" 	or substr(ds1,1,3) between 'M80' and 'M81'"
				+" 	or substr(ds1,1,3) between 'F00' and 'F03'"
				+" 	or substr(ds1,1,3) between 'T90' and 'T90'"
				+" 	or substr(ds1,1,3) between 'K20' and 'K21'"
				+" 	or substr(ds1,1,3) between 'K25' and 'K26'"
				+" 	or substr(ds1,1,3) between 'K57' and 'K57'"
				+" 	or ds1 in ('K22.2','K29.4','K29.5', 'K91.1','K62.1','K63.5','K62.0')"
				+"              )               "
				+"              )                "
				+"         )"
				+" 	and  d.period between  '"+date1+"' and '"+date2+"'"
				+g
				+" 	and  p.dat_end >= '01.01.2018'"
				+" 	and (p.pr_d_n = 0 or p.pr_d_n = 3)"
				+" 	and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')"
				+" 	and not exists (select 1 from pat pp where pp.caretype = 30"
				+" 			and mes in (401072)"
				+" 			and pp.fio = p.fio and pp.dr = p.dr)"
				+" 	and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)"
				+"                  ) zz"

				+" )     z_tmp "
				+" group  by z_tmp.lpu  "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc ";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		List<Object[]> res = q.getResultList();
		// for processed data
		List<Sp3RateMo> ls = new ArrayList<Sp3RateMo>(res.size());

		res.stream().forEach((record) -> {

			String _0 = (String) record[0];
			String _1 = (String) record[1];
			Long _2 = ((BigDecimal) record[2]).longValue();
			Long _3 = ((BigDecimal) record[3]).longValue();
			Long _4 = ((BigDecimal) record[4]).longValue();
			Long _5 = ((BigDecimal) record[5]).longValue();

			ls.add(new Sp3RateMo(_0, _1, _2, _3,_4,_5));

		});

		res=null;

		return ls;
	}
}
