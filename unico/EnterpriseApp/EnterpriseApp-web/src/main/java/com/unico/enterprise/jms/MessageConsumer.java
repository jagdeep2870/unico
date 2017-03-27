package com.unico.enterprise.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MessageConsumer {

	/**
	 * This method receives a parameter for a JMS Queue and returns it to the
	 * caller.
	 * 
	 * @param jmsConnectionFactory
	 * @param queue
	 * @return
	 */
	Logger logger = LogManager.getLogger(MessageConsumer.class);

	public Integer receive(ConnectionFactory jmsConnectionFactory, Queue queue) throws JMSException {
		JMSContext context = null;
		try {
			context = jmsConnectionFactory.createContext();
			context.start();
			String param = context.createConsumer(queue).receiveBody(String.class, 1000);
			logger.debug("Received from JMS Queue = " + param);
			if(param != null) {
				return Integer.valueOf(param);
			}
			throw new JMSException("JMS Queue empty !!");
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}
}
