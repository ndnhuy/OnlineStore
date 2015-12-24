package com.ndnhuy.onlinestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.sync.diffsync.web.JsonPatchHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	 
   
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
	
	@Autowired
	private OnlineStoreInterceptor onlineStoreInterceptor;
	
	@Bean
	public JsonPatchHttpMessageConverter jsonPatchMessageConverter() {
		return new JsonPatchHttpMessageConverter();
	}
	
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
			// TODO Auto-generated method stub
			super.addInterceptors(registry);
			
			registry.addInterceptor(onlineStoreInterceptor);
		}
}
