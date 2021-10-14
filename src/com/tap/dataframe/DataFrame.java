package com.tap.dataframe;

import com.tap.query.IQuery;

import java.util.*;
import java.util.stream.Collectors;

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
     * @param row number of item
     * @param column attribute to retrieve
     * @return value of a row and column
     */
    public Object iat(int row, int column) {
        return content.get(row).get(labels.get(column));
    }

    /**
     * Returns the value from specific attribute item.
     *
     * @param position vector position (row * #columns + column)
     * @return value of a row and column
     */
    public Object iat(int position) {
        int row = position / labels.size();
        int column = position % labels.size();

        return this.iat(row, column);
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
//	public List<String[]> sort(Comparator<Map<String, Object>> comparator) {
//		// (List<String>) llista.subList(from, to);
//	}


    /**
     *
     * @param condition
     * @return
     */
	public List<Map<String, Object>> query(IQuery<Map<String, Object>> condition) {
        return content.stream().filter(condition::fulfill).collect(Collectors.toList());
	}

    @Override
    public Iterator<Map<String, Object>> iterator() {
        return content.iterator();
    }

    @Override
    public String toString() {
        String output = "DataFrame{" +
                "labels=" + labels +
                ", content=[\n";

        for (Map<String, Object> item : content) {
            for (String label : item.keySet()) {
                output = output.concat("\t".concat(label).concat(": ").concat(item.get(label).toString()));
            }

            output = output.concat("\n");
        }

        return output.concat("]}");
    }
}
