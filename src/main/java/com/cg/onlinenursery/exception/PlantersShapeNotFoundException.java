package com.cg.onlinenursery.exception;

public class PlantersShapeNotFoundException extends RuntimeException {
	private static String planterShape;

	public PlantersShapeNotFoundException(String planterShape) {
		super(planterShape);
		this.planterShape = planterShape;
	}

	public PlantersShapeNotFoundException() {
		// TODO Auto-generated constructor stub
	}

}
