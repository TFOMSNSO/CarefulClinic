package com.careful.clinic.dao.zno;

import com.careful.clinic.model.Person;
import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.ZNO_PERSON;

import javax.ejb.Stateless;
import javax.persistence.*;
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
    @PersistenceContext(unitName = "testunit")
    private EntityManager oracletestem;

    public Collection<?> getInfoZNO(PersonModel personmodel) throws ParseException {
        System.out.println("getInfoZAAAAAAAAAAAAAO");
        System.out.println(personmodel);
        TypedQuery<Person> query = em_developer.createNamedQuery("Person.findZno", Person.class)

                .setParameter("personSurname", personmodel.getSurname().toUpperCase())
                .setParameter("personKindfirstname", personmodel.getFirstname().toUpperCase())
                .setParameter("personKindlastname", personmodel.getLastname().toUpperCase())
                .setParameter("personBirthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));

        List<?> ls = query.getResultList();

        TypedQuery<ZNO_PERSON> qr = oracletestem.createNamedQuery("personzno.findbyid1",ZNO_PERSON.class);



        List<?> lq = qr.getResultList();
        if(lq.size() > 0)
        for(int i =0; i < 1; i++)
            System.out.println(lq.get(i));

        return ls;
    }
}
