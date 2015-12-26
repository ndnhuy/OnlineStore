package com.ndnhuy.onlinestore.domain.domainservice.cart;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.app.dto.purchase.PurchaseDto;
import com.ndnhuy.onlinestore.commonutils.ConstPurchaseStatus;
import com.ndnhuy.onlinestore.domain.domainservice.product.ProductService;
import com.ndnhuy.onlinestore.domain.domainservice.purchase.PurchaseService;
import com.ndnhuy.onlinestore.domain.exception.EntityNotFoundException;

@Service
public class CartServiceImpl implements CartService {

	private final static Logger logger = Logger.getLogger(CartServiceImpl.class);
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public PurchaseDto findCartOfCustomer(Integer customerId) {
		logger.debug("Find cart of customer [id=" + customerId + "]");
		
		PurchaseDto dtoPurchase = null;
		try {
			dtoPurchase = purchaseService
					.findPurchaseByCustomerIdAndStatusId(customerId, 
														ConstPurchaseStatus.ORDERING.value());
		} catch (EntityNotFoundException ex) {
			PurchaseDto newPurchase = new PurchaseDto();
			newPurchase.setCustomerId(customerId);
			newPurchase.setStatusId(ConstPurchaseStatus.ORDERING.value());
			
			dtoPurchase = purchaseService.add(newPurchase);
			
			dtoPurchase.setProducts(new ArrayList<ProductDtoPurchase>());
		}
		
		
		Double total = 0.0;
		
		logger.debug(String.format("Add extra info into each %s inside %s", ProductDtoPurchase.class.getSimpleName(), PurchaseDto.class.getSimpleName()));
		for (ProductDtoPurchase p : dtoPurchase.getProducts()) {
			
			productService.addExtraInfoInto(p, dtoPurchase.getId());
			
			total += p.getSubtotal();
		}
		
		dtoPurchase.setTotal(total);
		logger.debug(String.format("Return %s [id=%d, total=%f]", PurchaseDto.class.getSimpleName(), dtoPurchase.getId(), dtoPurchase.getTotal()));
		
		return dtoPurchase;
	}

	@Override
	public void addProductIntoCart(Integer customerId, Integer productId) {
		logger.debug("Add product [id=" + productId + "] into cart of customer [id=" + customerId + "]");
		
		PurchaseDto dtoPurchase = purchaseService
				.findPurchaseByCustomerIdAndStatusId(customerId, 
													ConstPurchaseStatus.ORDERING.value());
		
		purchaseService.addProductIntoPurchase(productId, dtoPurchase.getId());
	}

	@Override
	public boolean removeProductFromCart(Integer productId, Integer customerId) {
		
		logger.debug("Delete product id " + productId + " from cart of customer id " + customerId);
		
		PurchaseDto dtoPurchase = purchaseService
				.findPurchaseByCustomerIdAndStatusId(customerId, 
													ConstPurchaseStatus.ORDERING.value());
		
		return purchaseService.removeProductFromPurchase(productId, dtoPurchase.getId());
	}

	@Override
	public void updateQuantityOfProductInCart(Integer quantity, Integer customerId,
			Integer productId) {

		PurchaseDto dtoPurchase = purchaseService
				.findPurchaseByCustomerIdAndStatusId(customerId, 
													ConstPurchaseStatus.ORDERING.value());
		
		purchaseService.updateQuantityOfProductInPurchase(quantity, productId, dtoPurchase.getId());
		
	}

}
