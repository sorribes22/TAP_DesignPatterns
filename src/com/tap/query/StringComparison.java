package com.tap.query;

import java.util.Map;

public class StringComparison implements IQuery<Map<String, String>> {

	private String attribute;

	private Operator operator;

	private String match;

	public StringComparison(String attribute, Operator operator, String match) {
		this.attribute = attribute;
		this.setOperator(operator);
		this.match = match;
	}

	private void setOperator (Operator operator) {
		this.operator = operator;
	}

	@Override
	public boolean fulfill(Map<String, String> item) {
		String value = item.get(attribute);

		return switch (this.operator) {
			case EQUALS -> value.equals(this.match);
			case NOT_EQUALS -> !value.equals(this.match);
			case LESS -> value.compareTo(this.match) < 0;
			case GREATER -> value.compareTo(this.match) > 0;
			case GREATER_OR_EQUAL -> value.equals(this.match) || value.compareTo(this.match) > 0;
			default -> false;
		};
	}

	@Override
	public String getAttribute() {
		return this.attribute;
	}
}
