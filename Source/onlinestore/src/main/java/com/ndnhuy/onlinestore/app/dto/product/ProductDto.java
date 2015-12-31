package com.ndnhuy.onlinestore.app.dto.product;

import java.io.Serializable;

import org.dozer.Mapping;

public class ProductDto implements Serializable {
	@Mapping("id")
	Integer id;
	
	@Mapping("name")
	String name;
	
	@Mapping("price")
	Double price;
	
	@Mapping("operatingSystem.name")
	String operatingSystemName;
	
	@Mapping("brand.name")
	String brandName;
	
	@Mapping("color")
	String color;
	
	@Mapping("imagePath")
	String imagePath;
	
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
	public String getOperatingSystemName() {
		return operatingSystemName;
	}
	public void setOperatingSystemName(String operatingSystemName) {
		this.operatingSystemName = operatingSystemName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
