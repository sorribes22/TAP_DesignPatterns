package com.tap.datafile;

import com.tap.query.IQuery;

import java.util.*;

// TODO ASK DataFrame<T> té sentit?
public abstract class DataFrame implements Iterable<String[]> {

	/**
	 * Labels and their index position.
	 */
	protected Map<String, Integer> labels = new HashMap<>();

	/**
	 * Rows that contains de items information.
	 */
	protected List<String[]> content = new ArrayList<>();

	/**
	 * Returns the value from specific attribute item.
	 *
	 * @param row   row of item
	 * @param label column
	 * @return value of a row and column
	 */
	public String at(int row, String label) {
		return content.get(row)[labels.get(label)];
	}

	/**
	 * Returns the value from specific attribute item.
	 *
	 * @param position vector position (row * #columns + column)
	 * @return value of a row and column
	 */
	public String iat(int position) {
		int row = position / labels.size();
		int column = position % labels.size();

		return content.get(row)[column];
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

	/**
	 * TODO ASK return correcte?
	 *
	 * @param comparator
	 * @return
	 */
//	public List<String[]> sort(Comparator<List<String[]>> comparator) {
//		// (List<String>) llista.subList(from, to);
//	}
//
//	public List<String> query(IQuery<String[]> condition) {
//
//	}
	@Override
	public Iterator<String[]> iterator() {
		return content.iterator();
	}

	@Override
	public String toString() {
		String output = "DataFrame{" +
			"labels=" + labels +
			", content=[";

		for (String[] row : content) {
			output = output.concat("\n\t");
			for (String column : row) {
				output = output.concat("\t".concat(column).concat(", "));
			}
		}

		return output.concat("\n}");
	}
}
