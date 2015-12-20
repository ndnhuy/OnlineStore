package com.ndnhuy.onlinestore.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_product")
public class PurchaseProduct {
	
	@EmbeddedId
	private PurchaseProductId purchaseProductId;
	
	@ManyToOne
	@JoinColumn(name="purchase_id", insertable=false, updatable=false)
	private Purchase purchase;
	
	@ManyToOne
	@JoinColumn(name="product_id", insertable=false, updatable=false)
	private Product product;
	
	@Column(name="quantity")
	private Integer quantity;

	
	
	public PurchaseProductId getPurchaseProductId() {
		return purchaseProductId;
	}

	public void setPurchaseProductId(PurchaseProductId purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}

//	public Purchase getPurchase() {
//		return purchase;
//	}
//
//	public void setPurchase(Purchase purchase) {
//		this.purchase = purchase;
//	}
//
//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
