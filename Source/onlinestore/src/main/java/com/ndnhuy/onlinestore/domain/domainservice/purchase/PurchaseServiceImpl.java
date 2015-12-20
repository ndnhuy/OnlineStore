package com.ndnhuy.onlinestore.domain.domainservice.purchase;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;
import com.ndnhuy.onlinestore.repository.ProductRepository;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl extends GenericServiceImpl<Purchase, PurchaseDto, Integer> implements PurchaseService {

	private static final Logger logger = Logger.getLogger(PurchaseServiceImpl.class);

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	ProductRepository ProductRepository;
	
	@Override
	public Integer findQuantityOfProductInPurchase(Integer purchaseId,
			Integer productId) {
		
		if (!purchaseRepository.exists(purchaseId)) {
			throw new EntityNotFoundException(Purchase.class.getSimpleName(), " which has id " + purchaseId, "id = " + purchaseId);
		}
		
		if (!ProductRepository.exists(productId)) {
			throw new EntityNotFoundException(Purchase.class.getSimpleName(), " which has id " + productId, "id = " + productId);
		}
		
		return purchaseRepository.findQuantityOfProductInPurchase(purchaseId, productId);
	}
	
	
}
