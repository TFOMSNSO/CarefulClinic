package com.careful.clinic.dao.zno;

import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.ZNO_TREATMENT;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.Collection;


@Local
public interface znoDAO {
    Collection<?> getInfoZNO(PersonModel personmodel) throws ParseException;
    Collection<?>  getTreatmentById(String id);
}
