package com.unico.enterprise.soap;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.jms.JMSException;
import javax.jws.WebService;

import com.unico.enterprise.dao.DaoException;

@WebService
public interface GCDSoapService {

	/**
	 * Calculate GCD for two numbers which are retrieved from JMS queue. If any
	 * of the number is Null (as queue does not have two number/messages) then
	 * we return null.
	 * 
	 * @return GCD of two numbers.
	 */
	public Integer gcd() throws GeneralSecurityException, JMSException, DaoException;

	/**
	 * List of all the GCD's which have been calculated and saved in the
	 * database.
	 * 
	 * @return List of GCS's calculated till now.
	 */
	public List<Integer> gcdList() throws GeneralSecurityException;

	/**
	 * Sum of all the GCD's which have been calculated and saved in the
	 * database.
	 * 
	 * @return Sum of all calculated GCS's till now.
	 */
	public int gcdSum() throws GeneralSecurityException;

}