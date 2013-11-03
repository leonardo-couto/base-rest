package com.bewkrop.baserest.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

@WebListener
public class SLF4JBridgeListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
