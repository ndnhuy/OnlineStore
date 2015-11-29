package com.ndnhuy.onlinestore.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class WebConfig {
	 
   
//	@Bean
//	public HttpMessageConverter<Object> createXmlHttpMessageConverter() {
//        MarshallingHttpMessageConverter xmlConverter = 
//          new MarshallingHttpMessageConverter();
// 
//        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
//        xmlConverter.setMarshaller(xstreamMarshaller);
//        xmlConverter.setUnmarshaller(xstreamMarshaller);
// 
//        return xmlConverter;
//	}
}
