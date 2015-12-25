package com.ndnhuy.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ndnhuy.onlinestore.domain.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, PurchaseCustomRepository {
//	@Query("select * from PurchaseProduct purchaseProduct where "
//			+ "purchaseProduct.purchase.id = :purchaseId "
//			+ "and purchaseProduct.product.id = :productId")
//	@Query("select p.quantity from PurchaseProduct p")
//	Integer findQuantityOfProductInPurchase(@Param("purchaseId") Integer purchaseId, 
//											@Param("productId") Integer productId);
	
	@Query("select p from Purchase p where p.customer.id = :customerId and p.purchaseStatus.id = :statusId")
	Purchase findPurchaseByCustomerIdAndStatusId(@Param("customerId") Integer customerId, 
												@Param("statusId") Integer statusId);
	
}
