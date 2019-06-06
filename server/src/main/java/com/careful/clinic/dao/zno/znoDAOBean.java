package com.careful.clinic.dao.zno;

import com.careful.clinic.model.Person;
import com.careful.clinic.model.PersonModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;


@Stateless
public class znoDAOBean implements znoDAO{
    @PersistenceContext(unitName="OracleDSDeveloper")
    private EntityManager em_developer;
    @PersistenceContext(unitName="NONXASDAME")
    private EntityManager EM_NONXASDAME;



    public Collection<?> getInfoZNO(PersonModel personmodel) throws ParseException {
        System.out.println("getInfoZAAAAAAAAAAAAAO");
        System.out.println(personmodel);
        TypedQuery<Person> query = em_developer.createNamedQuery("Person.findZno", Person.class)

                .setParameter("personSurname", personmodel.getSurname().toUpperCase())
                .setParameter("personKindfirstname", personmodel.getFirstname().toUpperCase())
                .setParameter("personKindlastname", personmodel.getLastname().toUpperCase())
                .setParameter("personBirthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));

        List<?> ls = query.getResultList();
        return ls;
    }
}
