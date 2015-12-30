package com.ndnhuy.onlinestore.domain.entity.purchase;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ndnhuy.onlinestore.domain.entity.product.Product;

@Entity
@Table(name="purchase_product")
@AssociationOverrides({
	@AssociationOverride(name="purchaseProductId.purchase",
			joinColumns=@JoinColumn(name="purchase_id")),
	@AssociationOverride(name="purchaseProductId.product",
			joinColumns=@JoinColumn(name="product_id"))
})
public class PurchaseProduct {
	
	private PurchaseProductId purchaseProductId = new PurchaseProductId();
	
	@Column(name="quantity")
	private Integer quantity;

	@EmbeddedId
	public PurchaseProductId getPurchaseProductId() {
		return purchaseProductId;
	}

	public void setPurchaseProductId(PurchaseProductId purchaseProductId) {
		this.purchaseProductId = purchaseProductId;
	}
	
	@Transient
	public Purchase getPurchase() {
		return getPurchaseProductId().getPurchase();
	}

	public void setPurchase(Purchase purchase) {
		getPurchaseProductId().setPurchase(purchase);
	}

	@Transient
	public Product getProduct() {
		return getPurchaseProductId().getProduct();
	}

	public void setProduct(Product product) {
		getPurchaseProductId().setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
