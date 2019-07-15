package com.careful.clinic.dao.moschedules;


import com.careful.clinic.model.ResponseDescription;
import com.careful.clinic.model.schedulemodels.table1.*;
import com.careful.clinic.model.schedulemodels.table2.*;
import com.careful.clinic.model.schedulemodels.table3.*;
import com.careful.clinic.model.schedulemodels.table4.*;
import com.careful.clinic.model.schedulemodels.table5.*;
import com.careful.clinic.model.schedulemodels.table6.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface scheduleDAO {
    List<DISP_TABLE1> getAllTable1();
    List<DISP_TABLE2> getAllTable2();
    List<DISP_TABLE3> getAllTable3();
    List<DISP_TABLE4> getAllTable4();
    List<DISP_TABLE5> getAllTable5();
    List<DISP_TABLE6> getAllTable6();

    List<DISP_TABLE4_UPDATE> getHistoryTable4();
    List<DISP_TABLE4_UPDATE> getHistoryTable4(String days);

    ResponseDescription exportTable1(List<DISP_TABLE1_V2> list);
    ResponseDescription exportTable2(List<DISP_TABLE2_V2> list);
    ResponseDescription exportTable3(List<DISP_TABLE3_V2> list);
    ResponseDescription exportTable4(List<DISP_TABLE4_V2> list);
    ResponseDescription exportTable5(List<DISP_TABLE5_V2> list);
    ResponseDescription exportTable6(List<DISP_TABLE6_V2> list);

}
