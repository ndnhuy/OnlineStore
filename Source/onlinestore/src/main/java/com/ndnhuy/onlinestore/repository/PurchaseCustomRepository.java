package com.ndnhuy.onlinestore.repository;

public interface PurchaseCustomRepository {
	Integer findQuantityOfProductInPurchase(Integer purchaseId, Integer productId);
}
