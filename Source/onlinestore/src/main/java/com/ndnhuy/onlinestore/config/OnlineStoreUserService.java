package com.ndnhuy.onlinestore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ndnhuy.onlinestore.commonutils.CurrentUser;
import com.ndnhuy.onlinestore.domain.entity.customer.Customer;
import com.ndnhuy.onlinestore.domain.exception.AppException;
import com.ndnhuy.onlinestore.repository.CustomerRepository;

@Component
public class OnlineStoreUserService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private CurrentUser currentUser;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Customer customer = customerRepository.findByUsername(username);
		
		if (customer != null) {
			
			if (customer.getEnabled() == false) {
				throw new AppException(HttpStatus.UNAUTHORIZED.value(), 
						"The account " + username + " is not activated", "The customer with username '" + username + "' has not been activated");
			}
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(customer.getUserRole().getRoleName().toUpperCase()));
			
			currentUser.setCustomerId(customer.getId());
			currentUser.setUsername(username);
			
			return new User(customer.getUsername(), customer.getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
