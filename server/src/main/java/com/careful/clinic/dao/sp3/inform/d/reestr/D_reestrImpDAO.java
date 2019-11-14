package com.careful.clinic.dao.sp3.inform.d.reestr;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.careful.clinic.model.JobWork;
import com.careful.clinic.model.ListExcelFiles;
import com.careful.clinic.model.Sp3RateMo;
import com.careful.clinic.model.InformDReestr;
import com.careful.clinic.rs.sp3.inform.d.reestr.report.D_reestr_Report;

@ManagedBean
@Stateless
public class D_reestrImpDAO  implements D_reestr{

	@PersistenceContext(unitName="NONXAMUR2018")
	private EntityManager non_mur_collect2018;

	@PersistenceContext(unitName="NONXAMUR2019")
	private EntityManager non_mur_collect2019;

	@PersistenceContext(unitName = "OracleDream2DS")
	private EntityManager ofoms;

	private HttpSession dreestrSession;


    @Override
    public List<InformDReestr> getDReestrUpdated() {
        List<Object[]> rs = non_mur_collect2019.createNativeQuery("select * from V_DISP_RECORD_NEW_2019_MAIN@LINK_OFOMS").getResultList();


        System.out.println("Count rs:"  +  rs.size());
        System.out.println(Arrays.toString(rs.get(0)));
        ArrayList<InformDReestr> result = new ArrayList<>();

        rs.stream().forEach(record -> {
            try {
                Long npp = ((BigDecimal) record[0]).longValue();
                String _0 = (String) record[1]; //fam
                String _1 = (String) record[2];// im
                String _2 = (String) record[3]; // ot
                Date _3 = (Date) record[4]; // dr
                String _tel = record[5] == null ? "" : (String) record[5]; // tel
                String _5 = record[6] == null ? "" : (String) record[6]; // address
                Long _6 = record[7] == null ? null : ((BigDecimal) record[7]).longValue(); // smo
                String _7 = record[8] == null ? "" : (String) record[8]; // polis?
                Long _8 =  record[9] == null ? null : ((BigDecimal) record[9]).longValue();//kpuid
                String _9 = record[10] == null ? "" : (String) record[10];// ambkarta
                Date _10 = record[11] == null ? null : (Date) record[11]; //dat_beg
                Date _11 = record[12] == null ? null : (Date) record[12];  //date_end_disp
                String _12 = record[13] == null ? "" : (String) record[13]; // mo_prik
                Long _13 = record[14] == null ? null :  ((BigDecimal) record[14]).longValue();
                Date _14 = record[15] == null ? null : (Date) record[15]; // ac_date
                String _15 = record[16] == null ? "" : (String) record[16]; //rezobr
                String _16 = record[17] == null ? "" : (String) record[17]; //ds1
                Long _17 = record[18] == null ? null : ((BigDecimal) record[18]).longValue(); // p_r_dn
                String _18 = record[19] == null ? "" : (String) record[19]; // mes
                String _19 = record[20] == null ? "" : (String) record[20]; // kratnost
                Date _20 = record[21] == null ? null : (Date) record[21];//last_treatment
                Date _21 = record[22] == null ? null : (Date) record[22]; // plan_inform
                Date _22 =  record[23] == null ? null :  (Date) record[23]; //date_inform
                String _23 = record[24] == null ? "" : (String) record[24]; //mes6
                Date _24 = record[25] == null ?  null : (Date) record[25]; // dat_end_mas6
                Long _25 = record[25] == null ? null : ((BigDecimal) record[26]).longValue(); // n

                result.add(new InformDReestr(npp, _0, _1, _2, _3, _tel, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24, _25));

            }catch (NullPointerException e){
                System.out.println("exception:" + e);

            }
        });

        return result;
    }

    private String addDate(String directoryDestination, String ob) {
		File file = new File(directoryDestination + File.separator + ob);
		final long lastModified = file.lastModified();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-");
		String x = sdf.format(new Date(lastModified));
		return x;
	}

	@Override
	public List<JobWork> refreshDReestrInfo(HttpServletRequest request) {
        List<JobWork> jobs = ofoms.createNamedQuery("findJobByDate",JobWork.class).setParameter("inDate",new Date()).getResultList();
        return jobs;
	}


	@Override
	public Response executeDReestrUpdate(HttpServletRequest request) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMM");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd.MM.yyyy");
		Date today = new Date();
		String targetDate = simpleDateFormat.format(today);
		List<JobWork> jobWorks = ofoms.createNamedQuery("findJobByDate",JobWork.class).setParameter("inDate",today).getResultList();


		if(!jobWorks.isEmpty())
			return Response.status(222).entity("Сегодня уже было обновление списков. Попробуйте завтра.").build();



		StoredProcedureQuery storedProcedureQuery = ofoms.createStoredProcedureQuery("eir_d_reestr_update");
		storedProcedureQuery.registerStoredProcedureParameter("v_date",String.class,ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("jobid",Integer.class,ParameterMode.OUT);

		storedProcedureQuery.setParameter("v_date",targetDate);

		storedProcedureQuery.execute();

		dreestrSession = request.getSession(true);
		dreestrSession.setAttribute("jobDate",simpleDateFormat1.format(today));
		return Response.ok().entity("Обновление списков успешно запущено.").build();
	}




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
		List<String> lsn = new ArrayList<>();
		List<ListExcelFiles> ls = new ArrayList<ListExcelFiles>();
		for (int i = 0; i < ob.length; i++) {
			String x =addDate(directoryDestination, ob[i]);
			lsn.add(x + ob[i]);
		}
		for (int j = 0; j < lsn.size(); j++) {
			ls.add(new ListExcelFiles(lsn.get(j), directoryDestination + File.separator + lsn.get(j)));
		}

		return ls;
	}

	@Override
	public List<InformDReestr> getResalt_D_reestrCollect2018(String date1,String date2,String user, int firrstResult)  {
		String sb;
		String g = user.equals("777")  ?  " " : " and  per.person_linksmoestablishmentid =" +user+ " " ;
		if(g == " ") {
			sb = "select rec.fam,rec.im,rec.ot, rec.dr, f_person_telephone_v2@dome_dev(rec.fam, rec.im, rec.ot, rec.dr) as tel, ADDRESS_PERSONFIO@dome_dev(rec.fam, rec.im, rec.ot, rec.dr) as address, per.person_linksmoestablishmentid as smo, nvl(per.person_serpolicy||per.person_numpolicy,pa.enp) as pol, yy.lpu, yy.ambkarta, yy.dat_beg, rec.date_end_disp, ' '||per.person_establishmentambul as mo_prik, yy.account, yy.ac_date, to_char(rec.rezobr), rec.ds1, rec.p_r_dn, yy.mes, ' '||(-1*sc.back_propagation_month) as kratnost, rec.last_treatment, pl2.plan_inform, pii.date_inform, ''||rec.mes6, rec.dat_end_mes6 " +
					"from " +
					"d_records@link_ofoms rec " +
					"left join pat yy on rec.pat_id=yy.id " +
					"left join SPR_MKB_KRATNOST@link_ofoms sc on (rec.ds1 between sc.mkb_start and sc.mkb_end) " +
					"left join (select  pl.fam,pl.im,pl.ot,pl.dr, max(pl.date_plan_info) as plan_inform from plan_pm_i_d_records@link_ofoms pl group by pl.fam,pl.im,pl.ot,pl.dr ) pl2  on (pl2.fam=rec.fam) and (pl2.im=rec.im) and (pl2.ot=rec.ot) and (pl2.dr=rec.dr) " +
					"left join  pm_assent@link_ofoms ass on ass.fam=rec.fam and ass.im=rec.im and ass.ot=rec.ot and ass.dr=rec.dr and ass.id_assent is not null " +
					"left join person@dome_dev per on per.person_surname=rec.fam and per.person_kindfirstname=rec.im and per.person_kindlastname=rec.ot and per.person_birthday=rec.dr " +
					"left join personadd@dome_dev pa on per.person_addressid = pa.personadd_addressid " +
					"left join (select pi.fam,pi.im,pi.ot,pi.dr,max(pi.d_info) as date_inform from pm_i@link_ofoms pi where pi.n_stage =5 group by pi.fam,pi.im,pi.ot,pi.dr) pii on pii.fam=rec.fam and pii.im=rec.im and pii.ot=rec.ot and pii.dr=rec.dr " +
					"where " +
					"(pl2.plan_inform not between pii.date_inform - 60 and pii.date_inform + 60 or pii.date_inform is null) " +
					g +
					" and " +
					" pl2.plan_inform between add_months((select   trunc(max(d.d_insert))  from D_RECORDS@link_ofoms d),-1) and  " +
					" (select   trunc(add_months(max(pl.d_insert),1))  from plan_pm_i_d_records@link_ofoms pl ) and rec.ds1 between sc.mkb_start and sc.mkb_end ";

		}else
		{
			sb = "select rec.fam,rec.im,rec.ot, rec.dr, f_person_telephone_v2@dome_dev(rec.fam, rec.im, rec.ot, rec.dr) as tel, ADDRESS_PERSONFIO@dome_dev(rec.fam, rec.im, rec.ot, rec.dr) as address, per.person_linksmoestablishmentid as smo, nvl(per.person_serpolicy||per.person_numpolicy,pa.enp) as pol, yy.lpu, yy.ambkarta, yy.dat_beg, rec.date_end_disp, ' '||per.person_establishmentambul as mo_prik, yy.account, yy.ac_date, to_char(rec.rezobr), rec.ds1, rec.p_r_dn, yy.mes, ' '||(-1*sc.back_propagation_month) as kratnost, rec.last_treatment, pl2.plan_inform, pii.date_inform, ''||rec.mes6, rec.dat_end_mes6 " +
					"from " +
					"d_records@link_ofoms rec " +
					"left join pat yy on rec.pat_id=yy.id " +
					"left join SPR_MKB_KRATNOST@link_ofoms sc on (rec.ds1 between sc.mkb_start and sc.mkb_end) " +
					"left join (select  pl.fam,pl.im,pl.ot,pl.dr, max(pl.date_plan_info) as plan_inform from plan_pm_i_d_records@link_ofoms pl group by pl.fam,pl.im,pl.ot,pl.dr ) pl2  on (pl2.fam=rec.fam) and (pl2.im=rec.im) and (pl2.ot=rec.ot) and (pl2.dr=rec.dr) " +
					"left join  pm_assent@link_ofoms ass on ass.fam=rec.fam and ass.im=rec.im and ass.ot=rec.ot and ass.dr=rec.dr and ass.id_assent is not null " +
					"left join person@dome_dev per on per.person_surname=rec.fam and per.person_kindfirstname=rec.im and per.person_kindlastname=rec.ot and per.person_birthday=rec.dr " +
					"left join personadd@dome_dev pa on per.person_addressid = pa.personadd_addressid " +
					"left join (select pi.fam,pi.im,pi.ot,pi.dr,max(pi.d_info) as date_inform from pm_i@link_ofoms pi where pi.n_stage =5 group by pi.fam,pi.im,pi.ot,pi.dr) pii on pii.fam=rec.fam and pii.im=rec.im and pii.ot=rec.ot and pii.dr=rec.dr " +
					"where " +
					"(pl2.plan_inform not between pii.date_inform - 60 and pii.date_inform + 60 or pii.date_inform is null) " +
					g +
					" and " +
					" pl2.plan_inform between add_months((select   trunc(max(d.d_insert))  from D_RECORDS@link_ofoms d),-1) and  " +
					" (select   trunc(add_months(max(pl.d_insert),1))  from plan_pm_i_d_records@link_ofoms pl ) and rec.p_r_dn in (1,2) and rec.ds1 between sc.mkb_start and sc.mkb_end ";
		}
		// TODO сделать выбор базы на сайте
		Query q = non_mur_collect2019.createNativeQuery(sb);

		q.setFirstResult(firrstResult);
		q.setMaxResults(60_000);
		List<Object[]> res = q.getResultList();
		// for processed data
		List<InformDReestr> ls = new ArrayList<InformDReestr>(res.size());

		res.stream().forEach((record) -> {

			try {
				String _0 = (String) record[0];
				String _1 = (String) record[1];
				String _2 = (String) record[2];
				Date _3 = (Date) record[3];
				String _tel = (String) record[4];
				String _5 = (String) record[5];
				Long _6 = ((BigDecimal) record[6]).longValue();
				String _7 = (String) record[7];
				Long _8 = (Long) record[8];
				String _9 = (String) record[9];
				Date _10 = (Date) record[10];
				Date _11 = (Date) record[11];
				String _12 = (String) record[12];
				Long _13 = ((BigDecimal) record[13]).longValue();
				Date _14 = (Date) record[14];
				String _15 = (String) record[15];
				String _16 = (String) record[16];
				Long _17 = ((BigDecimal) record[17]).longValue();
				String _18 = (String) record[18];
				String _19 = (String) record[19];
				Date _20 = (Date) record[20];
				Date _21 = (Date) record[21];
				Date _22 = (Date) record[22];
				String _23 = (String) record[23];
				Date _24 = (Date) record[24];
				//Long _21 = ((BigDecimal) record[21]).longValue(); Когда-нибудь понадобится

				ls.add(new InformDReestr(_0, _1, _2, _3, _tel, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16, _17, _18, _19, _20, _21, _22, _23, _24));
			}catch (NullPointerException e){

			}
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
