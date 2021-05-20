package com.cg.onlinenursery.exception;

import java.util.Date;

public class OrderIdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;

	private String order;

	public OrderIdNotFoundException() {
	}

	public OrderIdNotFoundException(String order) {
		// TODO Auto-generated constructor stub
		super(order);
		this.order = order;
	}
}