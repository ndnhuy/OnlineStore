package com.ndnhuy.onlinstore.unittest.domain.domainservice;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.commonutils.ValidatorUtil;
import com.ndnhuy.onlinestore.config.OnlineStoreConfig;
import com.ndnhuy.onlinestore.config.SpringApplicationRunner;
import com.ndnhuy.onlinestore.domain.domainservice.CustomerServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.Customer;
import com.ndnhuy.onlinestore.repository.CustomerRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringApplicationRunner.class, OnlineStoreConfig.class})
public class CustomerServiceTest {
	
	
	@InjectMocks
	@Autowired
	private CustomerServiceImpl customerService;
	
	@Mock
	private CustomerRepository customerRepo;
	
	@Mock
	private ValidatorUtil validator;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	
	@Rule
    public ExpectedException ex= ExpectedException.none();
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void add_customerHasNoPurchases_shouldSucceed() {
		CustomerDto customerHasExistedName = new CustomerDto();
		customerHasExistedName.setEmail("exampleEmail@gmail.com");
		customerHasExistedName.setName("exampleName");
		customerHasExistedName.setPassword("password");
		customerHasExistedName.setPurchases(null);
		
		customerService.add(customerHasExistedName);
		
		Mockito.verify(validator, Mockito.times(2)).validate(Mockito.anyObject());
		Mockito.verify(validator, Mockito.times(1)).checkIfFieldValueIsUniqueInRepo(customerHasExistedName, Customer.class);
		Mockito.verify(customerRepo, Mockito.times(1)).saveAndFlush(Mockito.any(Customer.class));
	}
	
	@Test
	public void add_theNameDoesAlreadyExist_shouldThrowExceptionWhileValidating() {
				
		CustomerDto customerHasExistedName = new CustomerDto();
		customerHasExistedName.setEmail("exampleEmail@gmail.com");
		customerHasExistedName.setName("exampleName");
		customerHasExistedName.setPassword("password");
		customerHasExistedName.setPurchases(null);
		
		ex.expect(RuntimeException.class);
		
		Mockito.doThrow(new RuntimeException()).when(validator).checkIfFieldValueIsUniqueInRepo(customerHasExistedName, Customer.class);
		
		customerService.add(customerHasExistedName);
	}
	
	
}
