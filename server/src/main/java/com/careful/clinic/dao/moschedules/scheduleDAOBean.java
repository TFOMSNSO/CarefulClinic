package com.careful.clinic.dao.moschedules;

import com.careful.clinic.model.ResponseDescription;
import com.careful.clinic.model.schedulemodels.table1.*;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2_V2;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3_V2;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4_HISTORY;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4_UPDATE;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4_V2;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5_V2;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6_V2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Stateless
public class scheduleDAOBean implements scheduleDAO {
    //private String days[] = {"Понедельник","Вторник","Среда","Четверг","Пятница","Суббота","Воскресенье"};


    @PersistenceContext(unitName = "WebOfoms")
    EntityManager webofoms;
    /*TODO: сделать из getAllTable1-6 один метод для всех таблиц.убрать exporttable...*/

    @Override
    public List<DISP_TABLE1> getAllTable1() {
        TypedQuery<DISP_TABLE1> tq = webofoms.createNamedQuery("findAllt1",DISP_TABLE1.class);

        List<DISP_TABLE1> list = tq.getResultList();
        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }

    @Override
    public List<DISP_TABLE2> getAllTable2() {
        TypedQuery<DISP_TABLE2> tq = webofoms.createNamedQuery("findAllt2",DISP_TABLE2.class);

        List<DISP_TABLE2> list = tq.getResultList();
        if(!list.isEmpty()){
            for(int i =0; i < list.size(); i++){
                if(list.get(i).getLpuId().trim().equals("143")){
                    System.out.println(list.get(i));
                }
            }
        }
        return list;
    }

    @Override
    public List<DISP_TABLE3> getAllTable3() {
        TypedQuery<DISP_TABLE3> tq = webofoms.createNamedQuery("findAllt3",DISP_TABLE3.class);

        List<DISP_TABLE3> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }

    @Override
    public List<DISP_TABLE4> getAllTable4() {
        TypedQuery<DISP_TABLE4> tq = webofoms.createNamedQuery("findAllt4",DISP_TABLE4.class);

        List<DISP_TABLE4> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }

    @Override
    public List<DISP_TABLE5> getAllTable5() {
        TypedQuery<DISP_TABLE5> tq = webofoms.createNamedQuery("findAllt5",DISP_TABLE5.class);

        List<DISP_TABLE5> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE5 all:" + list.size());
        }
        return list;
    }

    @Override
    public List<DISP_TABLE6> getAllTable6() {
        TypedQuery<DISP_TABLE6> tq = webofoms.createNamedQuery("findAllt6",DISP_TABLE6.class);

        List<DISP_TABLE6> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE6 all:" + list.size());
        }
        return list;
    }


    @Override
    public List<DISP_TABLE4> getHistoryInsertTable4(String days) {
        TypedQuery<DISP_TABLE4> tq = webofoms.createNamedQuery("findByDayst4",DISP_TABLE4.class)
                .setParameter("days",days);

        List<DISP_TABLE4> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE4_insert days all:" + list.size());
        }

        return list;
    }

    @Override
    public List<DISP_TABLE4_UPDATE> getHistoryTable4(String days) {
        TypedQuery<DISP_TABLE4_UPDATE> tq = webofoms.createNamedQuery("t4updateDays",DISP_TABLE4_UPDATE.class)
                .setParameter("days",days);

        System.out.print("tq" + tq);
        List<DISP_TABLE4_UPDATE> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE4_UPDATE all:" + list.size());
        }

        return list;
    }

    @Override
    public List<DISP_TABLE4_HISTORY> getHistoryDeleteTable4(String days) {
        TypedQuery<DISP_TABLE4_HISTORY> tq = webofoms.createNamedQuery("findDeleteHistoryT4",DISP_TABLE4_HISTORY.class)
                .setParameter("days",days);

        System.out.print("tq" + tq);
        List<DISP_TABLE4_HISTORY> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE4_ all:" + list.size());
        }

        return list;
    }

    @Override
    public List<DISP_TABLE1> getHistoryInsertTable1(String days) {
        TypedQuery<DISP_TABLE1> tq = webofoms.createNamedQuery("findByDayst1",DISP_TABLE1.class)
                .setParameter("days",days);

        List<DISP_TABLE1> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE4_insert days all:" + list.size());
        }

        return list;
    }

    @Override
    public List<DISP_TABLE1_UPDATE> getHistoryUpdateTable1(String days) {
        TypedQuery<DISP_TABLE1_UPDATE> tq = webofoms.createNamedQuery("findUpdateByDayst1",DISP_TABLE1_UPDATE.class)
                .setParameter("days",days);

        List<DISP_TABLE1_UPDATE> list = tq.getResultList();

        if(!list.isEmpty()){
            for(DISP_TABLE1_UPDATE x : list){
                System.out.println("\n" + x);
            }
        }

        return list;
    }

    @Override
    public List<DISP_TABLE1_HISTORY> getHistoryDeleteTable1(String days) {
        TypedQuery<DISP_TABLE1_HISTORY> tq = webofoms.createNamedQuery("findDeleteByDayst1",DISP_TABLE1_HISTORY.class)
                .setParameter("days",days);

        List<DISP_TABLE1_HISTORY> list = tq.getResultList();

        if(!list.isEmpty()){
            System.out.println("DISP_TABLE1_HISTORY days all:" + list.size());
        }

        return list;
    }

}
