package com.ndnhuy.onlinestore.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ndnhuy.onlinestore.commonutils.CurrentUser;
import com.ndnhuy.onlinestore.domain.entity.Customer;
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
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(customer.getUserRole().getRoleName().toUpperCase()));
			
			currentUser.setCustomerId(customer.getId());
			currentUser.setUsername(username);
			
			return new User(customer.getUsername(), customer.getPassword(), authorities);
		}
		
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
