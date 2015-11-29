package com.ndnhuy.onlinestore.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.PurchaseService;
import com.ndnhuy.onlinestore.domain.entity.Purchase;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<PurchaseDto> getAllPurchases() {
		return purchaseService.getAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void addPurchase(@RequestBody PurchaseDto addedPurchaseDto) {
		purchaseService.add(addedPurchaseDto);
	}
}
