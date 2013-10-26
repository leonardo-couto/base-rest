package com.bewkrop.baserest.context;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.rollback();
		
		// TODO: log exception		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception).build();
	}

}
