package com.ndnhuy.onlinestore.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ndnhuy.onlinestore.domain.entity.product.Product;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="purchase_id_seq", allocationSize=1)
	private Integer id;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private PurchaseStatus purchaseStatus;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinTable(name="purchase_product",
				joinColumns={@JoinColumn(name="purchase_id")},
				inverseJoinColumns={@JoinColumn(name="product_id")})
	private Collection<Product> products = new ArrayList<Product>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="purchaseProductId.purchase",
			cascade=CascadeType.ALL, orphanRemoval=true)
	private List<PurchaseProduct> purchaseProducts;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public List<PurchaseProduct> getPurchaseProducts() {
		return purchaseProducts;
	}

	public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
		this.purchaseProducts = purchaseProducts;
	}

	public PurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	
	
	
}
