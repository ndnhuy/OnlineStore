package com.ndnhuy.onlinestore.domain.domainservice.purchase;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.domainservice.product.ProductService;
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
	
	@Autowired
	private ProductService productService;
	
	
	@Override
	public PurchaseDto findOne(Integer id) {
		PurchaseDto purchaseDto = super.findOne(id);
		
		Double total = 0.0;
		for (ProductDtoPurchase p : purchaseDto.getProducts()) {
			
			productService.addExtraInfoInto(p, purchaseDto.getId());
			
			total += p.getSubtotal();
		}
		
		purchaseDto.setTotal(total);
		
		return purchaseDto;
	}
	
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
		//TODO treat the cart as the first purchase (id = 1) of this customer
		Purchase purchase = findCurrentPurchase();
		Product product = productRepository.findOne(productId);
		if (product == null) {
			throw new EntityNotFoundException(Purchase.class.getSimpleName(), " which has id " + productId, "id = " + productId);
		}
		
		//TODO optimize performance here
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

	@Override
	public boolean removeProductFromCurrentPurchase(Integer productId) {
		Purchase purchase = findCurrentPurchase();
		Product product = productRepository.findOne(productId);
		if (product == null) {
			throw new EntityNotFoundException(Purchase.class.getSimpleName(), " which has id " + productId, "id = " + productId);
		}
		
		for (PurchaseProduct pd : purchase.getPurchaseProducts()) {
			if (pd.getProduct().getId().equals(productId)) {
				purchase.getPurchaseProducts().remove(pd);
				purchaseRepository.save(purchase);
				return true;
			}
		}
		
		return false;
	}

	//TODO why return Purchase??????
	@Override
	public Purchase findCurrentPurchase() {
		return purchaseRepository.findOne(1);
	}
	
	
}
