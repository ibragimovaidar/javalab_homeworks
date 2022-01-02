package ru.kpfu.itis.ibragimovaidar;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.kpfu.itis.ibragimovaidar.config.ApplicationConfig;

import javax.servlet.ServletException;

public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {

	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
		var context = new AnnotationConfigWebApplicationContext();
		context.register(ApplicationConfig.class);
		var contextLoaderListener = new ContextLoaderListener(context);
		servletContext.addListener(contextLoaderListener);

		var dispatcherServlet = new DispatcherServlet(context);
		var servletRegistration = servletContext.addServlet("dispatcher", dispatcherServlet);
		servletRegistration.addMapping("/");
		servletRegistration.setLoadOnStartup(1);
	}
}
