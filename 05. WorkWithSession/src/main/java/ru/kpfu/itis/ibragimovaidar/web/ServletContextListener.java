package ru.kpfu.itis.ibragimovaidar.web;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kpfu.itis.ibragimovaidar.config.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

	private ApplicationContext applicationContext;

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext servletContext = contextEvent.getServletContext();

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		servletContext.setAttribute(ApplicationContext.class.getName(), applicationContext);
		System.out.println(applicationContext);
	}

}
