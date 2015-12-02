package com.ndnhuy.onlinestore.config;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@ComponentScan("com.ndnhuy.onlinestore")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="com.ndnhuy.onlinestore.repository")
@EntityScan("com.ndnhuy.onlinestore.domain.entity")
public class SpringApplicationRunner extends SpringBootServletInitializer {
	
	private static final Logger logger = Logger.getLogger(SpringApplicationRunner.class);
	
	 @Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		
		return builder.sources(SpringApplicationRunner.class);
	}
	
	 public static void main(String[] args) {
	        ApplicationContext ctx = SpringApplication.run(SpringApplicationRunner.class, args);
	        
//	        DriverManagerDataSource ds = new DriverManagerDataSource();
//	        ds.setDriverClassName("org.postgresql.Driver");
//	        ds.setUrl("jdbc:postgresql://pellefant.db.elephantsql.com:5432/jmqxmnjv");
//	        ds.setUsername("jmqxmnjv");
//	        ds.setPassword("uC4eRC5K8MV_iyqiBKDAA5XwNHvIF-Sx");
//	        
//	        JdbcTemplate jt = new JdbcTemplate(ds);
//	        
//	        jt.execute("create table elephant (id int, name varchar)");
//	        jt.execute("insert into elephant (id, name) values (1, 'elephant_1')");
//	        jt.execute("insert into elephant (id, name) values (2, 'elephant_2')");   
//	        
//	        Object[] parameters = new Object[] {new Integer(2)};
//	        Object o = jt.queryForObject("select name from elephant where id = ?",
//	          parameters, String.class);
//	        System.out.println((String)o);
	 }
}
