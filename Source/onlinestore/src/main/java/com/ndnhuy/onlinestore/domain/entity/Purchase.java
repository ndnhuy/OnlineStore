package com.ndnhuy.onlinestore.domain.entity;

import java.util.ArrayList;
import java.util.Collection;

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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="purchase_id_seq", allocationSize=1)
	private Integer id;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinTable(name="purchase_product",
				joinColumns={@JoinColumn(name="purchase_id")},
				inverseJoinColumns={@JoinColumn(name="product_id")})
	private Collection<Product> products = new ArrayList<Product>();
	
	
	
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
	
	
	
}
