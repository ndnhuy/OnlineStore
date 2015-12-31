package com.ndnhuy.onlinestore.app.service.purchase;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.purchase.OrderDto;
import com.ndnhuy.onlinestore.commonutils.Constant;
import com.ndnhuy.onlinestore.commonutils.CurrentUser;
import com.ndnhuy.onlinestore.domain.domainservice.purchase.OrderService;

@RestController
@RequestMapping("/buy/orders")
@Secured(Constant.ROLE_USER)
public class UserOrderController {
	
	private static final Logger logger = Logger.getLogger(UserOrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	@Autowired
	private CurrentUser currentUser;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getOrders() {
		return new RestSuccess(HttpStatus.OK.value(), orderService.findOrdersOfCustomer(currentUser.getCustomerId()), null);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public RestSuccess addOrder(@RequestBody OrderDto orderDto) {
		return new RestSuccess(HttpStatus.OK.value(), orderService.findOrdersOfCustomer(currentUser.getCustomerId()), null);
	}
	
	@RequestMapping(value="/{orderId}", method=RequestMethod.DELETE)
	public RestSuccess deleteOrder(@PathVariable("orderId") Integer orderId) {
		orderService.delete(orderId);
		return new RestSuccess(HttpStatus.OK.value(), null, null);
	}
}
