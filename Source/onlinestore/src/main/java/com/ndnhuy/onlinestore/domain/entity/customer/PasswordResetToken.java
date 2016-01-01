package com.ndnhuy.onlinestore.domain.entity.customer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RESET_PASSWORD_TOKEN")
public class PasswordResetToken {
	
	public PasswordResetToken() {}
	public PasswordResetToken(String token, Date expiryDate, Customer customer) {
		super();
		this.token = token;
		this.expiryDate = expiryDate;
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="reset_password_token_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="token")
	private String token;
	
	@Column(name="expiry_date")
	private Date expiryDate;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
