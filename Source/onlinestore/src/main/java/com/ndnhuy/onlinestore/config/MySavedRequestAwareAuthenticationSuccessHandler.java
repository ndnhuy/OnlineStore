package com.ndnhuy.onlinestore.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MySavedRequestAwareAuthenticationSuccessHandler 
extends SimpleUrlAuthenticationSuccessHandler {

private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws ServletException, IOException {
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("application/json");
		response.getWriter().write("{\"token\":\"" 
									+ new String(Base64.encode((username + ":" + password).getBytes())) 
									+ "\"}");
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
	  	
	  SavedRequest savedRequest = requestCache.getRequest(request, response);
	
	  if (savedRequest == null) {
	      clearAuthenticationAttributes(request);
	      return;
	  }
	  String targetUrlParam = getTargetUrlParameter();
	  if (isAlwaysUseDefaultTargetUrl() || 
	    (targetUrlParam != null && 
	    StringUtils.hasText(request.getParameter(targetUrlParam)))) {
	      requestCache.removeRequest(request, response);
	      clearAuthenticationAttributes(request);
	      return;
	  }
	
	  clearAuthenticationAttributes(request);
	  
	}
	
	public void setRequestCache(RequestCache requestCache) {
	  this.requestCache = requestCache;
	}
}
