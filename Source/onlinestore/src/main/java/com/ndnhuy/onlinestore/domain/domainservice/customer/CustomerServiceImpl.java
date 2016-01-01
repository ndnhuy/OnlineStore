package com.ndnhuy.onlinestore.domain.domainservice.customer;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.customer.BasicCustomerDto;
import com.ndnhuy.onlinestore.app.dto.customer.CustomerDto;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.commonutils.Constant;
import com.ndnhuy.onlinestore.commonutils.CurrentUser;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.customer.Address;
import com.ndnhuy.onlinestore.domain.entity.customer.Customer;
import com.ndnhuy.onlinestore.domain.entity.customer.CustomerDetail;
import com.ndnhuy.onlinestore.domain.entity.customer.PasswordResetToken;
import com.ndnhuy.onlinestore.domain.entity.customer.UserRole;
import com.ndnhuy.onlinestore.domain.entity.customer.VerificationToken;
import com.ndnhuy.onlinestore.domain.entity.purchase.Purchase;
import com.ndnhuy.onlinestore.domain.exception.AppException;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;
import com.ndnhuy.onlinestore.repository.CustomerRepository;
import com.ndnhuy.onlinestore.repository.PasswordResetTokenRepository;
import com.ndnhuy.onlinestore.repository.UserRoleRepository;
import com.ndnhuy.onlinestore.repository.VerificationTokenRepository;

@Service
@Transactional
public class CustomerServiceImpl extends GenericServiceImpl<Customer, CustomerDto, Integer> implements CustomerService {
	
	private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepo;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepo;
	
	@Override
	public CustomerDto add(CustomerDto dto) {
		
		validator.validate(dto);
		
		//TODO do the validating by hand
		if(customerRepository.findByUsername(dto.getUsername()) != null) {
			throw new AppException(HttpStatus.CONFLICT.value(), "This username already exist", "The username [" + dto.getUsername() + "] already exist");
		}
		if (customerRepository.findByEmail(dto.getEmail()) != null) {
			throw new AppException(HttpStatus.CONFLICT.value(), "This email already exist", "The email [" + dto.getEmail() + "] already exist");
		}
		
		//TODO must using cascade function here, it's too much code.
		
		Customer customer = new Customer();
		customer.setUsername(dto.getUsername());
		customer.setPassword(dto.getPassword());
		customer.setEmail(dto.getEmail());
		customer.setEnabled(false);
	
		customerRepository.saveAndFlush(customer);
		
		CustomerDetail detail = new CustomerDetail();
		Address address = new Address(dto.getStreet(), dto.getCity());
		
		address.setCustomerDetail(detail);
		detail.setAddress(address);
		
		customer.setCustomerDetail(detail);
		
		detail.setId(customer.getId());
		detail.setCustomer(customer);
		
//		if (customer.getPurchases() != null) {
//			for (Purchase p : customer.getPurchases()) {
//				p.setCustomer(customer);
//			}
//		}
		
		UserRole userRole = userRoleRepo.findByRoleNameIgnoreCase(Constant.ROLE_USER);
		
		if (userRole == null) {
			throw new EntityNotFoundException(UserRole.class.getSimpleName(), Constant.ROLE_USER, Constant.ROLE_USER);
		}
		
		customer.setUserRole(userRole);
		
		validator.validate(customer);
		
		//TODO check field unique in repo not working due to the private fields in BasiCustomerDto not available in CustomerDto
		validator.checkIfFieldValueIsUniqueInRepo(dto, Customer.class);
		
		repository.saveAndFlush(customer);
		
		
		return mapper.map(customer, CustomerDto.class);
	}
	
//	@Override
//	public CustomerDto update(CustomerDto updatedInfo) {
//		if (updatedInfo.getId() == null) {
//			throw new AppException(HttpStatus.BAD_REQUEST.value(), "You cannot update this information", "The CustomerDto id field is requried");
//		}
//		
//		customerRepository.findOne(updatedInfo.getId());
//		
//		
//	}

	@Override
	public Collection<PurchaseDto> findPurchasesOfCustomer(Integer customerId) {
		if(logger.isDebugEnabled()) {
			logger.debug("Find all purchases of customer [id = " + customerId + "]");
		}
		
		Customer customer = repository.findOne(customerId);
		if (customer == null) {
			throw new EntityNotFoundException(Customer.class.getSimpleName(), "id = " + customerId, "id = " + customerId);
		}
		
		Collection<Purchase> purchases = customer.getPurchases();
		
		Collection<PurchaseDto> dtoPurchases = new ArrayList<PurchaseDto>();
		for (Purchase p : purchases) {
			dtoPurchases.add(mapper.map(p, PurchaseDto.class));
		}
		
		return dtoPurchases;
	}
	
	@Override
	public Collection<CustomerDto> findAll() {
		return super.findAll();
	}

	@Override
	public BasicCustomerDto findBasicCustomerInfo(Integer customerId) {
		if(logger.isDebugEnabled()) {
			logger.debug("Find basic customer info id " + customerId);
		}
		
		Customer customer = repository.findOne(customerId);
		BasicCustomerDto dtoBasicCustomer = mapper.map(customer, BasicCustomerDto.class);
		
		return dtoBasicCustomer;
	}

	@Override
	public void createVerificationToken(Integer customerId, String token) {
		CustomerDto dtoCustomer = findOne(customerId);
		
		Customer customer = new Customer();
		customer.setId(customerId);
		verificationTokenRepo.save(new VerificationToken(token, null, customer));
	}

	@Override
	public CustomerDto confirmRegistration(String token) {
		VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
		if (verificationToken == null) {
			//TODO create confiemRegistration fail exception
			throw new AppException(HttpStatus.UNAUTHORIZED.value(), "Your confirm token is invalid", "Your token " + token + " is invalid");
		}
		
		Customer customer = verificationToken.getCustomer();
		if (customer == null) {
			throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cannot retrieve customer from token " + token, "Cannot retrieve customer from token " + token);
		}
		
		customer.setEnabled(true);
		
		return mapper.map(customer, CustomerDto.class);
	}

	@Override
	public void createPasswordResetToken(String email, String token) {
		Customer customer = customerRepository.findByEmail(email);
		if (customer == null) {
			throw new AppException(HttpStatus.NOT_FOUND.value(), "Your email does not exist. Fail to reset password", "The email " + email + " does not exist");
		}
		
		passwordResetTokenRepo.save(new PasswordResetToken(token, null, customer));
	}

	@Override
	public CustomerDto confirmPasswordReset(String token, String newPassword) {
		PasswordResetToken passwordResetToken = passwordResetTokenRepo.findByToken(token);
		if (passwordResetToken == null) {
			//TODO create confirmPasswordReset fail exception
			throw new AppException(HttpStatus.UNAUTHORIZED.value(), "You cannot reset password", "The password reset token is invalid");
		}
		
		Customer customer = passwordResetToken.getCustomer();
		if (customer == null) {
			throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cannot retrieve customer from token " + token, "Cannot retrieve customer from token " + token);
		}
		
		customer.setPassword(newPassword);
		
		return mapper.map(customer, CustomerDto.class);
	}
	
	

}
