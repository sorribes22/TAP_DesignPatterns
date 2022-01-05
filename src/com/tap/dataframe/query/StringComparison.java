package com.tap.dataframe.query;

import java.util.Map;

public class StringComparison extends Query<Map<String, String>> {

	public StringComparison(String attribute, Operator operator, String match) {
		super(attribute, operator, match);
	}

	@Override
	public boolean fulfill(Map<String, String> item) {
		String value = item.get(this.attribute);

		return switch (this.operator) {
			case EQUALS -> value.equals(this.match);
			case NOT_EQUALS -> !value.equals(this.match);
			case LESS -> value.compareTo(this.match) < 0;
			case GREATER -> value.compareTo(this.match) > 0;
			case GREATER_OR_EQUAL -> value.equals(this.match) || value.compareTo(this.match) > 0;
			default -> false;
		};
	}
}
