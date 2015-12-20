package com.ndnhuy.onlinestore.domain.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="product_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Double price;

	public Integer getId() {
		return id;
	}
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
	private Collection<Purchase> purchases = new ArrayList<Purchase>();

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
