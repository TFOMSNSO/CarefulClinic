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


    //история по таблице 4
    List<DISP_TABLE4_UPDATE> getHistoryTable4(String days);
    List<DISP_TABLE4> getHistoryInsertTable4(String days);
    List<DISP_TABLE4_HISTORY> getHistoryDeleteTable4(String days);

    //история по таблице 1
    List<DISP_TABLE1> getHistoryInsertTable1(String days);
    List<DISP_TABLE1_UPDATE> getHistoryUpdateTable1(String days);
    List<DISP_TABLE1_HISTORY> getHistoryDeleteTable1(String days);


}
