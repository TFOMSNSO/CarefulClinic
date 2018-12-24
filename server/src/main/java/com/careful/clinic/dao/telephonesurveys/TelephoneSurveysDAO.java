package com.careful.clinic.dao.telephonesurveys;

import javax.ejb.Local;
import java.io.IOException;
import java.util.Collection;

@Local
public interface TelephoneSurveysDAO {
    Collection<?> getListFlk1(Integer id) throws IOException;

    Collection<?> getListFlk2(Integer id);

    Collection<?> getListFlk3(Integer id);

    Collection<?> getListFlk4(Integer id);
}
