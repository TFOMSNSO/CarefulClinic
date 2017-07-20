package com.careful.clinic.dao.questions;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.careful.clinic.model.questions.Questions;


@Stateless
@LocalBean
public class QuestionsDAO {

	public QuestionsDAO() {
	}
	
	@PersistenceContext(unitName="primary")
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Collection<?> getAll(){
		 Query query = em.createQuery("SELECT e FROM Questions e");
		return (Collection<Questions>)query.getResultList();
	}

	
}
