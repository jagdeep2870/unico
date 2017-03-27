package com.unico.enterprise.rest;

import java.util.List;

public interface RestPushService {

	/**
	 * It takes two parameters which are added to the JMS queue and message is
	 * returned for the status of the request.
	 * 
	 * @param param1
	 * @param param2
	 * 
	 * @return String
	 */
	public String push(int param1, int param2);

	/**
	 * It returns the list of all the elements which were added to JMS queue.
	 * 
	 * @return List<Integer>
	 * 
	 */
	public List<Integer> getList();

}