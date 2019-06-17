package com.careful.clinic.dao.zno;

import com.careful.clinic.model.*;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.Collection;


@Local
public interface znoDAO {
    Collection<?> getInfoZNO(PersonSmoModel personmodel) throws ParseException;
    Collection<?>  getTreatmentById(String id);
    Expertise getExpertiseById(PersonExpModel e) throws ParseException;
}
