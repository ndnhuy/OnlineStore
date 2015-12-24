package com.ndnhuy.onlinestore.domain.domainservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.Product;
import com.ndnhuy.onlinestore.repository.PurchaseRepository;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, ProductDto, Integer> implements ProductService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	public void addExtraInfoInto(ProductDtoPurchase dto, Integer purchaseId) {
		dto.setQuantity(purchaseRepository
								.findQuantityOfProductInPurchase(purchaseId, dto.getId()));
		
		dto.setSubtotal(dto.getPrice() * dto.getQuantity());
	}
	
}
