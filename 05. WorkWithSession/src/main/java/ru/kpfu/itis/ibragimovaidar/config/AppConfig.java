package ru.kpfu.itis.ibragimovaidar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "ru.kpfu.itis.ibragimovaidar")
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Bean
	protected HikariDataSource hikariDataSource(HikariConfig hikariConfig){
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	protected HikariConfig hikariConfig(
			@Value("${db.jdbcUrl}") String jdbcUrl,
			@Value("${db.username}" ) String username,
			@Value("${db.password}") String password,
			@Value("${db.driver}") String driverClassName
	) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driverClassName);
		return hikariConfig;
	}
}
