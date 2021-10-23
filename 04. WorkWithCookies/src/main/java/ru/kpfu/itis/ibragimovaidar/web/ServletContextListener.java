package ru.kpfu.itis.ibragimovaidar.web;

import ru.kpfu.itis.ibragimovaidar.AuthenticationService;
import ru.kpfu.itis.ibragimovaidar.AuthenticationServiceHardcodedImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class ServletContextListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		AuthenticationService authenticationService = new AuthenticationServiceHardcodedImpl();
		servletContext.setAttribute(AuthenticationService.class.getName(), authenticationService);
	}

}
