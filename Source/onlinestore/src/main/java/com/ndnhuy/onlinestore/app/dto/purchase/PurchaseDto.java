package com.ndnhuy.onlinestore.app.dto.purchase;

import java.io.Serializable;
import java.util.Collection;

import org.dozer.Mapping;

import com.ndnhuy.onlinestore.domain.entity.Product;

public class PurchaseDto implements Serializable {
	
	@Mapping("id")
	private Integer id;
	
	@Mapping("products")
	private Collection<Product> products;
	
	private Integer customerId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Mapping("customer.id")
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Collection<Product> getProducts() {
		return products;
	}
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
	
}
