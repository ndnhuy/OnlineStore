package com.ndnhuy.onlinestore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private OnlineStoreUserService userService;
	
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .csrf().disable()
	      .exceptionHandling()
	      .authenticationEntryPoint(restAuthenticationEntryPoint)
	      .and()
	      .authorizeRequests()
	      	.antMatchers(HttpMethod.POST, "/").authenticated()
	      	.antMatchers(HttpMethod.PUT, "/").authenticated()
	      	.antMatchers(HttpMethod.DELETE, "/").authenticated()
	        .anyRequest().permitAll()
	        .and()
	      .httpBasic().and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and()
	      .formLogin()
	      .loginProcessingUrl("/validate_account")
	      .usernameParameter("username")
	      .passwordParameter("password")
	      .successHandler(authenticationSuccessHandler)
	      .failureHandler(new SimpleUrlAuthenticationFailureHandler())
	      .and()
	      .logout();
	  }
  
  	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
//		auth
//			.inMemoryAuthentication()
//			.withUser("admin").password("1234").roles("ADMIN").and()
//			.withUser("user").password("1234").roles("USER");
  		
  		auth
  			.userDetailsService(userService);
	}
}
