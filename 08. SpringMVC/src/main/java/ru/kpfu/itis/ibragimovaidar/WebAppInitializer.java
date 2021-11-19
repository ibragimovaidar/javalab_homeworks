package ru.kpfu.itis.ibragimovaidar;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.kpfu.itis.ibragimovaidar.config.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(AppConfig.class);
		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(webApplicationContext);
		servletContext.addListener(contextLoaderListener);

		ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher",
				new DispatcherServlet(webApplicationContext));
		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.addMapping("/");
	}
}
