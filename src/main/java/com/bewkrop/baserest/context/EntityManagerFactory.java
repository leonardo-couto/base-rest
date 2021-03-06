package com.bewkrop.baserest.context;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//TODO: ver se inicializa tambem no gae 
@WebListener
public class EntityManagerFactory implements ServletContextListener {
	
	private static final String NAME_KEY = "persistence-unit";
	private static final LocalEntityManager LOCAL = new LocalEntityManager();

	private static javax.persistence.EntityManagerFactory factory = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String name = getName();
		factory = Persistence.createEntityManagerFactory(name);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (factory != null) factory.close();
	}
	
	public static EntityManager get() {
		return LOCAL.get();
	}
	
	public static void remove() {
		LOCAL.remove();
	}
	
	private static EntityManager createEntityManager() {
		return factory.createEntityManager();
	}
	
	private static String getName() {
		try {
			Properties properties = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			properties.load(classLoader.getResourceAsStream("config.properties"));
			
			return properties.getProperty(NAME_KEY);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private static class LocalEntityManager extends ThreadLocal<EntityManager> {

		@Override
		protected EntityManager initialValue() {
			return EntityManagerFactory.createEntityManager();
		}

		@Override
		public void remove() {
			EntityManager em = this.get();
			if (em != null && em.isOpen()) {
				em.close();
			}
			super.remove();
		}

	}
	
}
