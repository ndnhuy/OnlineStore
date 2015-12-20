package com.ndnhuy.onlinestore.app.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@RestController
public class HomeController {
	
	@Autowired
	PurchaseRepository repo;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Integer home() {
		
		Integer quantity = repo.findQuantityOfProductInPurchase(2, 2);
		
		return quantity;
	}
}
