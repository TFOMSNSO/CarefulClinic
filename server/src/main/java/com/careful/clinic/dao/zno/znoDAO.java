package com.careful.clinic.dao.zno;

import com.careful.clinic.model.*;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Local
public interface znoDAO {
    Collection<?> getInfoZNO(PersonSmoModel personmodel) throws ParseException;
    Collection<?>  getTreatmentById(String id);
    Collection<?> getInfoZNOKeys(SearchZnoKeysModel keysmodel);
    Collection<?> exportToExcel(List<ZNO_PERSON> arrayList) throws Exception;
    Expertise getExpertiseById(PersonExpModel e) throws ParseException;
    void writeListToFile(String fileName, List<ZNO_PERSON> arrayList) throws Exception;
}
