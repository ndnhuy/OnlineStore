package com.ndnhuy.onlinestore.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PurchaseRepositoryImpl implements PurchaseCustomRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Integer findQuantityOfProductInPurchase(Integer purchaseId,
			Integer productId) {
		Integer quantity = (Integer) em.createNativeQuery("SELECT quantity FROM purchase_product "
				+ "WHERE purchase_product.purchase_id = " + purchaseId
				+ " AND purchase_product.product_id = " + productId)
		.getSingleResult();
		return quantity;
	}

}
