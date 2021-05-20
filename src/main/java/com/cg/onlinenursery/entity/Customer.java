package com.cg.onlinenursery.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

//import src.com.cg.onlineplantnursery.customer.entity.Address;
@Entity
@Table(name = "customers")

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "customerId")
	private Integer customerId;
	@Column(name = "customerName")
	private String customerName;
	@Column(name = "customerEmail")
	private String customerEmail;

	@JsonProperty(access = Access.READ_ONLY)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addressId")
	private Address address;
	@JsonIgnore
	// @JsonProperty(access=Access.READ_ONLY)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", targetEntity = Order.class, orphanRemoval = true)
	// @JoinColumn(name="orderId")
	private List<Order> order;

	/**
	 * @return the order
	 */
	public List<Order> getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Customer(List<Order> order) {
		super();
		this.order = order;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer(Integer customerId, String customerName, String customerEmail, Address address, List<Order> order) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.address = address;
		this.order = order;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

}