package com.ndnhuy.onlinestore.domain.entity.purchase;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_order")
public class Order {
	@Id
	@Column(name="purchase_id")
	private Integer id;
	
	@Column(name="SHIPPING_ADDRESS_COUNTRY")
	private String country;
	
	@Column(name="SHIPPING_ADDRESS_STREET")
	private String street;
	
	@Column(name="SHIPPING_ADDRESS_REGION")
	private String region;
	
	@Column(name="SHIPPING_ADDRESS_CITY")
	private String city;
	
	@Column(name="total")
	private Double total;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="order_date")
	private Date orderDate;

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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
