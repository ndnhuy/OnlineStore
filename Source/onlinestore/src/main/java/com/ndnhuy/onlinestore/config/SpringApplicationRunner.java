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

import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;


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
	        
//	        PurchaseRepository repo = ctx.getBean(PurchaseRepository.class);
//	        
//	        Purchase p = repo.findOne(1);
//	        
//	        System.out.println(p.getProducts());
	        
	 }
}
