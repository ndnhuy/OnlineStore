package com.ndnhuy.onlinestore.repository;

public interface PurchaseCustomRepository {
	Integer findQuantityOfProductInPurchase(Integer purchaseId, Integer productId);
//	void removeProductFromPurchase(Integer purchaseId, Integer productId);
}
