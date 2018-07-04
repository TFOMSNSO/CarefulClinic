package com.careful.clinic.rs.dao.sp3.inform.d.reestr;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.careful.clinic.model.ListExcelFiles;
import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.WrapSp3;

@Stateless
public class D_reestrImpDAO  implements D_reestr{

	@PersistenceContext(unitName="NONXAMUR2018")
    private EntityManager non_mur_collect2018;
	
	@Override
	public List<?> getListDReestr(Integer id) {
		int current_year = Calendar.getInstance().get(Calendar.YEAR);

		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\sp3\\info_d_reestr\\4";
		
		directoryDestination = directoryServer+directoryDestination;
		
		File path = new File(directoryDestination);
		String ob[] = path.list();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for(int i=0;i < ob.length;i++){
			ls.add(new ListExcelFiles(ob[i],directoryDestination+File.separator+ob[i]));
			
		}
		
		return ls;
	}

	@Override
	public List<WrapSp3> getResalt_D_reestrCollect2018(String date1,String date2,String user, int firrstResult) {

		
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		// 
		//String sb = "select  zz.id_assent,zz.person_linksmoestablishmentid,zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id"
String sb = "select tmp_all.* from ( "		
		+"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "
                                                           +"from ( "
//                                                           +"select n.id_assent,pe.person_linksmoestablishmentid,p.pr_d_n,p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id"
															+"select p.mes, p.pr_d_n, p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "
                                                           +"from "
                                                           +"demand d, pat p " 
//                                                           +"left join person@dome_dev pe on pe.person_surname = p.fam and pe.person_kindfirstname = p.im and pe.person_kindlastname = p.ot and pe.person_birthday = p.dr"
//                                                           +"left join pm_assent@link_ofoms n on n.fam = p.fam and n.im = p.im and n.ot = p.ot and n.dr = p.dr "
                                                           +"where "
                                                            +"d.id_demand = p.demand_id " 
                                                            +"and  p.caretype = 30 "   
                                                        //    and   p.REZOBR in (22) " 
                                                            +"and  d.period between ' 201711' and '201812' "
                                                   		    + g  
                                                            +" and  p.dat_end >= '01.01.2018' "

                                                           +"and (((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1  between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99'))) "
                                                            +"and  p.mes between '401048' and '401080' "
                                                            +"and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "
                                                            +"and exists (select 1 from pat pp where pp.caretype = 30  and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr) "
                                                            +") zz "
                                                            +"where zz.mes='401072' and zz.pr_d_n in (1, 2) "
                               +"union all "
                               
                               +"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "
                                                           +"from "
                                                           +"( "
//                                                           +"select n.id_assent,pe.person_linksmoestablishmentid,p.pr_d_n,p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "
															+"select p.mes,  p.pr_d_n, p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "
                                                           +"from "
                                                           +"demand d, pat p "
//                                                           +"left join person@dome_dev pe on pe.person_surname = p.fam and pe.person_kindfirstname = p.im and pe.person_kindlastname = p.ot and pe.person_birthday = p.dr "
//                                                           +"left join pm_assent@link_ofoms n on n.fam = p.fam and n.im = p.im and n.ot = p.ot and n.dr = p.dr "
                                                           +"where "
                                                           +"d.id_demand = p.demand_id "
                                                           +"and  p.caretype = 30 "
                                                           //and   p.REZOBR in (22) "
                                                           +"and  d.period between '201711' and '201812' "
                                                  		   + g  
                                                           +" and  p.dat_end >= '01.01.2018' "
                                                           +"and (((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99'))) "
                                                           +"and  p.mes between '401048' and '401080' "
                                                           +"and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "
                                                           +"and exists (select 1 from pat pp where pp.caretype = 30  and mes in(401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) "
                                                           +") zz "
                                                            +"where zz.mes between '401048' and '401071' and zz.pr_d_n  in (1, 2) "
                                                            
                                 +"union all "
                               
                               +"select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id "
                                                           +"from ( "
//                                                           +"select n.id_assent,pe.person_linksmoestablishmentid,p.pr_d_n,p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "
															+"select p.mes,   p.pr_d_n,  p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id "
                                                           +"from "
                                                           +"demand d, pat p "
//                                                           +"left join person@dome_dev pe on pe.person_surname = p.fam and pe.person_kindfirstname = p.im and pe.person_kindlastname = p.ot and pe.person_birthday = p.dr "
//                                                           +"left join pm_assent@link_ofoms n on n.fam = p.fam and n.im = p.im and n.ot = p.ot and n.dr = p.dr "
                                                           +"where "
                                                           +"d.id_demand = p.demand_id "
                                                           +"and  p.caretype = 30 "
                                                           //and   p.REZOBR in (22) "
                                                           +"and  d.period between '201711' and '201812' "
                                                  		 + g  
                                                           +" and  p.dat_end >= '01.01.2018' "
                                                            
                                                           +"and (((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99'))) "
                                                           +"and  p.mes between '401048' and '401080' "
                                                           +"and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) "
                                                           +"and not exists (select 1 from pat pp where pp.caretype = 30  and mes  in (401072,401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) "
                                                           +") zz "
                                                            +"where zz.mes between '401048' and '401071' and zz.pr_d_n  in (1, 2) and zz.rezobr in (16,22,23) "

+") tmp_all "
+ "where not exists "
+ " (select 1 from pat p2 where p2.fio = tmp_all.fio and p2.dr = tmp_all.dr and p2.dat_end > tmp_all.dat_end )";

		
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
			String _tel = (String) record[15];
			Long _id = ((BigDecimal)  record[16]).longValue();
		
			ls.add(new WrapSp3(_0, _1, _2, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _tel, _id));
		
	});
		
		res=null;
	    return ls;
	
	}

	@Override
	public List<Sp3RateMo> getResalt_D_reestrRateMo(String date1, String date2, String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getListFiles(Integer id) {
		
		String directoryServer = System.getProperty("jboss.home.dir");
		String directoryDestination = "";
		if(id == 777) directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\777";
		if(id == 1)	directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\1";
		if(id == 2)	directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\2";
		if(id == 4)	directoryDestination = "\\content\\report\\sp3\\d_reestr_report\\4";
		
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

}
