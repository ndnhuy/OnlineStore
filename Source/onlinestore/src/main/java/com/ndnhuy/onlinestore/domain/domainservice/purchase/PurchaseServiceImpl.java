package com.ndnhuy.onlinestore.domain.domainservice.purchase;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.Product;
import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.domain.entity.PurchaseProduct;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;
import com.ndnhuy.onlinestore.repository.ProductRepository;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl extends GenericServiceImpl<Purchase, PurchaseDto, Integer> implements PurchaseService {

	private static final Logger logger = Logger.getLogger(PurchaseServiceImpl.class);

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Integer findQuantityOfProductInPurchase(Integer purchaseId,
			Integer productId) {
		
		if (!purchaseRepository.exists(purchaseId)) {
			throw new EntityNotFoundException(Purchase.class.getSimpleName(), " which has id " + purchaseId, "id = " + purchaseId);
		}
		
		if (!productRepository.exists(productId)) {
			throw new EntityNotFoundException(Purchase.class.getSimpleName(), " which has id " + productId, "id = " + productId);
		}
		
		return purchaseRepository.findQuantityOfProductInPurchase(purchaseId, productId);
		//return 99;
	}

	@Override
	public void addProductIntoCurrentPurchase(Integer productId) {
		//TODO treat the cart as the first purchase (id = 0) of this customer
		Purchase purchase = purchaseRepository.findOne(0);
		Product product = productRepository.findOne(productId);
		if (product == null) {
			throw new EntityNotFoundException(Purchase.class.getSimpleName(), " which has id " + productId, "id = " + productId);
		}
		
		for (PurchaseProduct pd : purchase.getPurchaseProducts()) {
			if (pd.getProduct().getId().equals(productId)) {
				pd.setQuantity(pd.getQuantity() + 1);
				purchaseRepository.save(purchase);
				return;
			}
		}
		
		PurchaseProduct purchaseProduct = new PurchaseProduct();
		purchaseProduct.setPurchase(purchase);
		purchaseProduct.setProduct(product);
		purchaseProduct.setQuantity(1);
		
		purchase.getPurchaseProducts().add(purchaseProduct);
		
		purchaseRepository.save(purchase);
	}
	
	
}
