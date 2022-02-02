package com.tap.dataframe.query;

public enum Operator {
	EQUALS("="),
	NOT_EQUALS("!="),
	GREATER(">"),
	GREATER_OR_EQUAL(">="),
	LESS("<"),
	LESS_OR_EQUAL("<=");

	private final String sign;

	Operator(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return sign;
	}
}
