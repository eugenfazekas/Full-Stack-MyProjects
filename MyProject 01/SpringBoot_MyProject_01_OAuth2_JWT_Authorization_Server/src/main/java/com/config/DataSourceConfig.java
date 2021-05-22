package com.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
	
	@Value("spring.datasource.url")
	private String url;
	
	@Value("spring.datasource.username")
	private String username;
	
	@Value("spring.datasource.password")
	private String password;
	
	@Value("spring.datasource.driver-class-name")
	private String driver;

	public DataSource mySqlDataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	       
	        dataSource.setUrl(url);
	        dataSource.setUsername(username);
	        dataSource.setPassword(password);
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	            
	        return dataSource;
	    }
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
	    return new JdbcTemplate(mySqlDataSource());
	}
}
