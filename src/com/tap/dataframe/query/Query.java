package com.tap.dataframe.query;

import java.util.Objects;

public abstract class Query<T> {
	protected String attribute;

	protected Operator operator;

	protected String match;

	public Query(String attribute, Operator operator, String match) {
		this.attribute = attribute;
		this.operator = operator;
		this.match = match;
	}

	/**
	 * Checks if item fulfills a condition.
	 *
	 * @param item to check
	 * @return item passes the test
	 */
	abstract public boolean fulfill(T item);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Query<?> query = (Query<?>) o;
		return Objects.equals(attribute, query.attribute) && operator == query.operator && Objects.equals(match, query.match);
	}
}
