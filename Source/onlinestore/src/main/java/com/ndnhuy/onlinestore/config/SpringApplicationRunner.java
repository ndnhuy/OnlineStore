package com.ndnhuy.onlinestore.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.sync.Diff;
import org.springframework.sync.Patch;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;


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
	        
	        CustomerDto original = new CustomerDto();
	        original.setEmail("A");
	        original.setId(1);
	        original.setName("BBBB");
	        
	        CustomerDto modified = new CustomerDto();
	        modified.setEmail("A");
	        modified.setId(1);
	        modified.setName("B");
	        
	        Patch patch = Diff.diff(original, modified);
	        
	        CustomerDto patched = patch.apply(original, CustomerDto.class);
	        
	        System.out.println(ToStringBuilder.reflectionToString(patch));
	 }
}
