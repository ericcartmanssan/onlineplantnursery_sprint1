package com.cg.onlinenursery.entity;

import java.io.Serializable;
//import java.util.List;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "Orderentity")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "bookingOrderId")
	private Integer bookingOrderId;
	@Column(name = "orderDate")
	private LocalDate orderDate;
	@Column(name = "transactionMode")
	private String transactionMode;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "totalCost")
	private double totalCost;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Planters.class, orphanRemoval = true)

	@JsonProperty(access = Access.READ_ONLY)
	@JoinColumn(name = "plantersid")
	private List<Planters> planters;
	// @JsonIgnore
	@ManyToOne()
	@JsonProperty(access = Access.READ_ONLY)
	@JoinColumn(name = "customerid")
	private Customer customer;

	public Order(Customer customer) {
		super();
		this.customer = customer;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order() {
	}

	public Order(Integer bookingOrderId, LocalDate orderDate, String transactionMode, int quantity, double totalCost,
			List<Planters> planters) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.orderDate = orderDate;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
		this.planters = planters;
	}

	public Integer getBookingOrderId() {
		return bookingOrderId;
	}

	public void setBookingOrderId(Integer bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public List<Planters> getPlanters() {
		return planters;
	}

	public void setPlanters(List<Planters> planters) {
		this.planters = planters;
	}
}