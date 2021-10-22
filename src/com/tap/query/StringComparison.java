package com.tap.query;

import java.util.Comparator;
import java.util.Map;

public class StringComparison implements IQuery<Object extends Comparable<? super Object>> {

	private String attribute;

	private Operator operator;

	private String value;

	public StringComparison(String attribute, Operator operator, String value) {
		this.attribute = attribute;
		this.setOperator(operator);
		this.value = value;
	}

	private void setOperator (Operator operator) {
		// TODO raise exception if incorrect operator provided
		this.operator = operator;
	}

	@Override
	public boolean fulfill(Object item) {
		return switch (this.operator) {
			case EQUALS -> item.equals(this.value);
			case LESS -> item < this.value;
//			case '<':
//				return item.get(this.attribute).compareTo(value);
			default -> false;
		};
	}
}
