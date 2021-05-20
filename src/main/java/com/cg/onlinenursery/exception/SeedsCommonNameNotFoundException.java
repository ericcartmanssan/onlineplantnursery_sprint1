package com.cg.onlinenursery.exception;

public class SeedsCommonNameNotFoundException extends RuntimeException {
	private String CommonName;

	public SeedsCommonNameNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public SeedsCommonNameNotFoundException(String commonName) {
		super(commonName);
		this.CommonName = commonName;
	}

}