package com.tap.dataframe.query;

public interface IQuery<T> {
	/**
	 * Checks if item fulfills a condition.
	 *
	 * @param item to check
	 * @return item passes the test
	 */
	boolean fulfill(T item);
}
