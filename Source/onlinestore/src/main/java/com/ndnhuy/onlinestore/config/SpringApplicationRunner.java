package com.ndnhuy.onlinestore.config;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.domain.domainservice.customer.CustomerService;
import com.ndnhuy.onlinestore.domain.domainservice.customer.CustomerServiceImpl;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;


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
	        
	        ReloadableResourceBundleMessageSource messageSource = (ReloadableResourceBundleMessageSource) ctx.getBean("messageSource");
	        
	        
	        CustomerDto dto = new CustomerDto();
	        dto.setEmail("sampleEmail@gmail.com");
	        dto.setName("sample");
	        dto.setId(99);
	        dto.setPassword("pass");
	        dto.setPurchases(null);
	        
	        CustomerService service = ctx.getBean(CustomerServiceImpl.class);
	 }
}
