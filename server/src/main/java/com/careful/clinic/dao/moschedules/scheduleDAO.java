package com.careful.clinic.dao.moschedules;


import com.careful.clinic.model.schedulemodels.DISP_TABLE1;
import com.careful.clinic.model.schedulemodels.DISP_TABLE4;

import javax.ejb.Local;
import java.util.List;

@Local
public interface scheduleDAO {

    List<DISP_TABLE1> getAllTable1();
    List<DISP_TABLE4> getAllTable4();
}
