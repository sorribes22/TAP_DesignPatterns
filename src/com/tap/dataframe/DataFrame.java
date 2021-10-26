package com.tap.dataframe;

import com.tap.query.IQuery;

import java.util.*;
import java.util.stream.Collectors;

public abstract class DataFrame implements Iterable<Map<String, List<Object>>> {

    protected int size = 0;

    /**
     * Names of the columns.
     */
    protected List<String> labels = new ArrayList<>();

    /**
     * Rows that contains de items information.
     */
    protected Map<String, List<Object>> content = new LinkedHashMap<>();

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
    public Iterator<Map<String, List<Object>>> iterator() {
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
