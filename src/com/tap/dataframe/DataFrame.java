package com.tap.dataframe;

import com.tap.dataframe.query.Query;
import com.tap.dataframe.visitor.DataFrameVisitor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class DataFrame implements StringDataFrame {
	/**
	 * Number of items loaded
	 */
	protected int size = 0;

	/**
	 * Names of the columns.
	 */
	protected List<String> labels = new ArrayList<>();

	/**
	 * Rows that contains de items information.
	 */
	protected Map<String, List<String>> content = new LinkedHashMap<>();


	@Override
	public String at(int row, String label) {
		return content.get(label).get(row);
	}

	@Override
	public String iat(int row, int column) {
		return content.get(labels.get(column)).get(row);
	}

	@Override
	public int columns() {
		return labels.size();
	}

	public List<String> getLabels() {
		return labels;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<String> sort(String column, Comparator<String> comparator) {
		List<String> result = content.get(column);

		return result == null ? (new ArrayList<>()) : result.stream().sorted(comparator).collect(Collectors.toList());
	}

	@Override
//	public Map<String, List<String>> query(Query<Map<String, String>> condition) {
	public List<Map<String, String>> query(Query<Map<String, String>> condition) {
//		Map<String, List<String>> result = new HashMap<>();
//		for (String label : labels) result.put(label, new ArrayList<>());
//
//		for (Map<String, String> row : this) {
//			if (condition.fulfill(row)) {
//				for (String column : row.keySet()) {
//					result.get(column).add(row.get(column));
//				}
//			}
//		}
//
//		return result;
//		content.entrySet().stream().filter(condition.fulfill(Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
		Stream<Map<String, String>> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				this.iterator(),
				Spliterator.ORDERED)
			, true);

		return stream.filter(condition::fulfill).collect(Collectors.toList());
	}

	@Override
	public Map<String, List<String>> getContent() {
		return content;
	}

	public void accept(DataFrameVisitor visitor) {
		visitor.visit(this);
	}

	public Iterator<Map<String, String>> iterator() {
		return new Iterator<>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < size;
			}

			@Override
			public Map<String, String> next() {
				Map<String, String> row = new HashMap<>();

				for (String label : labels) {
					row.put(label, getContent().get(label).get(currentIndex));
				}

				currentIndex++;

				return row;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public String toString() {
		return "DataFrame{" +
			"size=" + size +
			", labels=" + labels +
			", content=" + content +
			'}';
	}
}
