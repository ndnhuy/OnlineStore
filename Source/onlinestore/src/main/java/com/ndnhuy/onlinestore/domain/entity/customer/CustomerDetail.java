package com.ndnhuy.onlinestore.domain.entity.customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_detail")
public class CustomerDetail {
	
	@Id
	@Column(name="customer_id")
	//TODO do the primaryKeyJoinColumn here
//	@GeneratedValue(generator="customForeignGenerator")
//	@GenericGenerator(name = "customForeignGenerator", 
//						strategy = "foreign",
//						parameters = @Parameter(name="property", value="customer"))
	private Integer customerId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	Address address;
	
	@OneToOne
	@JoinColumn(name="customer_id")
    public Customer customer;

	public Integer getId() {
		return customerId;
	}

	public void setId(Integer customerId) {
		this.customerId = customerId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
