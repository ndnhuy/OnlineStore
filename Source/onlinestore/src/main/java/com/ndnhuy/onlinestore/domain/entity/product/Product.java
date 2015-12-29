package com.ndnhuy.onlinestore.domain.entity.product;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ndnhuy.onlinestore.domain.entity.Purchase;
import com.ndnhuy.onlinestore.domain.entity.PurchaseProduct;

@Entity
@Table(name="product")
public class Product {

	public static final String FILTER_KEY_NAME = "name";
	public static final String FILTER_KEY_PRICE = "price";
	public static final String FILTER_KEY_OPERATING_SYSTEM = "operatingSystem";
	public static final String FILTER_KEY_OPERATING_SYSTEM_NAME = "name";
	public static final String FILTER_KEY_BRAND = "brand";
	public static final String FILTER_KEY_BRAND_NAME = "name";
	public static final String FILTER_KEY_COLOR = "color";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="product_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Double price;

	@ManyToOne
	@JoinColumn(name="operating_system_id")
	private OperatingSystem operatingSystem;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@Column(name="color")
	private String color;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
	private Collection<Purchase> purchases = new ArrayList<Purchase>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="purchaseProductId.product",
			cascade=CascadeType.ALL)
	private Collection<PurchaseProduct> purchaseProducts;

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

	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Collection<PurchaseProduct> getPurchaseProducts() {
		return purchaseProducts;
	}

	public void setPurchaseProducts(Collection<PurchaseProduct> purchaseProducts) {
		this.purchaseProducts = purchaseProducts;
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
