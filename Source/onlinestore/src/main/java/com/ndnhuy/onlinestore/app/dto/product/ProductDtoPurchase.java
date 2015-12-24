package com.ndnhuy.onlinestore.app.dto.product;

public class ProductDtoPurchase extends ProductDto{
	Integer quantity;
	Double subtotal;
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	
}
