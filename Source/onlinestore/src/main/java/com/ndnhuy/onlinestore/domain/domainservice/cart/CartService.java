package com.ndnhuy.onlinestore.domain.domainservice.cart;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;

public interface CartService {
	PurchaseDto findCartOfCustomer(Integer customerId);
	void addProductIntoCart(Integer customerId, Integer productId);
	boolean removeProductFromCart(Integer productId, Integer customerId);
}
