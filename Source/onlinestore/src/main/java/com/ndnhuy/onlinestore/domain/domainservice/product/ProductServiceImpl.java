package com.ndnhuy.onlinestore.domain.domainservice.product;

import org.springframework.stereotype.Service;

import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.domain.domainservice.generic.GenericServiceImpl;
import com.ndnhuy.onlinestore.domain.entity.Product;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, ProductDto, Integer> implements ProductService {
	
}
