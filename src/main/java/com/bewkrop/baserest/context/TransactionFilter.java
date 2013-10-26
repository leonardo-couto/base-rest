package com.bewkrop.baserest.context;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class TransactionFilter implements ContainerRequestFilter, ContainerResponseFilter {
	
	@Override
	public void filter(ContainerRequestContext request) {
		
		try {
			if (!"GET".equals(request.getMethod())) {
				TransactionManager transactionManager = new TransactionManager();
				transactionManager.beginTransaction();			
			}
		} catch (Exception e) {
			// TODO: log exception
			ResponseBuilder responseBuilder = Response.status(Status.INTERNAL_SERVER_ERROR);
			Response response = responseBuilder.entity(e.getMessage()).build();
			request.abortWith(response);
		}
		
	}

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) {
		try {
			TransactionManager transactionManager = new TransactionManager();
			transactionManager.commit();
			
		} catch (Exception e) {
			response.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
			// TODO: log exception
			response.setEntity(e.getMessage());
		}
	}
	
}
