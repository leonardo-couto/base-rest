package com.bewkrop.baserest.context;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter
public class TransactionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, 
			ServletResponse res, FilterChain chain) throws ServletException, IOException {
		
		this.beginTransaction(req);
		
		try {
			chain.doFilter(req, res);
			
		} catch (Exception e) {
			this.closeConnection(true);
			throw e;
		}
		
		this.closeConnection();
	}

	private void beginTransaction(ServletRequest req) {
		HttpServletRequest request = (HttpServletRequest) req;
		if (!"GET".equals(request.getMethod())) {
			EntityManager em = EntityManagerFactory.get();
			em.getTransaction().begin();
		}
	}
	
	private void closeConnection() {
		this.closeConnection(false);
	}

	private void closeConnection(boolean error) {
		try {
			EntityManager em = EntityManagerFactory.get();
			if (em.isOpen()) {
				this.commit(em.getTransaction(), error);
				em.close();
			}
			
		} finally {
			EntityManagerFactory.remove();
		}
	}
	
	private void commit(EntityTransaction transaction, boolean rollback) {
		if (!transaction.isActive()) return;
		
		if (rollback) {
			this.rollback(transaction);
			return;
		}

		if (transaction.getRollbackOnly()) {
			transaction.rollback();
			throw new RuntimeException("rollback");
		} else {
			transaction.commit();
		}
	}

	private void rollback(EntityTransaction transaction) {
		try {
			transaction.rollback();
		} catch (Exception e) {
			// TODO: LOG
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void init(FilterConfig filterConfig) {}

	@Override
	public void destroy() {}

}
