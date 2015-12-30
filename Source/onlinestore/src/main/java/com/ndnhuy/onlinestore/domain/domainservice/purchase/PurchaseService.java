package com.ndnhuy.onlinestore.domain.domainservice.purchase;

import java.util.List;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericService;
import com.ndnhuy.onlinestore.domain.entity.purchase.Purchase;

public interface PurchaseService extends GenericService<Purchase, PurchaseDto, Integer> {
	Integer findQuantityOfProductInPurchase(Integer purchaseId, Integer productId);
	void addProductIntoPurchase(Integer productId, Integer purchaseId);
	boolean removeProductFromPurchase(Integer productId, Integer purchaseId);
	List<PurchaseDto> findPurchaseByCustomerIdAndStatusId(Integer customerId, Integer statusId);
	void updateQuantityOfProductInPurchase(Integer quantity, Integer productId, Integer purchaseId);
}
