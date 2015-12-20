package com.ndnhuy.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ndnhuy.onlinestore.domain.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

	@Query("select purchaseProduct.quantity from PurchaseProduct purchaseProduct where "
			+ "purchaseProduct.purchase.id = :purchaseId "
			+ "and purchaseProduct.product.id = :productId")
	Integer findQuantityOfProductInPurchase(@Param("purchaseId") Integer purchaseId, 
											@Param("productId") Integer productId);
	
	
}
