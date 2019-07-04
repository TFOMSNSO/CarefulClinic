package com.careful.clinic.rs.schedule;


import com.careful.clinic.dao.moschedules.scheduleDAO;
import com.careful.clinic.model.ResponseDescription;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1;
import com.careful.clinic.model.schedulemodels.table1.DISP_TABLE1_V2;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2;
import com.careful.clinic.model.schedulemodels.table2.DISP_TABLE2_V2;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3;
import com.careful.clinic.model.schedulemodels.table3.DISP_TABLE3_V2;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4;
import com.careful.clinic.model.schedulemodels.table4.DISP_TABLE4_V2;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5;
import com.careful.clinic.model.schedulemodels.table5.DISP_TABLE5_V2;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6;
import com.careful.clinic.model.schedulemodels.table6.DISP_TABLE6_V2;

import javax.ejb.EJB;
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
    @Path("/export_table_disp1")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDescription exportTable1(List<DISP_TABLE1_V2> data){
        return schedule.exportTable1(data);
    }

    @POST
    @Path("/export_table_disp2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDescription exportTable2(List<DISP_TABLE2_V2> data){
        return schedule.exportTable2(data);
    }

    @POST
    @Path("/export_table_disp3")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDescription exportTable3(List<DISP_TABLE3_V2> data){
        return schedule.exportTable3(data);
    }

    @POST
    @Path("/export_table_disp4")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDescription exportTable4(List<DISP_TABLE4_V2> data){
        return schedule.exportTable4(data);
    }

    @POST
    @Path("/export_table_disp5")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDescription exportTable5(List<DISP_TABLE5_V2> data){
        return schedule.exportTable5(data);
    }


    @POST
    @Path("/export_table_disp6")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDescription exportTable6(List<DISP_TABLE6_V2> data){
        return schedule.exportTable6(data);
    }
}
