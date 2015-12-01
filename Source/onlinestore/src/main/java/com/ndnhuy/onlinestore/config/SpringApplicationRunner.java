package com.ndnhuy.onlinestore.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.commonutils.ApplicationContextProvider;
import com.ndnhuy.onlinestore.commonutils.EntityManagerProvider;
import com.ndnhuy.onlinestore.commonutils.ValidatorUtil;
import com.ndnhuy.onlinestore.domain.domainservice.CustomerService;
import com.ndnhuy.onlinestore.domain.domainservice.CustomerServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.repository.CustomerRepository;


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
	        
//	        CustomerDto dto = new CustomerDto();
//	        dto.setEmail("A867201@gmail.com");
//	        dto.setName("5465");
//	        dto.setPassword("pass");
//	        dto.setPurchases(null);
//	        
//	        CustomerServiceImpl service = ctx.getBean(CustomerServiceImpl.class);
//	        service.add(dto);
	 }
}
