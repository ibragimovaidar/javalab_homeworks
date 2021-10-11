package ru.kpfu.itis.ibragimovaidar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.kpfu.itis.ibragimovaidar")
@PropertySource("classpath:application.properties")
public class AppConfig {

	public static final String APPLICATION_CONTEXT_SERVLET_CONTEXT_ATTRIBUTE_NAME = "springApplicationContext";

	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}

	@Bean
	DataSource hikariDataSource(HikariConfig hikariConfig){
		return new HikariDataSource(hikariConfig);
	}


	@Bean
	HikariConfig hikariConfig(
			@Value("${db.jdbcUrl}") String jdbcUrl,
			@Value("${db.username}") String username,
			@Value("${db.password}") String password,
			@Value("${db.driver}") String driver) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driver);
		return hikariConfig;
	}
}
