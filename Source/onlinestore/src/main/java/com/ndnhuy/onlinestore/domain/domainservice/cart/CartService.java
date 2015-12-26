package com.ndnhuy.onlinestore.domain.domainservice.cart;

import org.springframework.sync.Patch;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;


public interface CartService {
	PurchaseDto findCartOfCustomer(Integer customerId);
	void addProductIntoCart(Integer customerId, Integer productId);
	boolean removeProductFromCart(Integer productId, Integer customerId);
	void updateQuantityOfProductInCart(Integer quantity, Integer customerId, Integer productId);
}
