package com.unico.enterprise.dao;

import java.util.Collection;
import java.util.List;

import com.unico.enterprise.domain.GCD;


public interface GCDDao {

	/**
	 * Will add GCD to the List of integers in DB.
	 * 
	 * @param gcd
	 * 
	 */
	public void saveGCD(Integer gcd) throws DaoException;
	
	/**
	 * Fetches the saved GCDs from DB
	 * @return Collection<GCD>
	 */
	public Collection<GCD> getGCDList();
	
	/**
	 * Gets sum of all GCDs saved till date
	 * @return Integer
	 */
	public Integer getSumOfGCDs();
}
