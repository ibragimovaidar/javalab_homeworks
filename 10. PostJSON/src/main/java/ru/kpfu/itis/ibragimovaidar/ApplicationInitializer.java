package ru.kpfu.itis.ibragimovaidar;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.kpfu.itis.ibragimovaidar.config.ApplicationConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ApplicationConfig.class);

		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(context);
		servletContext.addListener(contextLoaderListener);

		ServletRegistration.Dynamic registration =
				servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		registration.setLoadOnStartup(1);
		registration.addMapping("/", "/*");
	}
}
