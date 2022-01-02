package com.tap.dataframe;

import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.query.Query;
import com.tap.dataframe.visitor.DataFrameVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface StringDataFrame extends Iterable<Map<String, String>> {
	void loadContent(File file) throws InvalidFileFormatException, FileNotFoundException;

	/**
	 * Returns the value from specific attribute item.
	 *
	 * @param row   row of item
	 * @param label column
	 * @return value of a row and column
	 */
	Object at(int row, String label);

	/**
	 * Returns the value from specific attribute item.
	 *
	 * @param row    number of item
	 * @param column attribute to retrieve
	 * @return value of a row and column
	 */
	Object iat(int row, int column);

	/**
	 * @return number of labels
	 */
	int columns();

	/**
	 * @return number of items
	 */
	int size();

	/**
	 * Sorts a column.
	 *
	 * @param column     column to sort
	 * @param comparator rule to sort by
	 * @return column content sorted
	 */
	List<String> sort(String column, Comparator<String> comparator);

	/**
	 * Filter the data frame.
	 *
	 * @param condition condition that data frame must fulfill
	 * @return data frame content filtered
	 */
	Map<String, List<String>> query(Query<Map<String, String>> condition);

	/**
	 * @return content of the data frame
	 */
	Map<String, List<String>> getContent();

	List<String> getLabels();

	void accept(DataFrameVisitor visitor);

}
