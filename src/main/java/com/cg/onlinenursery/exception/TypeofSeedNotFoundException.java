package com.cg.onlinenursery.exception;

public class TypeofSeedNotFoundException extends RuntimeException {
	private static String typeofseed;

	public TypeofSeedNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public TypeofSeedNotFoundException(String typeofseed) {
		super(typeofseed);
		this.typeofseed = typeofseed;
	}

}