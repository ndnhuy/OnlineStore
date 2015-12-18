package com.ndnhuy.onlinestore.app.service.purchase;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ndnhuy.onlinestore.app.dto.common.RestSuccess;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.purchase.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	
	private static final Logger logger = Logger.getLogger(PurchaseController.class);
	
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public RestSuccess getAllPurchases() {
		logger.info("Find all purchases");
		
		Collection<PurchaseDto> purchases = purchaseService.findAll();
		
		logger.info("Purchases: ");
		for (PurchaseDto p : purchases) {
			logger.info("Id: " + p.getId() + ", customerId: " + p.getCustomerId());
		}
		
		return new RestSuccess(HttpStatus.OK.value(), purchaseService.findAll());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public RestSuccess getPurchase(@PathVariable("id") Integer id) {
		logger.info("Get purchase: id = " + id);
		
		PurchaseDto purchaseDto = purchaseService.findOne(id);
		
		logger.info("Purchase [id = " + purchaseDto.getId() + ", customerId = " + purchaseDto.getCustomerId() + "]");
		
		return new RestSuccess(HttpStatus.OK.value(), purchaseDto, null);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public RestSuccess addPurchase(@RequestBody PurchaseDto addedPurchaseDto) {
		logger.info("Add purchase[id: " + addedPurchaseDto.getId() + ", customerId: " + addedPurchaseDto.getCustomerId());
		
		PurchaseDto newlyCreatedPurchase = purchaseService.add(addedPurchaseDto);
		
		return new RestSuccess(HttpStatus.CREATED.value(), newlyCreatedPurchase, "Location " + 
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newlyCreatedPurchase.getId()).toUriString());
	}
	
	
}
