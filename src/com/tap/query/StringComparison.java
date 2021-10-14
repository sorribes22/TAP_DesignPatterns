package com.tap.query;

import java.util.Map;

public class StringComparison implements IQuery<Map<String, Object>> {

	private String attribute;

	private char operator;

	private String value;

	public StringComparison(String attribute, char operator, String value) {
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
	}

	@Override
	public boolean fulfill(Map<String, Object> item) {

		return false;
	}
}
