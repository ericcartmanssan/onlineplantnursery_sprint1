package com.cg.onlinenursery.exception;

import java.util.Date;

public class CustomerIdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private String message;

	private String customer;

	public CustomerIdNotFoundException() {
	}

	public CustomerIdNotFoundException(String customer) {
		super(customer);
		this.customer = customer;
	}

}