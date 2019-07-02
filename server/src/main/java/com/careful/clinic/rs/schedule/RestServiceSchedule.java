package com.careful.clinic.rs.schedule;


import com.careful.clinic.dao.moschedules.scheduleDAO;
import com.careful.clinic.model.schedulemodels.DISP_TABLE1;
import com.careful.clinic.model.schedulemodels.DISP_TABLE4;

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
    @Path("/table4_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DISP_TABLE4> getAllt4(){ return schedule.getAllTable4();}
}
