package com.ndnhuy.onlinestore.app.dto.product;

import org.dozer.Mapping;

public class ProductDto {
	@Mapping("id")
	Integer id;
	
	@Mapping("name")
	String name;
	
	@Mapping("price")
	Double price;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
