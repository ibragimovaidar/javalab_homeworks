package ru.kpfu.itis.ibragimovaidar.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kpfu.itis.ibragimovaidar.config.AppConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		servletContextEvent.getServletContext()
				.setAttribute(AppConfig.APPLICATION_CONTEXT_SERVLET_CONTEXT_ATTRIBUTE_NAME, context);
	}
}
