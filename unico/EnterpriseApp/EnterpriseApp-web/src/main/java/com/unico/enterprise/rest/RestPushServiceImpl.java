package com.unico.enterprise.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unico.enterprise.dao.ParamsDao;
import com.unico.enterprise.dao.UserDao;
import com.unico.enterprise.domain.Params;
import com.unico.enterprise.jms.MessageProducer;
import com.unico.enterprise.jms.Queues;
import com.unico.enterprise.json.JSONMapper;


/**
 * RestPushServiceImpl exposes two GET REST methods push and list.
 * 
 * @author Jagdeep
 *
 */
@Path("/gcd")
public class RestPushServiceImpl implements RestPushService {

	String name = null;
	@Inject
	ParamsDao paramsDao;
	
	@Inject
	UserDao userDao;
	
	@Inject
	MessageProducer producer;
	
	@Inject
	JSONMapper jsonMapper;
	
	@Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
	ConnectionFactory jmsConnectionFactory;

	@Inject 
	Queues queues;
	
	Logger logger = LogManager.getLogger(RestPushServiceImpl.class);

	public RestPushServiceImpl() {
		logger.debug("\n\n ........RestPushServiceImpl() ........\n\n");
		name = "Created by constructor";
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unico.services.rest.PushService#push(int, int)
	 */
	@GET
	@Path("/push/{param1}/{param2}")
	@Produces("text/plain")
	public String push(@PathParam("param1") int param1, @PathParam("param2") int param2) {
	
		logger.debug("\n param1 = " + param1 + " .. param2 = " + param2);
		try {
			producer.send(jmsConnectionFactory, queues.getParamQueue(), param1 + "");
			producer.send(jmsConnectionFactory, queues.getParamQueue(), param2 + "");
			paramsDao.saveParams(param1, param2);
		} catch (Exception e) {
			e.printStackTrace();
			return "Not able to send input to JMS queue: " + e.getMessage();
		} 
		return "Recieved parameters :" + param1 + " and " + param2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unico.services.rest.PushService#list()
	 */
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getList() {
		List<Integer> retList = new ArrayList<>();
		Collection<Params> allSavedItems = paramsDao.getSavedParamsList();
		for(Params params : allSavedItems){
			retList.add(params.getParam1());
			retList.add(params.getParam2());
		}
		return retList;
	}
}
