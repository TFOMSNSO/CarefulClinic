package com.careful.clinic.dao.zno;

import com.careful.clinic.model.*;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.enterprise.inject.Typed;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Stateless
public class znoDAOBean implements znoDAO{

    @PersistenceContext(unitName = "testunit")
    private EntityManager oracletestem;

    @PersistenceContext(unitName = "MEDEXP")
    private EntityManager medExp;

    public Collection<?> getInfoZNO(PersonSmoModel personmodel) throws ParseException {
        System.out.println("znoDAOBean.java:getInfoZNO:lulcs" + personmodel);
        String tempSmo = personmodel.getSmo().trim();
        TypedQuery<ZNO_PERSON> qr;

        if(tempSmo.equals("777")) {
            qr = oracletestem.createNamedQuery("personzno.findbyname", ZNO_PERSON.class)
                    .setParameter("personFirstname", personmodel.getFirstname().toUpperCase().trim())
                    .setParameter("personSurname", personmodel.getSurname().toUpperCase().trim())
                    .setParameter("personLastname", personmodel.getLastname().toUpperCase().trim())
                    .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));
        }else{
             qr = oracletestem.createNamedQuery("personzno.findbynamesmo", ZNO_PERSON.class)
                    .setParameter("personFirstname", personmodel.getFirstname().toUpperCase().trim())
                    .setParameter("personSurname", personmodel.getSurname().toUpperCase().trim())
                    .setParameter("personLastname", personmodel.getLastname().toUpperCase().trim())
                    .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()))
                    .setParameter("smo", tempSmo);
        }




        List<?> lq = qr.getResultList();


        return lq;
    }

    @Override
    public Collection<?>  getTreatmentById(String id) {
        System.out.println("Id:" + id);
        TypedQuery<ZNO_TREATMENT> tr = oracletestem.createNamedQuery("treatmentZno.findbyid1",ZNO_TREATMENT.class)
                .setParameter("p_id",id.trim().replaceAll("\"",""));

        List<ZNO_TREATMENT> ll = tr.getResultList();
        return ll;
    }

    @Override
    public Expertise getExpertiseById(PersonExpModel person) throws ParseException {
        System.out.println( "person:" + person);
        Expertise e = null;
        TypedQuery<Expertise> tq = medExp.createNamedQuery("Expertise.findex",Expertise.class)
                .setParameter("personSurname",person.getSurname().toUpperCase().trim())
                .setParameter("personName",person.getFirstname().toUpperCase().trim())
                .setParameter("personLastname",person.getLastname().toUpperCase().trim())
                .setParameter("birthday", new SimpleDateFormat("dd.MM.yyyy").parse(person.getBithday()))
                .setParameter("begintreat", new SimpleDateFormat("dd.MM.yyyy").parse(person.getDateBegin()))
                .setParameter("endtreat", new SimpleDateFormat("dd.MM.yyyy").parse(person.getDateEnd()));

        try {
            e = tq.getSingleResult();
            System.out.println(e);
            return e;
        }catch(NonUniqueResultException ex){
            return e;
        }catch(NoResultException ex){
            System.out.println("No expertise found for  " + person);
            return null;
        }
    }
}
