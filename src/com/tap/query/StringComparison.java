package com.tap.query;

public class StringComparison implements IQuery<String> {

	private String attribute;

	private char operator;

	public StringComparison(String attribute, char operator) {
		this.attribute = attribute;
		this.operator = operator;
	}

	@Override
	public boolean fulfill(String[] item) {

		return false;
	}
}
