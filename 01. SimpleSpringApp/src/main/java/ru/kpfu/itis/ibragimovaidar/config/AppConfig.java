package ru.kpfu.itis.ibragimovaidar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"ru.kpfu.itis.ibragimovaidar"})
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(hikariDataSource());
	}

	@Bean
	public DataSource hikariDataSource(){
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public HikariConfig hikariConfig(){
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(env.getProperty("db.jdbcUrl"));
		hikariConfig.setUsername(env.getProperty("db.username"));
		hikariConfig.setPassword(env.getProperty("db.password"));
		return hikariConfig;
	}
}
