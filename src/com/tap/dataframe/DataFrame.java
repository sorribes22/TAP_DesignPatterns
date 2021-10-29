package com.tap.dataframe;

import com.tap.query.IQuery;

import java.util.*;
import java.util.stream.Collectors;

public abstract class DataFrame implements Iterable<Map<String, String>> {
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

    public abstract void loadContent(Scanner scanner) throws ItemWithIncorrectNumberOfAttributesException;

    /**
     * Returns the value from specific attribute item.
     *
     * @param row   row of item
     * @param label column
     * @return value of a row and column
     */
    public Object at(int row, String label) {
        return content.get(label).get(row);
    }

    /**
     * Returns the value from specific attribute item.
     *
     * @param row number of item
     * @param column attribute to retrieve
     * @return value of a row and column
     */
    public Object iat(int row, int column) {
        return content.get(labels.get(column)).get(row);
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
        return size;
    }

	/**
	 *
	 *
	 * @param comparator
	 * @return
	 */
	public List<String> sort(String column, Comparator<String> comparator) {
		return content.get(column).stream().sorted(comparator).collect(Collectors.toList());
	}


    /**
     *
     * @param condition
     * @return
     */
	public Map<String, List<String>> query(IQuery<Map<String, String>> condition) {
        Map<String, List<String>> result = new HashMap<>();
        for (String label : labels) result.put(label, new ArrayList<>());

        for (Map<String, String> row : this) {
            if (condition.fulfill(row)) {
                for (String column : row.keySet()) {
                    result.get(column).add(row.get(column));
                }
            }
        }

        return result;
	}

    @Override
    public String toString() {
        String output = "DataFrame{" +
                "labels=" + labels +
                ", content=[\n";

        for (Map<String, String> item : this) {
            for (String label : item.keySet()) {
                output = output.concat("\t".concat(label).concat(": ").concat(item.get(label)));
            }

            output = output.concat("\n");
        }

        return output.concat("]}");
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
                    row.put(label, content.get(label).get(currentIndex));
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
}
