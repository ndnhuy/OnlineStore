package com.ndnhuy.onlinestore.domain.domainservice.cart;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.product.ProductService;
import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public PurchaseDto findCartOfCustomer(Integer customerId) {
		//TODO temporarily to get the first purchase of specific customer, later must find by order status
		List<Purchase> purchases = purchaseRepository.findPurchaseByCustomerId(customerId);
		
		PurchaseDto dtoPurchase = mapper.map(purchases.get(0), PurchaseDto.class);
		
		Double total = 0.0;
		for (ProductDtoPurchase p : dtoPurchase.getProducts()) {
			
			productService.addExtraInfoInto(p, dtoPurchase.getId());
			
			total += p.getSubtotal();
		}
		
		dtoPurchase.setTotal(total);
		
		return dtoPurchase;
	}

}
