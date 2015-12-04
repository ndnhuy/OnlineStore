package com.ndnhuy.onlinestore.config;

import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
      messageBundle.setBasenames("classpath:message/messages", "classpath:message/errors");
      messageBundle.setDefaultEncoding("UTF-8");
      return messageBundle;
    }
    
//    @Bean
//    public DataSource dataSource() {
//    	DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("org.postgresql.Driver");
//        ds.setUrl("jdbc:postgresql://pellefant.db.elephantsql.com:5432/jmqxmnjv");
//        ds.setUsername("jmqxmnjv");
//        ds.setPassword("uC4eRC5K8MV_iyqiBKDAA5XwNHvIF-Sx");
//        
//        return ds;
//    }
//    
}
