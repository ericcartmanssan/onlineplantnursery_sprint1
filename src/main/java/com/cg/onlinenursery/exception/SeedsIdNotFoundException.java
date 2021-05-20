package com.cg.onlinenursery.exception;

public class SeedsIdNotFoundException extends RuntimeException {
	private String seedId;

	public SeedsIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public SeedsIdNotFoundException(String seedId) {
		super(seedId);
		this.seedId = seedId;
	}

}