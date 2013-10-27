package com.bewkrop.baserest.context;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {
	
	private final Logger logger = LoggerFactory.getLogger(ThrowableMapper.class);

	@Override
	public Response toResponse(Throwable exception) {
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.rollback();
		
		logger.error("Uncaught exception", exception);
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception).build();
	}

}
