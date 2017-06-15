package com.careful.clinic.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.careful.clinic.model.Customer;

/**
 * Session Bean implementation class CustomDao
 */
@Stateless
@LocalBean
public class CustomDao {

    /**
     * Default constructor. 
     */
    public CustomDao() {
    }
    
    @PersistenceContext
    private EntityManager em;
    
    public Customer getCustomer(int id) {
    	/*Customer c = new Customer();
    	c.setName("Pylypiv Sergey");
    	return c;*/
        return em.find(Customer.class, id);
    }

}
