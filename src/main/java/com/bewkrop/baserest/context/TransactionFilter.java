package com.bewkrop.baserest.context;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class TransactionFilter implements ContainerRequestFilter, ContainerResponseFilter {
	
	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) {
		TransactionManager transactionManager = new TransactionManager();
		transactionManager.commit();
	}

	@Override
	public void filter(ContainerRequestContext request) {
		
		if (!"GET".equals(request.getMethod())) {
			TransactionManager transactionManager = new TransactionManager();
			transactionManager.beginTransaction();			
		}
		
	}

}
