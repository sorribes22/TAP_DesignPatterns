package com.tap.dataframe.query;

import java.util.Map;

public class IntComparasion extends Query<Map<String, String>> {

	public IntComparasion(String attribute, Operator operator, String match) {
		super(attribute, operator, match);
	}

	@Override
	public boolean fulfill(Map<String, String> item) {

		int value = Integer.parseInt(item.get(this.attribute));
		int intMatch = Integer.parseInt(this.match);

		return switch (this.operator) {
			case EQUALS -> value == intMatch;
			case NOT_EQUALS -> value != intMatch;
			case LESS -> value < intMatch;
			case GREATER -> value > intMatch;
			case GREATER_OR_EQUAL -> value >= intMatch;
			default -> false;
		};
	}
}
