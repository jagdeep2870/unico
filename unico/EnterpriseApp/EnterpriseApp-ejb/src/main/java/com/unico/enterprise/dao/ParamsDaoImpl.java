package com.unico.enterprise.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.unico.enterprise.domain.Params;

@Stateless
public class ParamsDaoImpl implements ParamsDao {

	@PersistenceContext(unitName = "unico.enterprise.mysql")
	EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveParams(Integer param1, Integer param2) throws DaoException {
		Params params = new Params();
		params.setParam1(param1);
		params.setParam2(param2);
		
		try {
			em.persist(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} 
	}


	@Override
	public Collection<Params> getSavedParamsList() {
		 Query query = em.createQuery("SELECT p FROM Params p");
		 return (Collection<Params>) query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
