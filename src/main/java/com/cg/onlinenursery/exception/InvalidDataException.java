package com.cg.onlinenursery.exception;

public class InvalidDataException extends RuntimeException {
	private String message;

	public InvalidDataException() {
		this.message = "";
	}

	public InvalidDataException(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "User not found" + this.message;
	}
}