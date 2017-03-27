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
//import javax.transaction.HeuristicMixedException;
//import javax.transaction.HeuristicRollbackException;
//import javax.transaction.NotSupportedException;
//import javax.transaction.RollbackException;
//import javax.transaction.SystemException;
//import javax.transaction.UserTransaction;

import com.unico.enterprise.domain.GCD;
import com.unico.enterprise.domain.Params;

@Stateless
public class GCDDaoImpl implements GCDDao {

	@PersistenceContext(unitName = "unico.enterprise.mysql")
	EntityManager em;
	
	/* (non-Javadoc)
	 * @see com.unico.enterprise.dao.GCDDao#saveGCD(java.lang.Integer)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveGCD(Integer gcd) throws DaoException {
		GCD gcdDomainObject = new GCD();
		gcdDomainObject.setComputedGCD(gcd);
		persist(gcdDomainObject);
	}

	private void persist(Object domainObject) throws DaoException {
		try {
			em.persist(domainObject);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage());
		} 
		
	}

	/* (non-Javadoc)
	 * @see com.unico.enterprise.dao.GCDDao#getGCDList()
	 */
	@Override
	public Collection<GCD> getGCDList() {
		 Query query = em.createQuery("SELECT g FROM GCD g");
		 return (Collection<GCD>) query.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.unico.enterprise.dao.GCDDao#getSumOfGCDs()
	 */
	@Override
	public Integer getSumOfGCDs() {
		 Query query = em.createQuery("SELECT sum(g.computedGCD) FROM GCD g");
		 return ((Long) query.getSingleResult()).intValue();
	}
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
