package com.ndnhuy.onlinestore.app.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.domain.domainservice.purchase.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public RestSuccess updateOrderStatus(@PathVariable("id") Integer orderId, @RequestParam("order_status") String orderStatus) {
		service.updateOrderStatus(orderId, orderStatus);
		
		return new RestSuccess(HttpStatus.OK.value(), null, null);
	}
}
