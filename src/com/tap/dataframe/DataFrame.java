package com.tap.dataframe;

import com.tap.query.IQuery;

import java.util.*;

public abstract class DataFrame implements Iterable<Map<String, Object>> {

	/**
	 * Names of the columns.
	 */
	protected List<String> labels = new ArrayList<>();

	/**
	 * Rows that contains de items information.
	 */
	protected List<Map<String, Object>> content = new ArrayList<>();

	/**
	 * Returns the value from specific attribute item.
	 *
	 * @param row   row of item
	 * @param label column
	 * @return value of a row and column
	 */
	public Object at(int row, String label) {
		return content.get(row).get(label);
	}

	/**
	 * Returns the value from specific attribute item.
	 *
	 * @param position vector position (row * #columns + column)
	 * @return value of a row and column
	 */
	public Object iat(int position) {
		int row = position / labels.size();
		int columnIndex = position % labels.size();

		return content.get(row).get(labels.get(columnIndex));
	}

	/**
	 * @return number of labels
	 */
	public int columns() {
		return labels.size();
	}

	/**
	 * @return number of items
	 */
	public int size() {
		return content.size();
	}

//	/**
//	 * TODO ASK return correcte?
//	 *
//	 * @param comparator
//	 * @return
//	 */
//	public List<String[]> sort(Comparator<List<String[]>> comparator) {
//		// (List<String>) llista.subList(from, to);
//	}


//	/**
//	 * @param condition
//	 * @return
//	 */
//	public List<String> query(IQuery<String[]> condition) {
//
//	}
	@Override
	public Iterator<Map<String, Object>> iterator() {
		return content.iterator();
	}

	@Override
	public String toString() {
		String output = "DataFrame{" +
			"labels=" + labels +
			", content=[";

		content.forEach(item -> {
			output = output.concat("\n\t");
			for (String column : row) {
				output = output.concat("\t".concat(column).concat(", "));
			}
		});
		for (String[] row : content) {
			output = output.concat("\n\t");
			for (String column : row) {
				output = output.concat("\t".concat(column).concat(", "));
			}
		}

		return output.concat("\n}");
	}
}
