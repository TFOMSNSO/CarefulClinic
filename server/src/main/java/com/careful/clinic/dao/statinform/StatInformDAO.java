package com.careful.clinic.dao.statinform;

import javax.ejb.Local;
import java.util.Collection;

@Local
public interface StatInformDAO {

    Collection<?> getListStatInform(Integer id);

    Collection<?> getListStatInformTable4(Integer id);

    Collection<?> getListStatInformTable5(Integer id);

    Collection<?> getListStatInformTable6(Integer id);
}
