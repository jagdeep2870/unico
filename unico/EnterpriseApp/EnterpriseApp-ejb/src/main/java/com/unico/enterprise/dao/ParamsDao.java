package com.unico.enterprise.dao;

import java.util.Collection;
import java.util.List;

import com.unico.enterprise.domain.Params;

public interface ParamsDao {

	/**
	 * Will add both the integers to DB.
	 * 
	 * @param i
	 * @param j
	 * @throws DaoException
	 */
	public void saveParams(Integer i, Integer j) throws DaoException;

	

	/**
	 * Will add both the integers to DB.
	 * 
	 */
	public Collection<Params> getSavedParamsList();

	

}
