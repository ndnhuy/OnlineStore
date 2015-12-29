package com.ndnhuy.onlinestore.app.dto.purchase;

import java.io.Serializable;
import java.util.Collection;

import org.dozer.Mapping;

import com.ndnhuy.onlinestore.app.dto.product.ProductDto;
import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;
import com.ndnhuy.onlinestore.domain.entity.product.Product;

public class PurchaseDto implements Serializable {
	
	@Mapping("id")
	private Integer id;
	
	@Mapping("products")
	private Collection<ProductDtoPurchase> products;
	
	@Mapping("purchaseStatus.id")
	private Integer statusId;
	
	private Double total;
	
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
	public Collection<ProductDtoPurchase> getProducts() {
		return products;
	}
	public void setProducts(Collection<ProductDtoPurchase> products) {
		this.products = products;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
	
}
