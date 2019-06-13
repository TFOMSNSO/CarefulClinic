package com.careful.clinic.dao.zno;

import com.careful.clinic.model.PersonModel;
import com.careful.clinic.model.ZNO_PERSON;
import com.careful.clinic.model.ZNO_TREATMENT;

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
        System.out.print("znoDAOBean.java:getInfoZNO:" + personmodel);
        TypedQuery<ZNO_PERSON> qr = oracletestem.createNamedQuery("personzno.findbyname",ZNO_PERSON.class)
                .setParameter("personFirstname",personmodel.getFirstname().toUpperCase().trim())
                .setParameter("personSurname",personmodel.getSurname().toUpperCase().trim())
                .setParameter("personLastname",personmodel.getLastname().toUpperCase().trim())
                .setParameter("birthday",new SimpleDateFormat("dd.MM.yyyy").parse(personmodel.getBithday()));

        List<?> lq = qr.getResultList();
        return lq;
    }

    @Override
    public Collection<?>  getTreatmentById(String id) {
        System.out.println("Id:" + id);
        TypedQuery<ZNO_TREATMENT> tr = oracletestem.createNamedQuery("treatmentZno.findbyid1",ZNO_TREATMENT.class)
                .setParameter("p_id",id.trim().replaceAll("\"",""));

        List<ZNO_TREATMENT> ll = tr.getResultList();
        System.out.println("ll.size:" + ll.size());

        for(ZNO_TREATMENT z:ll)
            System.out.println(z+"\n");
        return ll;
    }
}
