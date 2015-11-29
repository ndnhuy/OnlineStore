package com.ndnhuy.onlinestore.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ndnhuy.onlinestore.commonutils.ApplicationContextProvider;
import com.ndnhuy.onlinestore.commonutils.EntityManagerProvider;

@Configuration
public class OnlineStoreConfig {
	
	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		return new DozerBeanMapper();
	}
	
	@Bean
	public ApplicationContextProvider applicationContextProvider() {
		return new ApplicationContextProvider();
	}
	
	@Bean
	public EntityManagerProvider entityManagerProvider() {
		return new EntityManagerProvider();
	}
	
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
      messageBundle.setBasename("classpath:messages");
      messageBundle.setDefaultEncoding("UTF-8");
      return messageBundle;
    }
    
    
}
