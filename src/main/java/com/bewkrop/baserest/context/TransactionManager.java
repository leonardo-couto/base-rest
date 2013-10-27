package com.bewkrop.baserest.context;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionManager {
	
	private final Logger logger = LoggerFactory.getLogger(TransactionManager.class);
	private final EntityManager em;
	
	public TransactionManager() {
		this.em = EntityManagerFactory.get();
	}
	
	public void beginTransaction() {
		this.em.getTransaction().begin();
	}
	
	public void commit() {
		this.closeConnection(false);
	}
	
	public void rollback() {
		this.closeConnection(true);
	}
	
	private void closeConnection(boolean error) {
		try {

			if (this.em.isOpen()) { 
				this.closeTransaction(error);
			}
			
		} finally {
			EntityManagerFactory.remove();
		}
	}

	private void closeTransaction(boolean error) {
		try {
			EntityTransaction transaction = this.em.getTransaction();
			this.commitOrRollback(transaction, error);
		} finally {
			this.em.close();
		}
	}
	
	private void commitOrRollback(EntityTransaction transaction, boolean rollback) {
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
			logger.error("Error trying to rollback", e);
		}
	}
	

}
