package com.careful.clinic.rs.schedule;


import com.careful.clinic.dao.moschedules.scheduleDAO;
import com.careful.clinic.model.ResponseDescription;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1_HISTORY;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1_UPDATE;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1_V2;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2_V2;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3_V2;
import com.careful.clinic.model.schedulemodels.table4.*;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5_V2;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6_V2;

import javax.ejb.EJB;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/mo_schedule")
public class RestServiceSchedule {
    @EJB
    scheduleDAO schedule;

    @GET
    @Path("/table1_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE1> getAllt1(){
        return schedule.getAllTable1();
    }

    @GET
    @Path("/table2_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE2> getAllt2(){
        return schedule.getAllTable2();
    }

    @GET
    @Path("/table3_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE3> getAllt3(){ return schedule.getAllTable3();}

    @GET
    @Path("/table4_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE4> getAllt4(){ return schedule.getAllTable4();}

    @GET
    @Path("/table5_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE5> getAllt5(){ return schedule.getAllTable5();}


    @GET
    @Path("/table6_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE6> getAllt6(){ return schedule.getAllTable6();}

    @POST
    @Path("table4_update_days")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE4_UPDATE> getHistoryDaysTable4(DaysBy days){
        System.out.println("days:" + days);
        return schedule.getHistoryTable4(days.getDays());
    }

    @POST
    @Path("table4_insert_days")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE4> getHistoryInsertDaysTable4(DaysBy days){
        System.out.println("days:" + days);
        return schedule.getHistoryInsertTable4(days.getDays());
    }

    @POST
    @Path("table1_insert_days")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE1> getHistoryInsertDaysTable1(DaysBy days){
        System.out.println("days:" + days);
        return schedule.getHistoryInsertTable1(days.getDays());
    }

    @POST
    @Path("table1_update_days")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE1_UPDATE> getHistoryUpdateDaysTable1(DaysBy days){
        System.out.println("days delete:" + days);
        return schedule.getHistoryUpdateTable1(days.getDays());
    }

    @POST
    @Path("table1_delete_days")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE1_HISTORY> getHistoryDeleteDaysTable1(DaysBy days){
        System.out.println("days delete:" + days);
        return schedule.getHistoryDeleteTable1(days.getDays());
    }

    @POST
    @Path("table4_delete_days")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE4_HISTORY> getHistoryDeleteDaysTable4(DaysBy days){
        System.out.println("days delete:" + days);
        return schedule.getHistoryDeleteTable4(days.getDays());
    }

}
