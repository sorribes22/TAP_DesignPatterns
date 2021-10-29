package com.tap.query;

public interface IQuery<T> {
	/**
	 * Checks if item fulfills a condition.
	 *
	 * @param item to check
	 * @return item passes the test
	 */
	boolean fulfill(T item);

	/**
	 * Returns the attribute to query.
	 *
	 * @return attribute to query
	 */
	String getAttribute();
}
