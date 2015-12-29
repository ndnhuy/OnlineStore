package com.ndnhuy.onlinestore.domain.domainservice.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericService;
import com.ndnhuy.onlinestore.domain.entity.product.Product;

public interface ProductService extends GenericService<Product, ProductDto, Integer> {
	void addExtraInfoInto(ProductDtoPurchase dto, Integer purchaseId);
	List<ProductDto> filter(HttpServletRequest request);
}
