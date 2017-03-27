package com.unico.enterprise.soap;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unico.enterprise.dao.DaoException;
import com.unico.enterprise.dao.GCDDao;
import com.unico.enterprise.dao.UserDao;
import com.unico.enterprise.domain.GCD;
import com.unico.enterprise.jms.MessageConsumer;
import com.unico.enterprise.jms.Queues;
import com.unico.enterprise.utils.GCDCalculator;

/**
 * GCDSoapServiceImpl exposes SOAP services for calculating, listing and summing
 * GCD's.
 * 
 * @author Jagdeep Singh
 *
 */

@WebService(name = "GCDService", serviceName = "GCDService", 
portName = "GCDServicePort", targetNamespace = "http://soap.services.unico.com/")
@HandlerChain(file="/soap-handlers.xml")
public class GCDSoapServiceImpl implements GCDSoapService {

	@Inject
	GCDDao gcdDao;

	@Inject
	UserDao userDao;

	@Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
	ConnectionFactory jmsConnectionFactory;

	@Inject
	Queues queues;

	@Inject
	private MessageConsumer consumer;

	@Resource
    WebServiceContext webServiceContext;
	
	Logger logger = LogManager.getLogger(GCDSoapServiceImpl.class);
	
	/**
	 *  No arguments Constructor
	 */
	public GCDSoapServiceImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.unico.enterprise.soap.GCDSoapService#gcd()
	 */
	@WebMethod
	public synchronized Integer gcd() throws GeneralSecurityException, JMSException, DaoException {
		Integer param1 = consumer.receive(jmsConnectionFactory, queues.getParamQueue());
		Integer param2 = consumer.receive(jmsConnectionFactory, queues.getParamQueue());
		logger.debug("Computing GCD for param1:" + param1 + " param2:" + param2);
		Integer gcd = GCDCalculator.getGcd(param1, param2);

		// Save GCD to database
		gcdDao.saveGCD(gcd);
		return gcd;
	}

	/* (non-Javadoc)
	 * @see com.unico.enterprise.soap.GCDSoapService#gcdList()
	 */
	@WebMethod
	public synchronized List<Integer> gcdList() throws GeneralSecurityException {
		List<Integer> retList = new ArrayList<>();
		Collection<GCD> allSavedItems = gcdDao.getGCDList();
		for (GCD gcd : allSavedItems) {
			retList.add(gcd.getComputedGCD());
		}
		return retList;
	}

	/* (non-Javadoc)
	 * @see com.unico.enterprise.soap.GCDSoapService#gcdSum()
	 */
	@WebMethod
	public synchronized int gcdSum() throws GeneralSecurityException {
		return gcdDao.getSumOfGCDs();
	}
}
