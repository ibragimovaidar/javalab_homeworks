package ru.kpfu.itis.ibragimovaidar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.spi.ConfigurationState;
import java.util.Properties;

@Configuration
@ComponentScan("ru.kpfu.itis.ibragimovaidar")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories(basePackages = "ru.kpfu.itis.ibragimovaidar.repository")
public class ApplicationConfig {

	@Autowired
	private Environment environment;

	@Bean
	public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			JpaVendorAdapter jpaVendorAdapter,
			DataSource hikariDataSource,
			Properties hibernateProperties
	) {
		var entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactory.setDataSource(hikariDataSource);
		entityManagerFactory.setJpaProperties(hibernateProperties);
		entityManagerFactory.setPackagesToScan("ru.kpfu.itis.ibragimovaidar.model");
		return entityManagerFactory;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		var adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.POSTGRESQL);
		return adapter;
	}

	@Bean
	public Properties hibernateProperties(){
		var properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		return properties;
	}

	@Bean
	public DataSource hikariDataSource(HikariConfig hikariConfig){
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	public HikariConfig hikariConfig(
			@Value("${db.url}") String url,
			@Value("${db.username}") String username,
			@Value("${db.password}") String password,
			@Value("${db.driver}") String driver
	){
		var hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driver);
		return hikariConfig;
	}

	@Bean
	public Validator validator(){
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
}
