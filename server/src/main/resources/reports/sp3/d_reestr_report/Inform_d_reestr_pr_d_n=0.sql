--select * from person@dome_dev pe
--select * from pm_assent@link_ofoms n




select  zz.pr_d_n,zz.mes,zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id
                                                           from (
--                                                           select n.id_assent,pe.person_linksmoestablishmentid,p.pr_d_n,p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id
                              select p.mes, p.pr_d_n, p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id 
                                                           from 
                                                           demand d, pat p 
                                                           --left join person@dome_dev pe on pe.person_surname = p.fam and pe.person_kindfirstname = p.im and pe.person_kindlastname = p.ot and pe.person_birthday = p.dr
                                                           --left join pm_assent@link_ofoms n on n.fam = p.fam and n.im = p.im and n.ot = p.ot and n.dr = p.dr 
                                                           where
                                                            d.id_demand = p.demand_id 
                                                            and  p.caretype = 30
                                                            and   p.REZOBR in (22,23) 
                                                            and  d.period between '201805' and '201808'
                                                             and  p.dat_end >= '01.01.2018'
                                                           and (((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1  between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99')))
--                                                            and  p.mes between '401048' and '401080'
                                                            and  p.mes = '401072'
                                                            and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id)
--                                                            and exists (select 1 from pat pp where pp.caretype = 30  and mes between '401048' and '401071'  and pp.fio = p.fio and pp.dr = p.dr)
                                                            ) zz
                                                            where zz.mes='401072' and zz.pr_d_n = 0 
                               union all
                               
                               select  zz.pr_d_n,zz.mes,zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id 
                                                           from 
                                                           ( 
--                                                           select n.id_assent,pe.person_linksmoestablishmentid,p.pr_d_n,p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id 
                              select p.mes,  p.pr_d_n, p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id 
                                                           from 
                                                           demand d, pat p 
--                                                           left join person@dome_dev pe on pe.person_surname = p.fam and pe.person_kindfirstname = p.im and pe.person_kindlastname = p.ot and pe.person_birthday = p.dr 
--                                                           left join pm_assent@link_ofoms n on n.fam = p.fam and n.im = p.im and n.ot = p.ot and n.dr = p.dr 
                                                           where 
                                                           d.id_demand = p.demand_id 
                                                           and  p.caretype = 30 
                                                           and   p.REZOBR in (22,23) 
                                                           and  d.period between '201801' and '201812' 
                                                            and  p.dat_end >= '01.01.2018' 
                                                           and (((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99'))) 
                                                           and  p.mes between '401048' and '401080' 
                                                           and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) 
                                                           and exists (select 1 from pat pp where pp.caretype = 30  and mes in(401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) 
                                                           ) zz 
                                                            where zz.mes between '401048' and '401071' and zz.pr_d_n = 0
                                                            
                                 union all 
                               
                               select  zz.pr_d_n,zz.mes,zz.FIO,zz.dr, zz.SMOID,zz.SERPOLIS,zz.NUMPOLIS,LPU,zz.AMBKARTA, zz.DAT_BEG,zz.DAT_END,zz.LPU_PRIK,zz.s1,zz.account,zz.AC_DATE,zz.REZOBR, zz.ds1 as f_mkb_usl, f_person_telephone_v2@dome_dev(zz.fam, zz.im, zz.ot, zz.drr) as tel, zz.id 
                                                           from ( 
--                                                           select n.id_assent,pe.person_linksmoestablishmentid,p.pr_d_n,p.mes,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id 
                              select p.mes,p.pr_d_n,p.FIO,p.dr, p.SMOID,p.SERPOLIS,p.NUMPOLIS,p.lpu,p.AMBKARTA, p.DAT_BEG,p.DAT_END,LPU_PRIK,p.s1,p.account,p.AC_DATE,p.REZOBR, p.ds1, p.fam, p.im, p.ot, p.dr as drr, p.id
                                                           from 
                                                           demand d, pat p 
--                                                           left join person@dome_dev pe on pe.person_surname = p.fam and pe.person_kindfirstname = p.im and pe.person_kindlastname = p.ot and pe.person_birthday = p.dr 
--                                                           left join pm_assent@link_ofoms n on n.fam = p.fam and n.im = p.im and n.ot = p.ot and n.dr = p.dr 
                                                           where 
                                                           d.id_demand = p.demand_id 
                                                           and  p.caretype = 30 
                                                           and   p.REZOBR in (16,22,23) 
                                                           and  d.period between '201801' and '201812' 
                                                           and  p.dat_end >= '01.01.2018' 
                                                           and (((substr(p.ds1,0,1) = 'I') or (substr(p.ds1,0,1) = 'C') or (p.ds1 between 'E10' and 'E13.99') or (p.ds1 between 'J44' and 'J47.99')))
                                                           and  p.mes between '401048' and '401080' 
                                                           and  p.id not in (select ot.id_pred from otkl_id ot where ot.id_pred = p.id) 
                                                           and not exists (select 1 from pat pp where pp.caretype = 30  and mes  in (401072,401079,401080)  and pp.fio = p.fio and pp.dr = p.dr) 
                                                           ) zz 
                                                            where zz.mes between '401048' and '401071' and zz.pr_d_n  = 0 and zz.rezobr in (16,22,23) 

--) tmp_all;
--left join pat p2 on p2.fio = tmp_all.fio and p2.dr = tmp_all.dr and p2.dat_end > tmp_all.dat_end and p2.mes like '6%';
