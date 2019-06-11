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
        System.out.print("znoDAOBean.java:getInfoZNO:");
        TypedQuery<ZNO_PERSON> qr = oracletestem.createNamedQuery("personzno.findbyname",ZNO_PERSON.class)
                .setParameter("personFirstname",personmodel.getFirstname().toUpperCase())
                .setParameter("personSurname",personmodel.getSurname().toUpperCase())
                .setParameter("personLastname",personmodel.getLastname().toUpperCase())
                .setParameter("birthday",new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));

        List<?> lq = qr.getResultList();
        if(!lq.isEmpty())
            System.out.println(lq.get(0));

        return lq;
    }
}
