package com.unico.enterprise.rest.filter;

import java.security.GeneralSecurityException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unico.enterprise.dao.UserDao;
import com.unico.enterprise.soap.GCDSoapServiceImpl;
import com.unico.enterprise.utils.AuthUtils;

/*
 * This class acts a an interceptor for REST requests to the server 
 * and provides the security services.
 */
@PreMatching
@Priority(value = 3)
@Provider
public class RestAuthHandler implements ContainerRequestFilter {

	@Inject
	UserDao userDao;

	Logger logger = LogManager.getLogger(RestAuthHandler.class);

	public RestAuthHandler() {
		logger.debug("ServerAuthenticationRequestFilter initialization");
	}

	@Override
	public void filter(ContainerRequestContext requestContext) {
		ResponseBuilder responseBuilder = null;
		logger.debug("filter() on RestRequestFilter");
		try {
			AuthUtils.authenticate(userDao, requestContext.getHeaders());
			logger.debug("Authentication Filter [PASSED]");
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("Authentication Filter [FAILED]");
			responseBuilder = Response.serverError();
			Response response = responseBuilder.status(Status.UNAUTHORIZED).build();
			requestContext.abortWith(response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("Authentication Filter [FAILED]");
			responseBuilder = Response.serverError();
			Response response = responseBuilder.status(Status.INTERNAL_SERVER_ERROR).build();
			requestContext.abortWith(response);
		} 
	}
}
