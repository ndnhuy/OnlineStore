package com.ndnhuy.onlinestore.app.service.common;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.sync.Patch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.domain.entity.Address;
import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.domain.entity.CustomerDetail;
import com.ndnhuy.onlinestore.repository.CustomerRepository;

@RestController
@Transactional
public class HomeController {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Integer home() {
		return 1;
	}
	@RequestMapping(value="/", method=RequestMethod.PATCH)
	public Integer home2(@RequestBody Patch patch) {
		
//		CustomerDto dto
//		
//		CustomerDto modifiedDto = patch.apply(in, type)
		
		return 1;
	}
}
