package ru.kpfu.itis.ibragimovaidar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.kpfu.itis.ibragimovaidar")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class ApplicationConfig {

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource){
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public DataSource h2DataSource(){
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:sql/schema.sql")
				.addScript("classpath:sql/data.sql")
				.build();
	}

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".ftlh");
		viewResolver.setContentType("text/html;charset=UTF-8");
		return viewResolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfig() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("/templates/");
		return configurer;
	}

}
