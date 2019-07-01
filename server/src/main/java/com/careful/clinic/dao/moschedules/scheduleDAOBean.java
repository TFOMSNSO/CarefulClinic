package com.careful.clinic.dao.moschedules;

import com.careful.clinic.model.schedulemodels.DISP_TABLE1;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class scheduleDAOBean implements scheduleDAO {
    @PersistenceContext(unitName = "WebOfoms")
    EntityManager webofoms;


    @Override
    public List<DISP_TABLE1> getAllTable1() {
        TypedQuery<DISP_TABLE1> tq = webofoms.createNamedQuery("findAllt1",DISP_TABLE1.class);

        List<DISP_TABLE1> list = tq.getResultList();
        if(!list.isEmpty()){
            System.out.println(list.get(0));
        }
        return list;
    }
}
