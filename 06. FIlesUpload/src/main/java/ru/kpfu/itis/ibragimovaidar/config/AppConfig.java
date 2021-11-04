package ru.kpfu.itis.ibragimovaidar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.kpfu.itis.ibragimovaidar")
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Bean
	protected DataSource hikariDataSource(HikariConfig hikariConfig){
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	protected HikariConfig hikariConfig(
			@Value("${db.url}") String url,
			@Value("${db.user}") String user,
			@Value("${db.password}") String password,
			@Value("${db.driver}") String driver
	){
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(user);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driver);
		return hikariConfig;
	}
}
