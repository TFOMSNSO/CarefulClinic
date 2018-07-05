SELECT XMLRoot(XMLElement("MR_OB"

                 , XMLElement("ZGLV"
                             , XMLElement("VERSION", '1.0'), XMLElement("DATA", trunc(sysdate)), XMLElement("FILENAME", 'RS54180004'), XMLElement("FIRSTNAME",null)
                             
                              )
                   , XMLElement("SVD"
                             , XMLElement("CODE", 1), XMLElement("YEAR", 2018), XMLElement("MONTH", '04')
                                                         
                              )

                    , XMLElement("PODR"

                               ,(select XMLAgg( XMLElement
                               ("ZAP"
                              
                                 , XMLElement("N_ZAP", rownum), 
                                 XMLElement("PACIENT", 
                                   XMLElement("DR", tab.dr)
                                           ),
                                 XMLElement("SLUCH"
                                   , XMLElement("DATE_1", tab.dat_beg), 
                                   XMLElement("DS1", tab.mkb), 
                                   XMLElement("RSLT",rslt ), 
                                   XMLElement("GOSP_TYPE", gosp_type), 
                                   XMLElement("PVT", PVT), 
                                   XMLElement("AP_TYPE", AP_TYPE)
                                           )
                                           ,   
                                           
                                ( select XMLAgg( XMLElement("EKMP"
                                   , 
                                  case when def = 1 then 
                                  (
                                  select XMLAgg( XMLElement("PROBLEM",defect))
                                  from
                                  (select distinct medexpert.name_def(defect_Id) || '.' defect from 
                                  medexpert.me_medexp_defect d , pat_exp_ekmp pp 
                                  where pp.id = t.id and pp.type_exp = t.type_exp and pp.exp_id = d.medexpr_id)
                                  )
                                  else XMLElement("PROBLEM",null) end
                                   , 
                                   XMLElement("TYPE", case when type_exp = 2 then 0 else 1 end), 
                                   XMLElement("NO_PROBLEM", case when def = 1 then null else 1 end ) 
                                  
                                         )   ) 
                                          from ( select id, type_exp, def from pat_exp_ekmp p where cod_exp = 2 AND type_exp = 2
                                           and p.id = tab.id
                                           group by id , type_exp , def ) t                          
                                  )
                                 )
                               )  
                               
                               from  ( 
                               -- поликлиника
                               select DISTINCT P.ID,  p.dr,p.dat_beg, mkb,
                              
                                
                               Case  to_number(REZOBR)
        when 1 then 301
        when 2 then 301
        when 3 then 301
        when 4 then 301
        when 8  then 301
        when 11 then 313
        else
        301
        End  RSLT
                              ,
                              null GOSP_TYPE,
                              0 PVT,
                              CASE WHEN N.F10 > 0 THEN  'О' ELSE 'П' END AP_TYPE
                              
                                            from  pat p,  collect2018.SPR_MKB_PRIKAZ_104 s , PAT_NEOTL N , demand d where id_demand = p.demand_id and period = 201803
                                        
                                             --rezobr in (11,405,406,411) AND 
                                        and    N.ID = P.ID 
                                           and upper(trim(p.mkb)) between s.mkb1 and s.mkb2 
                                           and caretype  in (2,37,38,3)
                                           and (substr(mes,1,1) in ('1','2','5','9')) and mes not in ('241001','241002','242004','242005')
                                          -- and months_between(dat_beg,dr) between 1*12 and 18*12 -0.0001
                                          and months_between(dat_beg,dr) between 18*12 and 120*12
                                           
                                          --  and dat_end between '01.03.2018' and '31.03.2018'
                                          --  and p.id not in (select id_pred from otkl_id)
                                         and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)  
                                           
                                          union
                                        -------------------- скорая ----------------------------------------  
                                         select DISTINCT P.ID,  p.dr,p.dat_beg,mkb,
                                         to_number(p.rezobr) RSLT,
                              null GOSP_TYPE,
                              0 PVT,
                                 'П' AP_TYPE
                              
                                            from  pat p,  collect2018.SPR_MKB_PRIKAZ_104 s , demand d where id_demand = p.demand_id and period = 201803
                                            --rezobr in (11,405,406,411)   
                                           --and 
                                         and  upper(trim(p.mkb)) between s.mkb1 and s.mkb2 
                                           and caretype  in (11,33 )
                                            and months_between(dat_beg,dr) between 18*12 and 120*12 
                                           -- and dat_end between '01.03.2018' and '31.03.2018'
                                            --and id not in (select id_pred from otkl_id) 
                                          and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)   
                                        union
                                        --------------------- дневной стационар ---------------------------------------  
                                         select DISTINCT P.ID,  p.dr,p.dat_beg,mkb,
                                          Case  to_number(REZOBR)
        when 1 then 201
        when 2 then 201
        when 3 then 201
        when 4 then 203
        when 8  then 202
        when 11 then 205
        else
        201
        End
 RSLT,
 
 case form_pom
 when 1 then 4
 when 2 then 5
 when 3 then 3
 end
 /*
 3	плановая 
4	экстренная 
5	неотложная

 */
  GOSP_TYPE,
                             case
     when dat_end >= '01.01.2018' then

          case when not (exists (select 1 from collect2018.SPR_GER_TABLE2 s where  s.ksg = p.ksg)) then

          case
          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg -28 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and (not ( exists (select 1 from collect2018.SPR_GER_TABLE2 s where  s.ksg = pp.ksg)  ))  ) then 1

          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg - 90 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and not ( exists (select 1 from collect2018.SPR_GER_TABLE2 s where  s.ksg = pp.ksg)  )) then 2

          else 0 end
          else 0 end



     when dat_end >='01.01.2017' then
          case when not (exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(p.mkb)) = mkb and ksg = p.ksg and ds = 1)
          or exists (select 1 from SPR_KSG_KURSOVOE_LECH k where upper(trim(p.nomkid)) = k.oper and ksg = p.ksg and ds = 1)) then

          case
          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg - 28 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and not (exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(pp.mkb)) = mkb and ksg = pp.ksg and ds = 1)
          or exists (select 1 from SPR_KSG_KURSOVOE_LECH k where upper(trim(pp.nomkid)) = k.oper and ksg = pp.ksg and ds = 1))  ) then 1

          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg - 90 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and not (exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(pp.mkb)) = mkb and ksg = pp.ksg and ds = 1)
          or exists (select 1 from SPR_KSG_KURSOVOE_LECH k where upper(trim(pp.nomkid)) = k.oper and ksg = pp.ksg and ds = 1))) then 2

          else 0 end
          else 0 end
          end PVT,
                                null AP_TYPE
                              
                                            from  pat p,  collect2018.SPR_MKB_PRIKAZ_104 s , demand d where id_demand = p.demand_id and period = 201803 and
                                            --rezobr in (11,405,406,411)   
                                           --and
                                            upper(trim(p.mkb)) between s.mkb1 and s.mkb2 
                                           and caretype  in (4 )
                                           -- and dat_end between '01.03.2018' and '31.03.2018'
                                          --  and id not in (select id_pred from otkl_id)      
                                         and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)   
                                          and months_between(dat_beg,dr) between 18*12 and 120*12 
                                         ------------------------------------------------------
                                         --------------------стаыионар -------------------------
                                         union
                                         
                                        
                                     
                                     
                                     select distinct hc.id ,birthday,
                                     arrival_d, upper(trim(ds_final)),
                                     case  effect
when 1 then 101
when 2 then 105
when 3 then 102
when 13 then 103
when 14 then 104
when 16 then 106
when 17 then 107
when 18 then 108
when 19 then 109
when 20 then 110
else
101
end
 RSLT,
 case when caretype = 10 then null else  
 --1 - экстренная, 2 - неотложная, 3 - плановая
 case form_pom
 when 1 then 4
 when 2 then 5
 when 3 then 3
 end
  --0	плановая 
 --1	экстренная 
 --2	неотложная
--  hosp_extr -1 
  
  end gosp_type,
 case
when hc.leave_d >= '01.01.2018' then
      case when not ( exists (select 1 from collect2018.SPR_GER_TABLE1 t where  t.ksg = hc.ksg_id )) then
     case
     when exists (select 1 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 28 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not (exists (select 1 from collect2018.SPR_GER_TABLE1 t where  t.ksg = hc2.ksg_id ) )) then 1

     when exists (select 2 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 90 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not ( exists (select 1 from collect2018.SPR_GER_TABLE1 t where  t.ksg = hc2.ksg_id ) )) then 2

     else 0 end

     else 0 end

when hc.leave_d >= '01.01.2017' then



     case when not ((ksg_id in ('147','148','149','306') ) or exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(hc.ds_final)) = mkb and ksg = hc.ksg_id and ds = 0)) then
     case
     when exists (select 1 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 28 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not ((hc2.ksg_id in ('147','148','149','306') )  or exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(hc2.ds_final)) = mkb and ksg = hc2.ksg_id and ds = 0) )) then 1
     when exists (select 2 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 90 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not ((hc2.ksg_id in ('147','148','149','306') )  or exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(hc2.ds_final)) = mkb and ksg = hc2.ksg_id and ds= 0) )) then 2

     else 0 end

     else 0 end
     else 0 end
      PVT,
         case when caretype = 10 then 'П' else  null end AP_TYPE
                                      
    from  hospcard hc,  collect2018.SPR_MKB_PRIKAZ_104 s, patinfo pi , demand d where id_demand = hc.demand_id and period = 201803 and
  -- where    -- (effect in (2,16)  or result=5) and 
   pi.id = hc.id
   and upper(trim(ds_final)) between mkb1 and mkb2
   
  -- and leave_d between '01.03.2018' and '31.03.2018'
  and hc.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2) 
    and months_between(arrival_d,birthday) between 18*12 and 120*12                                     
                                         
                                         
                                         
                                         --- ДЕТИ ДЕТИ ДЕТИ ------------------------------------
union

                               -- поликлиника
                               select DISTINCT P.ID,  p.dr,p.dat_beg, mkb,
                              
                                
                               Case  to_number(REZOBR)
        when 1 then 301
        when 2 then 301
        when 3 then 301
        when 4 then 301
        when 8  then 301
        when 11 then 313
        else
        301
        End  RSLT
                              ,
                              null GOSP_TYPE,
                              0 PVT,
                              CASE WHEN N.F10 > 0 THEN  'О' ELSE 'П' END AP_TYPE
                              
                                            from  pat p,  collect2018.SPR_MKB_PRIKAZ_104_Deti s , PAT_NEOTL N , demand d where id_demand = p.demand_id and period = 201803
                                        
                                             --rezobr in (11,405,406,411) AND 
                                        and    N.ID = P.ID 
                                           and upper(trim(p.mkb)) between s.mkb1 and s.mkb2 
                                           and caretype  in (2,37,38,3)
                                           and (substr(mes,1,1) in ('1','2','5','9')) and mes not in ('241001','241002','242004','242005')
                                          -- and months_between(dat_beg,dr) between 1*12 and 18*12 -0.0001
                                          and months_between(dat_beg,dr) between 1*12 and 18*12 - 0.0001 
                                          --  and dat_end between '01.03.2018' and '31.03.2018'
                                          --  and p.id not in (select id_pred from otkl_id)
                                          and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)  
                                           
                                          union
                                        -------------------- скорая ----------------------------------------  
                                         select DISTINCT P.ID,  p.dr,p.dat_beg,mkb,
                                         to_number(p.rezobr) RSLT,
                              null GOSP_TYPE,
                              0 PVT,
                                 'П' AP_TYPE
                              
                                            from  pat p,  collect2018.SPR_MKB_PRIKAZ_104_Deti s , demand d where id_demand = p.demand_id and period = 201803
                                            --rezobr in (11,405,406,411)   
                                           --and 
                                         and  upper(trim(p.mkb)) between s.mkb1 and s.mkb2 
                                           and caretype  in (11,33 )
                                            and months_between(dat_beg,dr) between 1*12 and 18*12 - 0.0001  
                                           -- and dat_end between '01.03.2018' and '31.03.2018'
                                            --and id not in (select id_pred from otkl_id) 
                                           and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)   
                                        union
                                        --------------------- дневной стационар ---------------------------------------  
                                         select DISTINCT P.ID,  p.dr,p.dat_beg,mkb,
                                          Case  to_number(REZOBR)
        when 1 then 201
        when 2 then 201
        when 3 then 201
        when 4 then 203
        when 8  then 202
        when 11 then 205
        else
        201
        End
 RSLT,
                                
 case form_pom
 when 1 then 4
 when 2 then 5
 when 3 then 3
 end
 
                               
                               GOSP_TYPE,
                             case
     when dat_end >= '01.01.2018' then

          case when not (exists (select 1 from collect2018.SPR_GER_TABLE2 s where  s.ksg = p.ksg)) then

          case
          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg -28 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and (not ( exists (select 1 from collect2018.SPR_GER_TABLE2 s where  s.ksg = pp.ksg)  ))  ) then 1

          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg - 90 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and not ( exists (select 1 from collect2018.SPR_GER_TABLE2 s where  s.ksg = pp.ksg)  )) then 2

          else 0 end
          else 0 end



     when dat_end >='01.01.2017' then
          case when not (exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(p.mkb)) = mkb and ksg = p.ksg and ds = 1)
          or exists (select 1 from SPR_KSG_KURSOVOE_LECH k where upper(trim(p.nomkid)) = k.oper and ksg = p.ksg and ds = 1)) then

          case
          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg - 28 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and not (exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(pp.mkb)) = mkb and ksg = pp.ksg and ds = 1)
          or exists (select 1 from SPR_KSG_KURSOVOE_LECH k where upper(trim(pp.nomkid)) = k.oper and ksg = pp.ksg and ds = 1))  ) then 1

          when exists (select 1 from pat pp where
          pp.id  <> p.id and pp.dr = p.dr and pp.caretype = 4 and pp.ksg <> '0' and  upper (trim(p.fio)) =  upper(trim(pp.fio))
          and pp.dat_end between p.dat_beg - 90 and p.dat_end and upper(trim(p.mkb)) = upper(trim(pp.mkb))
          and not (exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(pp.mkb)) = mkb and ksg = pp.ksg and ds = 1)
          or exists (select 1 from SPR_KSG_KURSOVOE_LECH k where upper(trim(pp.nomkid)) = k.oper and ksg = pp.ksg and ds = 1))) then 2

          else 0 end
          else 0 end
          end PVT,
                                null AP_TYPE
                              
                                           from  pat p,  collect2018.SPR_MKB_PRIKAZ_104_Deti s , demand d where id_demand = p.demand_id and period = 201803 and
                                            --rezobr in (11,405,406,411)   
                                           --and
                                           upper(trim(p.mkb)) between s.mkb1 and s.mkb2 
                                           and caretype  in (4 )
                                           
                                           -- and dat_end between '01.03.2018' and '31.03.2018'
                                          --  and id not in (select id_pred from otkl_id)      
                                         and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)   
                                          and months_between(dat_beg,dr) between 1*12 and 18*12 - 0.0001 
                                         ------------------------------------------------------
                                         --------------------стаыионар -------------------------
                                         union
                                         
                                        
                                     
                                     
                                     select distinct hc.id ,birthday,
                                     arrival_d, upper(trim(ds_final)),
                                     case  effect
when 1 then 101
when 2 then 105
when 3 then 102
when 13 then 103
when 14 then 104
when 16 then 106
when 17 then 107
when 18 then 108
when 19 then 109
when 20 then 110
else
101
end
 RSLT,
 case when caretype = 10 then null else  
 case form_pom
 when 1 then 4
 when 2 then 5
 when 3 then 3
 end
 
 --hosp_extr -1 
 end
  gosp_type,
 case
when hc.leave_d >= '01.01.2018' then
      case when not ( exists (select 1 from collect2018.SPR_GER_TABLE1 t where  t.ksg = hc.ksg_id )) then
     case
     when exists (select 1 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 28 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not (exists (select 1 from collect2018.SPR_GER_TABLE1 t where  t.ksg = hc2.ksg_id ) )) then 1

     when exists (select 2 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 90 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not ( exists (select 1 from collect2018.SPR_GER_TABLE1 t where  t.ksg = hc2.ksg_id ) )) then 2

     else 0 end

     else 0 end

when hc.leave_d >= '01.01.2017' then



     case when not ((ksg_id in ('147','148','149','306') ) or exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(hc.ds_final)) = mkb and ksg = hc.ksg_id and ds = 0)) then
     case
     when exists (select 1 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 28 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not ((hc2.ksg_id in ('147','148','149','306') )  or exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(hc2.ds_final)) = mkb and ksg = hc2.ksg_id and ds = 0) )) then 1
     when exists (select 2 from hospcard hc2 ,patinfo pi2 where hc2.id = pi2.id
     and pi2.id  <> hc.id and pi2.birthday = pi.birthday and  (trim(pi.surname)) =  (trim(pi2.surname))
     and  (trim(pi.first_name)) =  (trim(pi2.first_name)) and  (trim(pi.sec_name)) =  (trim(pi2.sec_name))
     and hc2.leave_d between hc.arrival_d - 90 and hc.arrival_d and upper(trim(hc.ds_final)) = upper(trim(hc2.ds_final))

     and not ((hc2.ksg_id in ('147','148','149','306') )  or exists (select 1 from SPR_KSG_KURSOVOE_LECH where upper(trim(hc2.ds_final)) = mkb and ksg = hc2.ksg_id and ds= 0) )) then 2

     else 0 end

     else 0 end
     else 0 end
      PVT,
    case when caretype = 10 then 'П' else  null end AP_TYPE
                                      
    from  hospcard hc,  collect2018.SPR_MKB_PRIKAZ_104_Deti s, patinfo pi , demand d where id_demand = hc.demand_id and period = 201803 and
  -- where    -- (effect in (2,16)  or result=5) and 
   pi.id = hc.id
   and upper(trim(ds_final)) between mkb1 and mkb2
  -- and leave_d between '01.03.2018' and '31.03.2018'
   and hc.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2) 
    and months_between(arrival_d,birthday) between 1*12 and 18*12 - 0.0001                                           
    union
    
select DISTINCT  ID,  to_date(p.dr,'ddmmyyyy'), p.dat_1, ds,
                              
                                
                      RSLT,
                            case when q_u in (1,3) then 
                                
 case for_pom
 when 1 then 4
 when 2 then 5
 when 3 then 3
 end
                            else 
                              null 
                            end
                              GOSP_TYPE,
                              0 PVT,
                              case when q_u in (1,3) then 
                                 null
                            else 
                             CASE WHEN kd_pos > 1 THEN  'О' ELSE 'П' END 
                            end
                            
                               AP_TYPE
                              
                                            from  MTR_MAIN_TABLE2017_2018_04@dome_ggv p,  collect2018.SPR_MKB_PRIKAZ_104_Deti s  where
                                            
                                            to_char (add_Months(data_s, -1),'yyyymm' ) = 201803

                                           and
                                            upper(trim(p.ds)) between s.mkb1 and s.mkb2 
                                             and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)  
                                          -- and months_between(dat_beg,dr) between 1*12 and 18*12 -0.0001
                                          and months_between(dat_1, to_date(p.dr,'ddmmyyyy')) between 1*12 and 18*12 - 0.0001 
                                          and 
                                          (
                                          q_u in (1,3,4)
                                          or
                                          (q_u in (2) and (substr(mes,1,1) in ('1','2','5','9')) and mes not in ('241001','241002','242004','242005'))
                                          )
                    union                      

select DISTINCT  ID,  to_date(p.dr,'ddmmyyyy'), p.dat_1, ds,
                              
                                
                      RSLT,
                            case when q_u in (1,3) then 
 case for_pom
 when 1 then 4
 when 2 then 5
 when 3 then 3
 end
                            else 
                              null 
                            end
                              GOSP_TYPE,
                              0 PVT,
                              case when q_u in (1,3) then 
                                 null
                            else 
                             CASE WHEN kd_pos > 1 THEN  'О' ELSE 'П' END 
                            end
                            
                               AP_TYPE
                              
                                            from  MTR_MAIN_TABLE2017_2018_04@dome_ggv p,  collect2018.SPR_MKB_PRIKAZ_104 s  where
                                            
                                            to_char (add_Months(data_s, -1),'yyyymm' ) = 201803

                                           and
                                            upper(trim(p.ds)) between s.mkb1 and s.mkb2 
                                             and p.id in (select id from pat_exp_ekmp e where cod_exp = 2 and e.type_exp = 2)  
                                          -- and months_between(dat_beg,dr) between 1*12 and 18*12 -0.0001
                                          and months_between(dat_1, to_date(p.dr,'ddmmyyyy')) between 18*12 and 120*12 
                                          and 
                                          (
                                          q_u in (1,3,4)
                                          or
                                          (q_u in (2) and (substr(mes,1,1) in ('1','2','5','9')) and mes not in ('241001','241002','242004','242005'))
                                          )                                          
    
                                         
                                         
                                         
                                         ) tab
                                         
                                         
                              ) )
                                 
                )
, version '1.0" encoding="windows-1251')  from dual                

 
 

