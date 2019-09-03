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
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import com.careful.clinic.model.*;
import org.hibernate.transform.Transformers;

@Stateless
public class Sp3ExpertiseDaoBean implements ISp3ExpertiseDao {

	@PersistenceContext(unitName="OracleMUR2017")
	private EntityManager mur_collect2017;

	@PersistenceContext(unitName="OracleMUR2018")
	private EntityManager mur_collect2018;

	@PersistenceContext(unitName = "OracleMUR2019")
	private EntityManager mur_collect2019;

	@PersistenceContext(unitName="NONXAMUR2018")
	private EntityManager non_mur_collect2018;

	@PersistenceContext(unitName="NONXAMUR2019")
	private EntityManager non_mur_collect2019;

	@Override
	public List<?> getResult3b_new(String date1, String date2, String user) {

		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id \n" +
				"            from \n" +
				"            demand  d, pat  p \n" +
				"            where \n" +
				"            d.id_demand = p.demand_id \n" +
				"            and  p.caretype = 30 \n" +
				"            and   p.REZOBR in (14,21) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"            and  p.dat_end >= '01.01.2019'\n" + g +
				"            and \n" +
				"            (\n" +
				"              substr(ds1,1,1) in ('C','I')\n" +
				"              or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"              or ds1 between 'E11' and 'E11.99'\n" +
				"              or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"              , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"            )\n" +
				"            and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"            and (p.fio,p.dr)  in \n" +
				"            ( \n" +
				"              select zz.fio,zz.dr \n" +
				"              from\n" +
				"              ( \n" +
				"                select p.fio,p.dr,p.mes \n" +
				"                from \n" +
				"                demand  d, pat  p \n" +
				"                where \n" +
				"                d.id_demand = p.demand_id \n" +
				"                and  p.caretype = 30 \n" +
				"                and  p.dat_end >= '01.01.2019' \n" +
				"                and  \n" +
				"                (\n" +
				"                    p.mes between '401048' and '401080' \n" +
				"                    or p.mes between '401081' and '401129'\n" +
				"                )\n" +
				"                and exists \n" +
				"                (\n" +
				"                    select 1 from pat  pp where \n" +
				"                    pp.caretype = 30 \n" +
				"                    and mes between '401048' and '401071'\n" +
				"                    and pp.fio = p.fio \n" +
				"                    and pp.dr = p.dr\n" +
				"                )\n" +
				"              ) zz\n" +
				"              where zz.mes in ('401079','401080','401128', '401129')  \n" +
				"            ) \n" +
				"            and \n" +
				"            p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            union \n" +
				"            select zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30 \n" +
				"              and   p.REZOBR in (15,23) \n" +
				"              and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and \n" +
				"              p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and \n" +
				"              (\n" +
				"                  substr(ds1,1,1) in ('C','I')\n" +
				"                  or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                  or ds1 between 'E11' and 'E11.99'\n" +
				"                  or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                  , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              )\n" +
				"              and (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"              and exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp \n" +
				"                  where pp.caretype = 30  \n" +
				"                  and mes between '401048' and '401071'\n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              )\n" +
				"            ) zz \n" +
				"            where zz.mes in ('401072','401127')\n" +
				"            ------------\n" +
				"            union \n" +
				"            ------------\n" +
				"            select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30\n" +
				"              and   p.REZOBR in (15,23) \n" +
				"              and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and\n" +
				"              ( \n" +
				"                  substr(ds1,1,1) in ('C','I')\n" +
				"                  or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                  or ds1 between 'E11' and 'E11.99'\n" +
				"                  or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                  , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              )\n" +
				"              and  (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"              and not exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp where \n" +
				"                  pp.caretype = 30  \n" +
				"                  and mes in('401072','401079','401080','401127','401128','401129')  \n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              ) \n" +
				"            ) zz \n" +
				"            where (zz.mes between '401048' and '401071'  or zz.mes between '401081' and '401120')";


		Query q = non_mur_collect2019.createNativeQuery(sb);

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
	public List<WrapSp3> getResalt3b_expertise(String date1,String date2,String user, int firrstResult) {
		Long t1 = System.currentTimeMillis();
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		String sb = "select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id \n" +
				"            from \n" +
				"            demand  d, pat  p \n" +
				"            where \n" +
				"            d.id_demand = p.demand_id \n" +
				"            and  p.caretype = 30 \n" +
				"            and   p.REZOBR in (14,21) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"            and  p.dat_end >= '01.01.2019'\n" + g +
				"            and \n" +
				"            (\n" +
				"              substr(ds1,1,1) in ('C')\n" +
				"              or substr(ds1,1,1) in ('I')\n" +
				"              or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"              or ds1 between 'E11' and 'E11.99'\n" +
				"              or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"              , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"            )\n" +
				"            and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"            and (p.fio,p.dr)  in \n" +
				"            ( \n" +
				"              select zz.fio,zz.dr \n" +
				"              from\n" +
				"              ( \n" +
				"                select p.fio,p.dr,p.mes \n" +
				"                from \n" +
				"                demand  d, pat  p \n" +
				"                where \n" +
				"                d.id_demand = p.demand_id \n" +
				"                and  p.caretype = 30 \n" +
				"                and  p.dat_end >= '01.01.2019' \n" +
				"                and  \n" +
				"                (\n" +
				"                    p.mes between '401048' and '401080' \n" +
				"                    or p.mes between '401081' and '401129'\n" +
				"                )\n" +
				"                and exists \n" +
				"                (\n" +
				"                    select 1 from pat  pp where \n" +
				"                    pp.caretype = 30 \n" +
				"                    and mes between '401048' and '401071'\n" +
				"                    and pp.fio = p.fio \n" +
				"                    and pp.dr = p.dr\n" +
				"                )\n" +
				"              ) zz\n" +
				"              where zz.mes in ('401079','401080','401128', '401129')  \n" +
				"            ) \n" +
				"            and \n" +
				"            p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            ---------------\n" +
				"            union \n" +
				"            ---------------\n" +
				"            select zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30 \n" +
				"              and   p.REZOBR in (15,23) \n" +
				"              and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and \n" +
				"              p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and \n" +
				"              (\n" +
				"                  substr(ds1,1,1) in ('C')\n" +
				"                  or substr(ds1,1,1) in ('I')\n" +
				"                  or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                  or ds1 between 'E11' and 'E11.99'\n" +
				"                  or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                  , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              )\n" +
				"              and (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"              and exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp \n" +
				"                  where pp.caretype = 30  \n" +
				"                  and mes between '401048' and '401071'\n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              )\n" +
				"            ) zz \n" +
				"            where zz.mes in ('401072','401127')\n" +
				"            ------------\n" +
				"            union \n" +
				"            ------------\n" +
				"            select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30\n" +
				"              and   p.REZOBR in (15,23) \n" +
				"              and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and\n" +
				"              ( \n" +
				"                  substr(ds1,1,1) in ('C')\n" +
				"                  or substr(ds1,1,1) in ('I')\n" +
				"                  or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                  or ds1 between 'E11' and 'E11.99'\n" +
				"                  or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                  , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              )\n" +
				"              and  (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"              and not exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp where \n" +
				"                  pp.caretype = 30  \n" +
				"                  and mes in('401072','401079','401080','401127','401128','401129')  \n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              ) \n" +
				"            ) zz \n" +
				"            where (zz.mes between '401048' and '401071'  or zz.mes between '401081' and '401120')";




		/*String sb = "select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id "+
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
				+ " where zz.mes between '401048' and '401071'  ";*/

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createNativeQuery(sb);
		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		Long t2 = System.currentTimeMillis();
		System.out.println("query time:" + (t2-t1)/1000.0);
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
		System.out.println("3b time:" + (System.currentTimeMillis() - t1)/1000.0 );
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
				+"select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id \n" +
				"            from \n" +
				"            demand  d, pat  p \n" +
				"            where \n" +
				"            d.id_demand = p.demand_id \n" +
				"            and  p.caretype = 30 \n" +
				"            and   p.REZOBR in (14,21) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"            and  p.dat_end >= '01.01.2019'\n" + g +
				"            and \n" +
				"            (\n" +
				"              substr(ds1,1,1) in ('C')\n" +
				"              or substr(ds1,1,1) in ('I')\n" +
				"              or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"              or ds1 between 'E11' and 'E11.99'\n" +
				"              or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"              , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"            )\n" +
				"            and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"            and (p.fio,p.dr)  in \n" +
				"            ( \n" +
				"              select zz.fio,zz.dr \n" +
				"              from\n" +
				"              ( \n" +
				"                select p.fio,p.dr,p.mes \n" +
				"                from \n" +
				"                demand  d, pat  p \n" +
				"                where \n" +
				"                d.id_demand = p.demand_id \n" +
				"                and  p.caretype = 30 \n" +
				"                and  p.dat_end >= '01.01.2019' \n" +
				"                and  \n" +
				"                (\n" +
				"                    p.mes between '401048' and '401080' \n" +
				"                    or p.mes between '401081' and '401129'\n" +
				"                )\n" +
				"                and exists \n" +
				"                (\n" +
				"                    select 1 from pat  pp where \n" +
				"                    pp.caretype = 30 \n" +
				"                    and mes between '401048' and '401071'\n" +
				"                    and pp.fio = p.fio \n" +
				"                    and pp.dr = p.dr\n" +
				"                )\n" +
				"              ) zz\n" +
				"              where zz.mes in ('401079','401080','401128', '401129')  \n" +
				"            ) \n" +
				"            and \n" +
				"            p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            ---------------\n" +
				"            union \n" +
				"            ---------------\n" +
				"            select zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30 \n" +
				"              and   p.REZOBR in (15,23) \n" +
				"              and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and \n" +
				"              p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and \n" +
				"              (\n" +
				"                  substr(ds1,1,1) in ('C')\n" +
				"                  or substr(ds1,1,1) in ('I')\n" +
				"                  or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                  or ds1 between 'E11' and 'E11.99'\n" +
				"                  or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                  , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              )\n" +
				"              and (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"              and exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp \n" +
				"                  where pp.caretype = 30  \n" +
				"                  and mes between '401048' and '401071'\n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              )\n" +
				"            ) zz \n" +
				"            where zz.mes in ('401072','401127')\n" +
				"            ------------\n" +
				"            union \n" +
				"            ------------\n" +
				"            select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30\n" +
				"              and   p.REZOBR in (15,23) \n" +
				"              and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and\n" +
				"              ( \n" +
				"                  substr(ds1,1,1) in ('C')\n" +
				"                  or substr(ds1,1,1) in ('I')\n" +
				"                  or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                  or ds1 between 'E11' and 'E11.99'\n" +
				"                  or ds1 in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                  , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              )\n" +
				"              and  (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"              and not exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp where \n" +
				"                  pp.caretype = 30  \n" +
				"                  and mes in('401072','401079','401080','401127','401128','401129')  \n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              ) \n" +
				"            ) zz \n" +
				"            where (zz.mes between '401048' and '401071'  or  zz.mes between '401081' and '401120')"
				+" )     z_tmp "
				+" group  by z_tmp.lpu  "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc ";
		// TODO сделать выбор базы на сайте
	    Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createNativeQuery(sb);
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
	public List<WrapSp3> getResalt3a_expertise(String date1,String date2,String user, int firrstResult) throws Exception{
		//TODO полных лет
		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;

		String sb = "select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id \n" +
				"            from " +
				"            demand  d, pat  p " +
				"            where " +
				"            d.id_demand = p.demand_id " +
				"            and  p.caretype = 30 " +
				"            and   p.REZOBR in (20,22) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"            and  p.dat_end >= '01.01.2019'" + g +
				"            and substr(ds1,1,1) not in ('C')\n" +
				"            and substr(ds1,1,1) not in ('I')\n" +
				"            and substr(ds1,1,3) not between 'D00' and 'D09'\n" +
				"            and ds1 not between 'E11' and 'E11.99'\n" +
				"            and ds1 not in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                      , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"            and  \n" +
				"            (\n" +
				"                p.mes between '401048' and '401071' \n" +
				"                or p.mes between '401081' and '401120'\n" +
				"            )\n" +
				"            and  (p.fio,p.dr)  \n" +
				"            in \n" +
				"            (\n" +
				"              select zz.fio,zz.dr \n" +
				"              from \n" +
				"              ( \n" +
				"                select p.fio,p.dr,p.mes \n" +
				"                from \n" +
				"                demand  d, pat  p \n" +
				"                where \n" +
				"                d.id_demand = p.demand_id \n" +
				"                and  p.caretype = 30 \n" +
				"                and  p.dat_end >= '01.01.2019'\n" +
				"                and  (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"                and exists \n" +
				"                (" +
				"                    select 1 from pat  pp where \n" +
				"                    pp.caretype = 30 \n" +
				"                    and (mes between '401048' and '401071' or mes between '401081' and '401120')\n" +
				"                    and pp.fio = p.fio \n" +
				"                    and pp.dr = p.dr\n" +
				"                )" +
				"              ) zz\n" +
				"              where zz.mes in ('401079','401080','401128','401129')\n" +
				"            ) \n" +
				"            and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            \n" +
				"            union \n" +
				"            \n" +
				"            select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30 \n" +
				"              and   p.REZOBR in (22) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019'\n"  + g +
				"              and substr(ds1,1,1) not in ('C')\n" +
				"              and substr(ds1,1,1) not in ('I')\n" +
				"              and substr(ds1,1,3) not between 'D00' and 'D09'\n" +
				"              and ds1 not between 'E11' and 'E11.99'\n" +
				"              and ds1 not in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              and  \n" +
				"              (\n" +
				"                  p.mes between '401048' and '401080' \n" +
				"                  or p.mes between '401081' and '401129'\n" +
				"              )\n" +
				"              and  p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp where \n" +
				"                  pp.caretype = 30  \n" +
				"                  and (mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              )\n" +
				"            ) zz\n" +
				"            where zz.mes in ('401072','401127')\n" +
				"            \n" +
				"            union \n" +
				"            \n" +
				"            select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30 \n" +
				"              and   p.REZOBR in (22) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and substr(ds1,1,1) not in ('C')\n" +
				"              and substr(ds1,1,1) not in ('I')\n" +
				"              and substr(ds1,1,3) not between 'D00' and 'D09'\n" +
				"              and ds1 not between 'E11' and 'E11.99'\n" +
				"              and ds1 not in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              and  \n" +
				"              (\n" +
				"                p.mes between '401048' and '401080' \n" +
				"                or p.mes between '401081' and '401129'\n" +
				"              )\n" +
				"              and  p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and not exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp where \n" +
				"                  pp.caretype = 30  \n" +
				"                  and mes in ('401072','401079','401080','401127','401128','401129')\n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              )\n" +
				"            ) zz \n" +
				"            where (zz.mes between '401048' and '401071' or zz.mes between '401081' and '401120')";




		/*
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
		*/
		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createQuery(sb);
		//Query q = mur_collect2019.createNativeQuery(sb);
		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);

		List<Object[]> res = q.getResultList();
		if(res.size() > 0) {
			// for processed data
			List<WrapSp3> ls = new ArrayList<WrapSp3>(res.size());

			res.stream().forEach((record) -> {

				String _0 = (String) record[0];
				Date _1 = (Date) record[1];
				Long _2 = ((BigDecimal) record[2]).longValue();
				String _3 = (String) record[3];
				String _4 = (String) record[4];
				String _5 = (String) record[5];
				String _6 = (String) record[6];
				Date _7 = (Date) record[7];
				Date _8 = (Date) record[8];
				Long _9 = ((BigDecimal) record[9]).longValue();
				Long _10 = ((BigDecimal) record[10]).longValue();
				Long _11 = ((BigDecimal) record[11]).longValue();
				Date _12 = (Date) record[12];
				String _13 = (String) record[13];
				String _14 = (String) record[14];
				String _15 = (String) record[15];
				String _tel = (String) record[16];
				Long _id = ((BigDecimal) record[17]).longValue();

				ls.add(new WrapSp3(_0, _1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _tel, _id));

			});

			res = null;
			return ls;
		}else return null;
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
				+"select p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.mes, p.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel, p.id \n" +
				"            from " +
				"            demand  d, pat  p " +
				"            where " +
				"            d.id_demand = p.demand_id " +
				"            and  p.caretype = 30 " +
				"            and   p.REZOBR in (20,22) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"            and  p.dat_end >= '01.01.2019'" + g +
				"            and substr(ds1,1,1) not in ('C')\n" +
				"            and substr(ds1,1,1) not in ('I')\n" +
				"            and substr(ds1,1,3) not between 'D00' and 'D09'\n" +
				"            and ds1 not between 'E11' and 'E11.99'\n" +
				"            and ds1 not in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                      , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"            and  \n" +
				"            (\n" +
				"                p.mes between '401048' and '401071' \n" +
				"                or p.mes between '401081' and '401120'\n" +
				"            )\n" +
				"            and  (p.fio,p.dr)  \n" +
				"            in \n" +
				"            (\n" +
				"              select zz.fio,zz.dr \n" +
				"              from \n" +
				"              ( \n" +
				"                select p.fio,p.dr,p.mes \n" +
				"                from \n" +
				"                demand  d, pat  p \n" +
				"                where \n" +
				"                d.id_demand = p.demand_id \n" +
				"                and  p.caretype = 30 \n" +
				"                and  p.dat_end >= '01.01.2019'\n" +
				"                and  (p.mes between '401048' and '401080' or p.mes between '401081' and '401129')\n" +
				"                and exists \n" +
				"                (" +
				"                    select 1 from pat  pp where \n" +
				"                    pp.caretype = 30 \n" +
				"                    and (mes between '401048' and '401071' or mes between '401081' and '401120')\n" +
				"                    and pp.fio = p.fio \n" +
				"                    and pp.dr = p.dr\n" +
				"                )" +
				"              ) zz\n" +
				"              where zz.mes in ('401079','401080','401128','401129')\n" +
				"            ) \n" +
				"            and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            \n" +
				"            union \n" +
				"            \n" +
				"            select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30 \n" +
				"              and   p.REZOBR in (22) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019'\n"  + g +
				"              and substr(ds1,1,1) not in ('C')\n" +
				"              and substr(ds1,1,1) not in ('I')\n" +
				"              and substr(ds1,1,3) not between 'D00' and 'D09'\n" +
				"              and ds1 not between 'E11' and 'E11.99'\n" +
				"              and ds1 not in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              and  \n" +
				"              (\n" +
				"                  p.mes between '401048' and '401080' \n" +
				"                  or p.mes between '401081' and '401129'\n" +
				"              )\n" +
				"              and  p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp where \n" +
				"                  pp.caretype = 30  \n" +
				"                  and (mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              )\n" +
				"            ) zz\n" +
				"            where zz.mes in ('401072','401127')\n" +
				"            \n" +
				"            union \n" +
				"            \n" +
				"            select  zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.mes, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id \n" +
				"            from \n" +
				"            ( \n" +
				"              select p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id \n" +
				"              from \n" +
				"              demand  d, pat  p \n" +
				"              where \n" +
				"              d.id_demand = p.demand_id \n" +
				"              and  p.caretype = 30 \n" +
				"              and   p.REZOBR in (22) \n" +
				"            and  d.period between '"+date1+"' and '"+date2+"'  "+
				"              and  p.dat_end >= '01.01.2019' \n" + g +
				"              and substr(ds1,1,1) not in ('C')\n" +
				"              and substr(ds1,1,1) not in ('I')\n" +
				"              and substr(ds1,1,3) not between 'D00' and 'D09'\n" +
				"              and ds1 not between 'E11' and 'E11.99'\n" +
				"              and ds1 not in ('J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"              and  \n" +
				"              (\n" +
				"                p.mes between '401048' and '401080' \n" +
				"                or p.mes between '401081' and '401129'\n" +
				"              )\n" +
				"              and  p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"              and not exists \n" +
				"              (\n" +
				"                  select 1 from pat  pp where \n" +
				"                  pp.caretype = 30  \n" +
				"                  and mes in ('401072','401079','401080','401127','401128','401129')\n" +
				"                  and pp.fio = p.fio \n" +
				"                  and pp.dr = p.dr\n" +
				"              )\n" +
				"            ) zz \n" +
				"            where (zz.mes between '401048' and '401071' or zz.mes between '401081' and '401120')"


				+" )     z_tmp "
				+" group  by z_tmp.lpu "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createQuery(sb);
        //Query q = mur_collect2019.createNativeQuery(sb);
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
		/*String sb = "select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id"
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
				+"                  ) zz";*/

		String sb = "select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id\n" +
				"  from\n" +
				"      pat  p left join gosp_eir_view@link_webofoms gosp on\n" +
				"      p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)\n" +
				"      and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)\n" +
				"      and (gosp.d_np) >= '01.01.2019'\n" +
				"      left join demand  d on d.id_demand = p.demand_id\n" +
				"      where gosp.fam is  null\n" +
				"      and  p.caretype = 30\n" +
				"      and   p.REZOBR in (20,21)\n" +
				"      and\n" +
				"      (\n" +
				"       (\n" +
				"          (rezobr = 20)\n" +
				"          and\n" +
				"          (\n" +
				"            substr(ds1,1,1) in ('C')\n" +
				"            or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"            or ds1 between 'E11' and 'E11.99'\n" +
				"            or ds1 between 'I10' and 'I10.99'\n" +
				"            or ds1 between 'I11' and 'I11.99'\n" +
				"            or ds1 between 'I12' and 'I12.99'\n" +
				"            or ds1 between 'I13' and 'I13.99'\n" +
				"            or ds1 between 'I15' and 'I15.99'\n" +
				"            or ds1 between 'I48' and 'I48.99' --++\n" +
				"            or ds1 between 'I47' and 'I47.99' --++\n" +
				"            or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"          )\n" +
				"       )\n" +
				"       or\n" +
				"       (\n" +
				"          (rezobr = 21)\n" +
				"          and\n" +
				"          (\n" +
				"            ds1 between 'J12' and 'J13.99'\n" +
				"            or ds1 between 'E78' and 'E78.99'\n" +
				"            or ds1 between 'B86' and 'B86.99'\n" +
				"            or ds1 between 'K20' and 'K20.99'\n" +
				"            or ds1 between 'K25' and 'K26.99'\n" +
				"            or ds1 between 'K86' and 'K86.99'\n" +
				"            or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                      , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                      , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"          )\n" +
				"       )\n" +
				"      )\n" +
				" 	   and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"      and  p.dat_end >= '01.01.2019'\n" +
				"      and  (p.mes between '401048' and '401071' or  p.mes between '401081' and '401120')\n" +
				"      and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"      and (p.fio,p.dr)  in \n" +
				"      (\n" +
				"              select fio, dr\n" +
				"              from demand  d, pat  p\n" +
				"              where d.id_demand = p.demand_id\n" +
				"              and  p.caretype = 30\n" +
				"              and  p.dat_end >= '01.01.2019'\n" +
				"              and  d.period  between  '"+date1+"' and '"+date2+"' " +
				"              and  p.mes in ('401079','401080','401128','401129')\n" +
				"       )\n" +
				"       and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"       --------------\n" +
				"       union\n" +
				"       --------------\n" +
				"       select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id\n" +
				"       from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id\n" +
				"              from pat  p left join gosp_eir_view@link_webofoms gosp on\n" +
				"               p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)\n" +
				"               and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)\n" +
				"               and (gosp.d_np) >= '01.01.2019'\n" +
				"                   left join demand  d on d.id_demand = p.demand_id\n" +
				"                   where gosp.fam is  null\n" +
				"                   and  p.caretype = 30\n" +
				"                   and   p.REZOBR in (22,23)\n" +
				"                   and\n" +
				"                   (\n" +
				"                    (\n" +
				"                      (rezobr = 22)\n" +
				"                      and\n" +
				"                      (\n" +
				"                      substr(ds1,1,1) in ('C')\n" +
				"                      or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                      or ds1 between 'E11' and 'E11.99'\n" +
				"                      or ds1 between 'I10' and 'I10.99'\n" +
				"                      or ds1 between 'I11' and 'I11.99'\n" +
				"                      or ds1 between 'I12' and 'I12.99'\n" +
				"                      or ds1 between 'I13' and 'I13.99'\n" +
				"                      or ds1 between 'I15' and 'I15.99'\n" +
				"                      or ds1 between 'I48' and 'I48.99' --++\n" +
				"                      or ds1 between 'I47' and 'I47.99' --++\n" +
				"                      or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                        , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                        , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                        , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                        , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                        , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"                      )\n" +
				"                    )\n" +
				"                    or\n" +
				"                    (\n" +
				"                      (rezobr = 23)\n" +
				"                      and\n" +
				"                      (\n" +
				"                      ds1 between 'J12' and 'J13.99'\n" +
				"                      or ds1 between 'E78' and 'E78.99'\n" +
				"                      or ds1 between 'B86' and 'B86.99'\n" +
				"                      or ds1 between 'K20' and 'K20.99'\n" +
				"                      or ds1 between 'K25' and 'K26.99'\n" +
				"                      or ds1 between 'K86' and 'K86.99'\n" +
				"                      or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                              , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                              , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"                      )\n" +
				"                    )\n" +
				"                   )\n" +
				" 					and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"                   and  p.dat_end >= '01.01.2019'\n" +
				"                   and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"                   and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                   and not exists (select 1 from pat  pp where pp.caretype = 30\n" +
				"                   and mes in ('401072','401127')\n" +
				"                   and pp.fio = p.fio and pp.dr = p.dr)\n" +
				"                   and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            ) zz";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createQuery(sb);
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

				+"select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id\n" +
				"  from\n" +
				"      pat  p left join gosp_eir_view@link_webofoms gosp on\n" +
				"      p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)\n" +
				"      and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)\n" +
				"      and (gosp.d_np) >= '01.01.2019'\n" +
				"      left join demand  d on d.id_demand = p.demand_id\n" +
				"      where gosp.fam is  null\n" +
				"      and  p.caretype = 30\n" +
				"      and   p.REZOBR in (20,21)\n" +
				"      and\n" +
				"      (\n" +
				"       (\n" +
				"          (rezobr = 20)\n" +
				"          and\n" +
				"          (\n" +
				"            substr(ds1,1,1) in ('C')\n" +
				"            or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"            or ds1 between 'E11' and 'E11.99'\n" +
				"            or ds1 between 'I10' and 'I10.99'\n" +
				"            or ds1 between 'I11' and 'I11.99'\n" +
				"            or ds1 between 'I12' and 'I12.99'\n" +
				"            or ds1 between 'I13' and 'I13.99'\n" +
				"            or ds1 between 'I15' and 'I15.99'\n" +
				"            or ds1 between 'I48' and 'I48.99' --++\n" +
				"            or ds1 between 'I47' and 'I47.99' --++\n" +
				"            or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"          )\n" +
				"       )\n" +
				"       or\n" +
				"       (\n" +
				"          (rezobr = 21)\n" +
				"          and\n" +
				"          (\n" +
				"            ds1 between 'J12' and 'J13.99'\n" +
				"            or ds1 between 'E78' and 'E78.99'\n" +
				"            or ds1 between 'B86' and 'B86.99'\n" +
				"            or ds1 between 'K20' and 'K20.99'\n" +
				"            or ds1 between 'K25' and 'K26.99'\n" +
				"            or ds1 between 'K86' and 'K86.99'\n" +
				"            or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                      , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                      , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"          )\n" +
				"       )\n" +
				"      )\n" +
				" 	   and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"      and  p.dat_end >= '01.01.2019'\n" +
				"      and  (p.mes between '401048' and '401071' or  p.mes between '401081' and '401120')\n" +
				"      and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"      and (p.fio,p.dr)  in \n" +
				"      (\n" +
				"              select fio, dr\n" +
				"              from demand  d, pat  p\n" +
				"              where d.id_demand = p.demand_id\n" +
				"              and  p.caretype = 30\n" +
				"              and  p.dat_end >= '01.01.2019'\n" +
				"              and  d.period  between  '"+date1+"' and '"+date2+"' " +
				"              and  p.mes in ('401079','401080','401128','401129')\n" +
				"       )\n" +
				"       and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"       --------------\n" +
				"       union\n" +
				"       --------------\n" +
				"       select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id\n" +
				"       from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id\n" +
				"              from pat  p left join gosp_eir_view@link_webofoms gosp on\n" +
				"               p.fam = upper(trim(gosp.fam)) and p.im = upper(trim(gosp.im)) and p.ot = upper(trim(gosp.ot)) and p.dr = (gosp.dr)\n" +
				"               and p.dat_end <= (gosp.d_np) and substr(p.ds1,0,1) = substr(gosp.mkb_priem,0,1)\n" +
				"               and (gosp.d_np) >= '01.01.2019'\n" +
				"                   left join demand  d on d.id_demand = p.demand_id\n" +
				"                   where gosp.fam is  null\n" +
				"                   and  p.caretype = 30\n" +
				"                   and   p.REZOBR in (22,23)\n" +
				"                   and\n" +
				"                   (\n" +
				"                    (\n" +
				"                      (rezobr = 22)\n" +
				"                      and\n" +
				"                      (\n" +
				"                      substr(ds1,1,1) in ('C')\n" +
				"                      or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                      or ds1 between 'E11' and 'E11.99'\n" +
				"                      or ds1 between 'I10' and 'I10.99'\n" +
				"                      or ds1 between 'I11' and 'I11.99'\n" +
				"                      or ds1 between 'I12' and 'I12.99'\n" +
				"                      or ds1 between 'I13' and 'I13.99'\n" +
				"                      or ds1 between 'I15' and 'I15.99'\n" +
				"                      or ds1 between 'I48' and 'I48.99' --++\n" +
				"                      or ds1 between 'I47' and 'I47.99' --++\n" +
				"                      or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                        , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                        , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                        , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                        , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                        , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"                      )\n" +
				"                    )\n" +
				"                    or\n" +
				"                    (\n" +
				"                      (rezobr = 23)\n" +
				"                      and\n" +
				"                      (\n" +
				"                      ds1 between 'J12' and 'J13.99'\n" +
				"                      or ds1 between 'E78' and 'E78.99'\n" +
				"                      or ds1 between 'B86' and 'B86.99'\n" +
				"                      or ds1 between 'K20' and 'K20.99'\n" +
				"                      or ds1 between 'K25' and 'K26.99'\n" +
				"                      or ds1 between 'K86' and 'K86.99'\n" +
				"                      or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                              , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                              , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"                      )\n" +
				"                    )\n" +
				"                   )\n" +
				" 					and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"                   and  p.dat_end >= '01.01.2019'\n" +
				"                   and (p.nazr <> 5 and p.nazr <> 7 and p.nazr <> 2 and p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"                   and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                   and not exists (select 1 from pat  pp where pp.caretype = 30\n" +
				"                   and mes in ('401072','401127')\n" +
				"                   and pp.fio = p.fio and pp.dr = p.dr)\n" +
				"                   and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            ) zz"

				+" )     z_tmp "
				+" group  by z_tmp.lpu  "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc ";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createQuery(sb);
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
		/*String sb = "select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id"
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
				+"                  ) zz";*/

		String sb = "select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id\n" +
				"       from\n" +
				"       pat  p left join demand  d on d.id_demand = p.demand_id\n" +
				"       where p.caretype = 30\n" +
				"       and   p.REZOBR in (20,21)\n" +
				"       and\n" +
				"       (\n" +
				"          (\n" +
				"            (rezobr = 20)\n" +
				"            and\n" +
				"            (\n" +
				"                substr(ds1,1,1) in ('C')\n" +
				"                or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                or ds1 between 'E11' and 'E11.99'\n" +
				"                or ds1 between 'I10' and 'I10.99'\n" +
				"                or ds1 between 'I11' and 'I11.99'\n" +
				"                or ds1 between 'I12' and 'I12.99'\n" +
				"                or ds1 between 'I13' and 'I13.99'\n" +
				"                or ds1 between 'I15' and 'I15.99'\n" +
				"                or ds1 between 'I48' and 'I48.99' --++\n" +
				"                or ds1 between 'I47' and 'I47.99' --++\n" +
				"                or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                    , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                    , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                    , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                    , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                    , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                    , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"            )\n" +
				"          )\n" +
				"          or\n" +
				"          (\n" +
				"            (rezobr = 21)\n" +
				"            and\n" +
				"            (\n" +
				"              ds1 between 'J12' and 'J13.99'\n" +
				"              or ds1 between 'E78' and 'E78.99'\n" +
				"              or ds1 between 'B86' and 'B86.99'\n" +
				"              or ds1 between 'K20' and 'K20.99'\n" +
				"              or ds1 between 'K25' and 'K26.99'\n" +
				"              or ds1 between 'K86' and 'K86.99'\n" +
				"              or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                        , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                        , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"            )\n" +
				"          )\n" +
				"       )\n" +
				" 	    and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"       and  p.dat_end >= '01.01.2019'\n" +
				"       and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"       and (p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"       and (p.fio,p.dr)  in \n" +
				"       (\n" +
				"          select fio, dr\n" +
				"          from demand  d, pat  p\n" +
				"          where d.id_demand = p.demand_id\n" +
				"          and  p.caretype = 30\n" +
				"          and  p.dat_end >= '01.01.2019'\n" +
				"          and  d.period  between  '"+date1+"' and '"+date2+"' " +
				"          and  p.mes in ('401079','401080','401128','401129')\n" +
				"       )\n" +
				"       and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"       ------------------------\n" +
				"       union\n" +
				"       ------------------------\n" +
				"       select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id\n" +
				"       from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id\n" +
				"              from pat  p left join demand  d on d.id_demand = p.demand_id\n" +
				"           where p.caretype = 30\n" +
				"           and   p.REZOBR in (22,23)\n" +
				"           and\n" +
				"           (\n" +
				"                (\n" +
				"                  (rezobr = 22)\n" +
				"                  and\n" +
				"                  (\n" +
				"                    substr(ds1,1,1) in ('C')\n" +
				"                    or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                    or ds1 between 'E11' and 'E11.99'\n" +
				"                    or ds1 between 'I10' and 'I10.99'\n" +
				"                    or ds1 between 'I11' and 'I11.99'\n" +
				"                    or ds1 between 'I12' and 'I12.99'\n" +
				"                    or ds1 between 'I13' and 'I13.99'\n" +
				"                    or ds1 between 'I15' and 'I15.99'\n" +
				"                    or ds1 between 'I48' and 'I48.99' --++\n" +
				"                    or ds1 between 'I47' and 'I47.99' --++\n" +
				"                    or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                        , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                        , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                        , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                        , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                        , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"                  )\n" +
				"              )\n" +
				"              or\n" +
				"              (\n" +
				"                (rezobr = 23)\n" +
				"                and\n" +
				"                (\n" +
				"                  ds1 between 'J12' and 'J13.99'\n" +
				"                  or ds1 between 'E78' and 'E78.99'\n" +
				"                  or ds1 between 'B86' and 'B86.99'\n" +
				"                  or ds1 between 'K20' and 'K20.99'\n" +
				"                  or ds1 between 'K25' and 'K26.99'\n" +
				"                  or ds1 between 'K86' and 'K86.99'\n" +
				"                  or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                            , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                             , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"                    \n" +
				"                )\n" +
				"              )\n" +
				"          )\n" +
				" 	    and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"          and  p.dat_end >= '01.01.2019'\n" +
				"          and (p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"          and  p.mes in ('401072','401127')\n" +
				"          and exists (select 1 from pat  pp \n" +
				"                      where pp.caretype = 30\n" +
				"                      and (pp.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                      and pp.fio = p.fio and pp.dr = p.dr)\n" +
				"          and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            ) zz\n" +
				"          -----------------\n" +
				"          union\n" +
				"          -----------------\n" +
				"          select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id\n" +
				"           from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id\n" +
				"                 from pat  p left join demand  d on d.id_demand = p.demand_id\n" +
				"                 where p.caretype = 30\n" +
				"                 and  p.REZOBR in (22,23)\n" +
				"                 and\n" +
				"                 (\n" +
				"                   (\n" +
				"                    (rezobr = 22)\n" +
				"                    and\n" +
				"                    (\n" +
				"                      substr(ds1,1,1) in ('C')\n" +
				"                      or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                      or ds1 between 'E11' and 'E11.99'\n" +
				"                      or ds1 between 'I10' and 'I10.99'\n" +
				"                      or ds1 between 'I11' and 'I11.99'\n" +
				"                      or ds1 between 'I12' and 'I12.99'\n" +
				"                      or ds1 between 'I13' and 'I13.99'\n" +
				"                      or ds1 between 'I15' and 'I15.99'\n" +
				"                      or ds1 between 'I48' and 'I48.99' --++\n" +
				"                      or ds1 between 'I47' and 'I47.99' --++\n" +
				"                      or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                          , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                          , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                          , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                          , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                          , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                          , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"                    )\n" +
				"                  )\n" +
				"                  or\n" +
				"                  (\n" +
				"                    (rezobr = 23)\n" +
				"                    and\n" +
				"                    (\n" +
				"                      ds1 between 'J12' and 'J13.99'\n" +
				"                      or ds1 between 'E78' and 'E78.99'\n" +
				"                      or ds1 between 'B86' and 'B86.99'\n" +
				"                      or ds1 between 'K20' and 'K20.99'\n" +
				"                      or ds1 between 'K25' and 'K26.99'\n" +
				"                      or ds1 between 'K86' and 'K86.99'\n" +
				"                      or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                                , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                                , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"                    )\n" +
				"                  )\n" +
				"                 )\n" +
				" 	    and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"                 and  p.dat_end >= '01.01.2019'\n" +
				"                 and (p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"                 and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                 and not exists (select 1 from pat  pp where pp.caretype = 30\n" +
				"                       and mes in ('401072')\n" +
				"                       and pp.fio = p.fio and pp.dr = p.dr)\n" +
				"                 and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"               ) zz";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createQuery(sb);
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

				+"select p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.mes,p.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,p.id\n" +
				"       from\n" +
				"       pat  p left join demand  d on d.id_demand = p.demand_id\n" +
				"       where p.caretype = 30\n" +
				"       and   p.REZOBR in (20,21)\n" +
				"       and\n" +
				"       (\n" +
				"          (\n" +
				"            (rezobr = 20)\n" +
				"            and\n" +
				"            (\n" +
				"                substr(ds1,1,1) in ('C')\n" +
				"                or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                or ds1 between 'E11' and 'E11.99'\n" +
				"                or ds1 between 'I10' and 'I10.99'\n" +
				"                or ds1 between 'I11' and 'I11.99'\n" +
				"                or ds1 between 'I12' and 'I12.99'\n" +
				"                or ds1 between 'I13' and 'I13.99'\n" +
				"                or ds1 between 'I15' and 'I15.99'\n" +
				"                or ds1 between 'I48' and 'I48.99' --++\n" +
				"                or ds1 between 'I47' and 'I47.99' --++\n" +
				"                or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                    , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                    , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                    , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                    , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                    , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                    , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"            )\n" +
				"          )\n" +
				"          or\n" +
				"          (\n" +
				"            (rezobr = 21)\n" +
				"            and\n" +
				"            (\n" +
				"              ds1 between 'J12' and 'J13.99'\n" +
				"              or ds1 between 'E78' and 'E78.99'\n" +
				"              or ds1 between 'B86' and 'B86.99'\n" +
				"              or ds1 between 'K20' and 'K20.99'\n" +
				"              or ds1 between 'K25' and 'K26.99'\n" +
				"              or ds1 between 'K86' and 'K86.99'\n" +
				"              or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                        , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                        , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"            )\n" +
				"          )\n" +
				"       )\n" +
				" 	    and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"       and  p.dat_end >= '01.01.2019'\n" +
				"       and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"       and (p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"       and (p.fio,p.dr)  in \n" +
				"       (\n" +
				"          select fio, dr\n" +
				"          from demand  d, pat  p\n" +
				"          where d.id_demand = p.demand_id\n" +
				"          and  p.caretype = 30\n" +
				"          and  p.dat_end >= '01.01.2019'\n" +
				"          and  d.period  between  '"+date1+"' and '"+date2+"' " +
				"          and  p.mes in ('401079','401080','401128','401129')\n" +
				"       )\n" +
				"       and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"       ------------------------\n" +
				"       union\n" +
				"       ------------------------\n" +
				"       select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id\n" +
				"       from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id\n" +
				"              from pat  p left join demand  d on d.id_demand = p.demand_id\n" +
				"           where p.caretype = 30\n" +
				"           and   p.REZOBR in (22,23)\n" +
				"           and\n" +
				"           (\n" +
				"                (\n" +
				"                  (rezobr = 22)\n" +
				"                  and\n" +
				"                  (\n" +
				"                    substr(ds1,1,1) in ('C')\n" +
				"                    or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                    or ds1 between 'E11' and 'E11.99'\n" +
				"                    or ds1 between 'I10' and 'I10.99'\n" +
				"                    or ds1 between 'I11' and 'I11.99'\n" +
				"                    or ds1 between 'I12' and 'I12.99'\n" +
				"                    or ds1 between 'I13' and 'I13.99'\n" +
				"                    or ds1 between 'I15' and 'I15.99'\n" +
				"                    or ds1 between 'I48' and 'I48.99' --++\n" +
				"                    or ds1 between 'I47' and 'I47.99' --++\n" +
				"                    or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                        , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                        , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                        , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                        , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                        , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                        , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"                  )\n" +
				"              )\n" +
				"              or\n" +
				"              (\n" +
				"                (rezobr = 23)\n" +
				"                and\n" +
				"                (\n" +
				"                  ds1 between 'J12' and 'J13.99'\n" +
				"                  or ds1 between 'E78' and 'E78.99'\n" +
				"                  or ds1 between 'B86' and 'B86.99'\n" +
				"                  or ds1 between 'K20' and 'K20.99'\n" +
				"                  or ds1 between 'K25' and 'K26.99'\n" +
				"                  or ds1 between 'K86' and 'K86.99'\n" +
				"                  or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                            , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                             , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"                    \n" +
				"                )\n" +
				"              )\n" +
				"          )\n" +
				" 	    and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"          and  p.dat_end >= '01.01.2019'\n" +
				"          and (p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"          and  p.mes in ('401072','401127')\n" +
				"          and exists (select 1 from pat  pp \n" +
				"                      where pp.caretype = 30\n" +
				"                      and (pp.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                      and pp.fio = p.fio and pp.dr = p.dr)\n" +
				"          and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"            ) zz\n" +
				"          -----------------\n" +
				"          union\n" +
				"          -----------------\n" +
				"          select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.mes,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id\n" +
				"           from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id\n" +
				"                 from pat  p left join demand  d on d.id_demand = p.demand_id\n" +
				"                 where p.caretype = 30\n" +
				"                 and  p.REZOBR in (22,23)\n" +
				"                 and\n" +
				"                 (\n" +
				"                   (\n" +
				"                    (rezobr = 22)\n" +
				"                    and\n" +
				"                    (\n" +
				"                      substr(ds1,1,1) in ('C')\n" +
				"                      or substr(ds1,1,3) between 'D00' and 'D09'\n" +
				"                      or ds1 between 'E11' and 'E11.99'\n" +
				"                      or ds1 between 'I10' and 'I10.99'\n" +
				"                      or ds1 between 'I11' and 'I11.99'\n" +
				"                      or ds1 between 'I12' and 'I12.99'\n" +
				"                      or ds1 between 'I13' and 'I13.99'\n" +
				"                      or ds1 between 'I15' and 'I15.99'\n" +
				"                      or ds1 between 'I48' and 'I48.99' --++\n" +
				"                      or ds1 between 'I47' and 'I47.99' --++\n" +
				"                      or ds1 in ('I20.1', 'I20.8', 'I20.9', 'I25.0'\n" +
				"                          , 'I25.1', 'I25.2', 'I25.5', 'I25.6'\n" +
				"                          , 'I25.8', 'I25.9', 'I50.0', 'I50.1'\n" +
				"                          , 'I50.9' , 'I65.2', 'I69.0', 'I69.1'\n" +
				"                          , 'I69.2', 'I69.3', 'I69.4', 'I67.8'\n" +
				"                          , 'J45.0', 'J45.1', 'J45.8', 'J45.9'\n" +
				"                          , 'J44.0', 'J44.8',  'J44.9', 'M81.5')\n" +
				"                    )\n" +
				"                  )\n" +
				"                  or\n" +
				"                  (\n" +
				"                    (rezobr = 23)\n" +
				"                    and\n" +
				"                    (\n" +
				"                      ds1 between 'J12' and 'J13.99'\n" +
				"                      or ds1 between 'E78' and 'E78.99'\n" +
				"                      or ds1 between 'B86' and 'B86.99'\n" +
				"                      or ds1 between 'K20' and 'K20.99'\n" +
				"                      or ds1 between 'K25' and 'K26.99'\n" +
				"                      or ds1 between 'K86' and 'K86.99'\n" +
				"                      or ds1 in ('J41.0', 'J41.1', 'J41.8', 'J47.0'\n" +
				"                                , 'J84.1', 'R73.0','R73.9','N18.1'\n" +
				"                                , 'N18.9','K29.4','K29.5','K31.7', 'K21.0')\n" +
				"\n" +
				"                    )\n" +
				"                  )\n" +
				"                 )\n" +
				" 	    and  d.period between  '"+date1+"' and '"+date2+"' " + g +
				"                 and  p.dat_end >= '01.01.2019'\n" +
				"                 and (p.pr_d_n = 0 or p.pr_d_n = 3)\n" +
				"                 and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                 and not exists (select 1 from pat  pp where pp.caretype = 30\n" +
				"                       and mes in ('401072')\n" +
				"                       and pp.fio = p.fio and pp.dr = p.dr)\n" +
				"                 and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"               ) zz"

				+" )     z_tmp "
				+" group  by z_tmp.lpu  "
				+" ) tmp1,  "
				+" (  select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name from medical_organization@dome_dawn t  where t.mo_d_end is null  and t.mo_mcod like '540%'  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  ) tmp  where tmp1.lp = tmp.codl order by tmp1.total desc ";

		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createQuery(sb);
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
	public List<AfterDisp3Group> getResalt_after_disp_3_group(String date1, String date2, String user) {
		/*String sb = "select " +
				"      tmp1.lp as code_mo ," +
				"      tmp.name as name_mo," +
				"      tmp1.total," +
				"      tmp1.total_1," +
				"      tmp1.total_2," +
				"      tmp1.total_4," +
				"      tmp1.total_20_22," +
				"      tmp1.total_20," +
				"      tmp1.total_22," +
				"      tmp1.total_21_23," +
				"      tmp1.total_21," +
				"      tmp1.total_23 " +
				"from" +
				"  ( " +
				"  select   z_tmp.lpu as lp," +
				"           count(*) total," +
				"           count (case when z_tmp.smoid = 1 then 1 end) total_1," +
				"           count (case when z_tmp.smoid = 2 then 1 end) total_2," +
				"           count (case when z_tmp.smoid = 4 then 1 end) total_4," +
				"           count (case when z_tmp.rezobr in (20,22) then 1 end) as total_20_22," +
				"           count (case when z_tmp.rezobr in (20) then 1 end) as total_20," +
				"           count (case when z_tmp.rezobr in (22) then 1 end) as total_22," +
				"           count (case when z_tmp.rezobr in (21,23) then 1 end ) as total_21_23," +
				"           count (case when z_tmp.rezobr in (21) then 1 end) as total_21," +
				"           count (case when z_tmp.rezobr in (23) then 1 end) as total_23 " +
				"     from " +
				"       (select p.FIO," +
				"              p.dr," +
				"              p.SMOID," +
				"              p.SERPOLIS," +
				"              p.NUMPOLIS," +
				"              p.lpu," +
				"              p.AMBKARTA," +
				"              p.DAT_BEG," +
				"              p.DAT_END," +
				"              LPU_PRIK," +
				"              p.s1," +
				"              p.account," +
				"              p.AC_DATE," +
				"              p.REZOBR," +
				"              p.ds1 as f_mkb_usl," +
				"              f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel," +
				"              p.id" +
				"          from" +
				"            pat p, demand d" +
				"            where" +
				"            d.id_demand = p.demand_id" +
				"            and  p.caretype = 30" +
				"            and   p.REZOBR in (20,21)" +
				"            and  d.period between  '"+date1+"' and '"+date2+"' " +
				"            and  p.dat_end >= '01.01.2018'" +
				"            and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')" +
				"            and (p.fio,p.dr)  in (" +
				"                                  select fio, dr" +
				"                                                from pat pp" +
				"                                                   where pp.caretype = 30" +
				"                                                   and  pp.dat_end >= '01.01.2018'" +
				"                                                   and  pp.mes in (401079,401080))" +
				"              and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)" +
				"          union " +
				"          select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id " +
				"             from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id " +
				"                   from demand d, pat p " +
				"                   where" +
				"                   d.id_demand = p.demand_id" +
				"                   and  p.caretype = 30" +
				"                   and   p.REZOBR in (22,23)" +
				"                   and  d.period between  '"+date1+"' and '"+date2+"' " +
				"                   and  p.dat_end >= '01.01.2018'" +
				"                   and  p.mes='401072'" +
				"                   and exists (select 1 from pat pp" +
				"                                                   where pp.caretype = 30  " +
				"                                                          and (pp.mes between '401048' and '401071' or pp.mes between '401081' and '401083') " +
				"                                                          and pp.fio = p.fio and pp.dr = p.dr)" +
				"                   and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)" +
				"                   ) zz " +
				"          union " +
				"          select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id " +
				"             from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id " +
				"                    from demand d, pat p " +
				"                    where" +
				"                           d.id_demand = p.demand_id" +
				"                           and  p.caretype = 30" +
				"                           and  p.REZOBR in (22,23)" +
				"                           and  d.period between  '"+date1+"' and '"+date2+"' " +
				"                           and  p.dat_end >= '01.01.2018'" +
				"                           and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083')" +
				"                           and not exists (select 1 from pat pp" +
				"                                                   where pp.caretype = 30" +
				"                                                                      and mes in (401072)" +
				"                                                                      and pp.fio = p.fio and pp.dr = p.dr)" +
				"                           and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)" +
				"                   ) zz" +
				"          union " +
				"          select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id " +
				"             from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id " +
				"                    from demand d, pat p " +
				"                    where " +
				"                          d.id_demand = p.demand_id " +
				"                          and  p.caretype = 30 " +
				"                          and   p.REZOBR in (20,21) " +
				"                          and  d.period between  '"+date1+"' and '"+date2+"' " +
				"                          and  p.dat_end >= '01.01.2018' " +
				"                          and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401083') " +
				"                          and not exists (select 1 from pat pp " +
				"                                                   where pp.caretype = 30 " +
				"                                                               and mes in(401072,401079,401080) " +
				"                                                               and pp.fio = p.fio and pp.dr = p.dr) " +
				"                          and p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)) zz " +
				"                  where (zz.mes between '401048' and '401071' or zz.mes between '401081' and '401083')) z_tmp " +
				"   group  by z_tmp.lpu " +
				"   ) tmp1, (select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name " +
				"  from medical_organization@dome_dawn t " +
				"  where t.mo_d_end is null " +
				"  and t.mo_mcod like '540%' " +
				"  and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  " +
				") tmp where tmp1.lp = tmp.codl " +
				"order by tmp1.total desc";*/
		String sb = "select \n" +
				"            tmp1.lp as code_mo ,\n" +
				"            tmp.name as name_mo,\n" +
				"            tmp1.total,\n" +
				"            tmp1.total_1,\n" +
				"            tmp1.total_2,\n" +
				"            tmp1.total_4,\n" +
				"            tmp1.total_20_22,\n" +
				"            tmp1.total_20,\n" +
				"            tmp1.total_22,\n" +
				"            tmp1.total_21_23,\n" +
				"            tmp1.total_21,\n" +
				"            tmp1.total_23 \n" +
				"      from\n" +
				"        ( \n" +
				"        select   z_tmp.lpu as lp,\n" +
				"                 count(*) total,\n" +
				"                 count (case when z_tmp.smoid = 1 then 1 end) total_1,\n" +
				"                 count (case when z_tmp.smoid = 2 then 1 end) total_2,\n" +
				"                 count (case when z_tmp.smoid = 4 then 1 end) total_4,\n" +
				"                 count (case when z_tmp.rezobr in (20,22) then 1 end) as total_20_22,\n" +
				"                 count (case when z_tmp.rezobr in (20) then 1 end) as total_20,\n" +
				"                 count (case when z_tmp.rezobr in (22) then 1 end) as total_22,\n" +
				"                 count (case when z_tmp.rezobr in (21,23) then 1 end ) as total_21_23,\n" +
				"                 count (case when z_tmp.rezobr in (21) then 1 end) as total_21,\n" +
				"                 count (case when z_tmp.rezobr in (23) then 1 end) as total_23 \n" +
				"           from \n" +
				"             (select p.FIO,\n" +
				"                    p.dr,\n" +
				"                    p.SMOID,\n" +
				"                    p.SERPOLIS,\n" +
				"                    p.NUMPOLIS,\n" +
				"                    p.lpu,\n" +
				"                    p.AMBKARTA,\n" +
				"                    p.DAT_BEG,\n" +
				"                    p.DAT_END,\n" +
				"                    LPU_PRIK,\n" +
				"                    p.s1,\n" +
				"                    p.account,\n" +
				"                    p.AC_DATE,\n" +
				"                    p.REZOBR,\n" +
				"                    p.ds1 as f_mkb_usl,\n" +
				"                    f_person_telephone_v2@dome_dev(p.fam, p.im, p.ot, p.dr) as tel,\n" +
				"                    p.id\n" +
				"                from\n" +
				"                  pat  p, demand  d\n" +
				"                  where\n" +
				"                  d.id_demand = p.demand_id\n" +
				"                  and  p.caretype = 30\n" +
				"                  and   p.REZOBR in (20,21)\n" +
				"                  and  d.period between  '"+date1+"' and '"+date2+"' " +
				"                  and  p.dat_end >= '01.01.2019'\n" +
				"                  and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                  and (p.fio,p.dr)  in \n" +
				"                  (\n" +
				"                    select fio, dr\n" +
				"                    from pat  pp\n" +
				"                    where pp.caretype = 30\n" +
				"                    and  pp.dat_end >= '01.01.2019'\n" +
				"                    and  pp.mes in ('401079','401080','401128','401129')\n" +
				"                  )\n" +
				"                  and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"                union -------------------------------------\n" +
				"                select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id \n" +
				"                   from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id \n" +
				"                         from demand  d, pat  p \n" +
				"                         where\n" +
				"                         d.id_demand = p.demand_id\n" +
				"                         and  p.caretype = 30\n" +
				"                         and   p.REZOBR in (22,23)\n" +
				"                  	      and  d.period between  '"+date1+"' and '"+date2+"' " +
				"                         and  p.dat_end >= '01.01.2019'\n" +
				"                         and  p.mes in ('401072','401127')\n" +
				"                         and exists \n" +
				"                         (\n" +
				"                            select 1 from pat  pp\n" +
				"                            where pp.caretype = 30  \n" +
				"                            and (pp.mes between '401048' and '401071' or pp.mes between '401081' and '401120') \n" +
				"                            and pp.fio = p.fio and pp.dr = p.dr\n" +
				"                         )\n" +
				"                         and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"                         ) zz \n" +
				"                union ----------------------\n" +
				"                select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id \n" +
				"                   from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id \n" +
				"                          from demand  d, pat  p \n" +
				"                          where\n" +
				"                                 d.id_demand = p.demand_id\n" +
				"                                 and  p.caretype = 30\n" +
				"                                 and  p.REZOBR in (22,23)\n" +
				"                                 and  d.period between  '"+date1+"' and '"+date2+"' " +
				"                                 and  p.dat_end >= '01.01.2019'\n" +
				"                                 and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120')\n" +
				"                                 and not exists \n" +
				"                                 (\n" +
				"                                    select 1 from pat  pp\n" +
				"                                    where pp.caretype = 30\n" +
				"                                    and mes in ('401072','401127')\n" +
				"                                    and pp.fio = p.fio and pp.dr = p.dr\n" +
				"                                 )\n" +
				"                                 and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"                         ) zz\n" +
				"                union --------------------------\n" +
				"                select  zz.FIO,zz.dr,zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA,zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR,zz.ds1 as f_mkb_usl,f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel,zz.id \n" +
				"                   from (select p.mes,p.FIO,p.dr,p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA,p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR,p.ds1,p.fam,p.im,p.ot,p.dr as drr,p.id \n" +
				"                          from demand  d, pat  p \n" +
				"                          where \n" +
				"                                d.id_demand = p.demand_id \n" +
				"                                and  p.caretype = 30 \n" +
				"                                and   p.REZOBR in (20,21) \n" +
				"                                and  d.period between  '"+date1+"' and '"+date2+"' " +
				"                                and  p.dat_end >= '01.01.2019' \n" +
				"                                and  (p.mes between '401048' and '401071' or p.mes between '401081' and '401120') \n" +
				"                                and not exists \n" +
				"                                (\n" +
				"                                  select 1 from pat  pp \n" +
				"                                  where pp.caretype = 30 \n" +
				"                                  and mes in('401072','401079','401080','401127','401128','401129') \n" +
				"                                  and pp.fio = p.fio and pp.dr = p.dr\n" +
				"                                ) \n" +
				"                                and p.id not in (select ot.id_pred from otkl_id  ot where ot.id_pred = p.id)\n" +
				"                        ) zz \n" +
				"                        where (zz.mes between '401048' and '401071' or zz.mes between '401081' and '401120')) z_tmp \n" +
				"         group  by z_tmp.lpu \n" +
				"         ) tmp1, (select  distinct substr(t.mo_mcod,4,6) as codl,t.mo_nam_mok as name \n" +
				"        from medical_organization@dome_dawn t \n" +
				"        where t.mo_d_end is null \n" +
				"        and t.mo_mcod like '540%' \n" +
				"        and t.mo_d_edit = (select max(t2.mo_d_edit) from medical_organization@dome_dawn t2 where t.mo_mcod = t2.mo_mcod)  \n" +
				"      ) tmp where tmp1.lp = tmp.codl \n" +
				"      order by tmp1.total desc";
		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);
        //Query q = mur_collect2019.createQuery(sb);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<AfterDisp3Group> ls = new ArrayList<AfterDisp3Group>(res.size());

		res.stream().forEach((record) -> {

			String _0 = (String) record[0];
			String _1 = (String) record[1];
			Long _2 = ((BigDecimal) record[2]).longValue();
			Long _3 = ((BigDecimal) record[3]).longValue();
			Long _4 = ((BigDecimal) record[4]).longValue();
			Long _5 = ((BigDecimal) record[5]).longValue();
			Long _6 = ((BigDecimal) record[6]).longValue();
			Long _7 = ((BigDecimal) record[7]).longValue();
			Long _8 = ((BigDecimal) record[8]).longValue();
			Long _9 = ((BigDecimal) record[9]).longValue();
			Long _10 = ((BigDecimal) record[10]).longValue();
			Long _11 = ((BigDecimal) record[11]).longValue();

			ls.add(new AfterDisp3Group(_0, _1, _2, _3,_4,_5,_6,_7,_8,_9,_10,_11));

		});

		res=null;

		return ls;
	}
}
