package com.ndnhuy.onlinestore.app.dto.purchase;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapping;

import com.ndnhuy.onlinestore.app.dto.product.ProductDtoPurchase;

public class OrderDto implements Serializable {
	@Mapping("id")
	private Integer id;
	
	@Mapping("country")
	private String country;
	
	@Mapping("street")
	private String street;
	
	@Mapping("region")
	private String region;
	
	@Mapping("city")
	private String city;
	
	@Mapping("total")
	private Double total;
	
	@Mapping("orderStatus")
	private String orderStatus;
	
	private Collection<ProductDtoPurchase> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Collection<ProductDtoPurchase> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductDtoPurchase> products) {
		this.products = products;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
