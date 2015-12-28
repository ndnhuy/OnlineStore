package com.ndnhuy.onlinestore.domain.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer")
public class Customer implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="customer_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="username")
	@NotNull
	private String username;
	
	@Column(name="email")
	@NotNull
	private String email;
	
	@Column(name="password", unique=true)
	@NotNull
	private String password;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private UserRole userRole;
	
	@OneToOne(mappedBy="customer",cascade=CascadeType.ALL)
	private CustomerDetail customerDetail;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer", cascade=CascadeType.ALL)
	private Collection<Purchase> purchases;
	
	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}
	
	
}
