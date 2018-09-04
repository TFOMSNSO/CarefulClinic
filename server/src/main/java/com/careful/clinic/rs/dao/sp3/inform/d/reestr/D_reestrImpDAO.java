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
import com.careful.clinic.model.InformDReestr;

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
	public List<InformDReestr> getResalt_D_reestrCollect2018(String date1,String date2,String user, int firrstResult) {


		String g = user.equals("777")  ?  " " : " and  p.smoid ="+user+ " " ;
		//
		//String sb = "select  zz.id_assent,zz.person_linksmoestablishmentid,zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id"
		String sb = "select rec.fam||' '||rec.im||' '||rec.ot as FIO,  rec.dr, f_person_telephone_v2@dome(rec.fam, rec.im, rec.ot, rec.dr) as tel, ADDRESS_PERSONFIO@Dome(rec.fam, rec.im, rec.ot, rec.dr) as address, per.person_linksmoestablishmentid as smo, nvl(per.person_serpolicy||per.person_numpolicy,pa.enp) as pol, yy.lpu, yy.ambkarta, yy.dat_beg, rec.date_end_disp,per.person_establishmentambul as mo_prik, yy.account, yy.ac_date, rec.rezobr, rec.ds1, rec.p_r_dn, yy.mes, -1*sc.back_propagation_month as kratnost, rec.last_treatment, pl2.plan_inform, pii.date_inform, ass.id_assent" +
				"from d_records  rec"+
				"left join pat@link_collect2018 yy on rec.pat_id=yy.id"+
				"left join visit_schedule sc on (rec.ds1 between sc.mkb_start and sc.mkb_end)"+
				"left join (select  pl.fam,pl.im,pl.ot,pl.dr, max(pl.date_plan_info) as plan_inform from plan_pm_i_d_records pl group by pl.fam,pl.im,pl.ot,pl.dr ) pl2  on (pl2.fam=rec.fam) and (pl2.im=rec.im) and (pl2.ot=rec.ot) and (pl2.dr=rec.dr)"+
				"left join  pm_assent ass on ass.fam=rec.fam and ass.im=rec.im and ass.ot=rec.ot and ass.dr=rec.dr and ass.id_assent is not null"+
				"left join person@dome per on per.person_surname=rec.fam and per.person_kindfirstname=rec.im and per.person_kindlastname=rec.ot and per.person_birthday=rec.dr"+
				"left join personadd@dome pa on per.person_addressid = pa.personadd_addressid"+
				"left join (select pi.fam,pi.im,pi.ot,pi.dr,max(pi.d_info) as date_inform from pm_i pi where pi.n_stage =5 group by pi.fam,pi.im,pi.ot,pi.dr) pii on pii.fam=rec.fam and pii.im=rec.im and pii.ot=rec.ot and pii.dr=rec.dr"+
				"where"+
				"(pl2.plan_inform not between pii.date_inform - 45 and pii.date_inform + 45 or pii.date_inform is null)"+
				"and"+
				"pl2.plan_inform between '"+date1+"' and '"+date2+"' ";


		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2018.createNativeQuery(sb);

		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<InformDReestr> ls = new ArrayList<InformDReestr>(res.size());

		res.stream().forEach((record) -> {

			String _0 = (String) record[0];
			Date _1 = (Date) record[1];
			String _tel = (String) record[2];
			String _3 = (String) record[3];
			String _4 = (String) record[4];
			String _5 = (String) record[5];
			String _6 = (String) record[6];
			String _7 = (String) record[7];
			Date _8 = (Date) record[8];
			Date _9 = (Date) record[9];
			String _10 = (String) record[10];
			String _11 = (String)  record[11];
			Date _12 = (Date) record[12];
			String _13 = (String)  record[13];
			String _14 = (String)  record[14];
			String _15 = (String)  record[15];
			String _16 = (String)  record[16];
			String _17 = (String)  record[17];
			Date _18 = (Date) record[18];
			Date _19 = (Date) record[19];
			Date _20 = (Date) record[20];
			Long _id = ((BigDecimal)  record[21]).longValue();

			ls.add(new InformDReestr(_0, _1, _tel, _3,_4,_5, _6,_7,_8,_9,_10,_11,_12,_13,_14, _15,   _16, _17, _18, _19, _20,  _id));

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
