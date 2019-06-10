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
        System.out.println("znoDAOBean.java:getInfoZNO");
        System.out.println(personmodel);
      /*  TypedQuery<Person> query = em_developer.createNamedQuery("Person.findByFIOD", Person.class)

                .setParameter("personSurname", personmodel.getSurname().toUpperCase())
                .setParameter("personKindfirstname", personmodel.getFirstname().toUpperCase())
                .setParameter("personKindlastname", personmodel.getLastname().toUpperCase())
                .setParameter("personBirthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));

        List<?> ls = query.getResultList();*/
      //  System.out.println(ls.get(0));

        TypedQuery<ZNO_PERSON> qr = oracletestem.createNamedQuery("personzno.findbyname",ZNO_PERSON.class)
                .setParameter("personFirstname",personmodel.getFirstname().toUpperCase())
                .setParameter("personSurname",personmodel.getSurname().toUpperCase());

        List<?> lq = qr.getResultList();
        System.out.println(lq.get(0));
        return lq;
    }
}
