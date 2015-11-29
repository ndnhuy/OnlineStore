package com.ndnhuy.onlinestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.sync.diffsync.web.JsonPatchHttpMessageConverter;

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
	
	@Bean
	public JsonPatchHttpMessageConverter jsonPatchMessageConverter() {
		return new JsonPatchHttpMessageConverter();
	}
}
