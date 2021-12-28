package com.tap.dataframe;

import com.tap.dataframe.query.Query;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Returns the value from specific attribute item.
     *
     * @param row   row of item
     * @param label column
     * @return value of a row and column
     */
    @Override
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
    @Override
    public Object iat(int row, int column) {
        return content.get(labels.get(column)).get(row);
    }

    /**
     * @return number of labels
     */
    @Override
    public int columns() {
        return labels.size();
    }

    /**
     * @return number of items
     */
    @Override
    public int size() {
        return size;
    }

	@Override
    public List<String> sort(String column, Comparator<String> comparator) {
        // TODO si content.get(column) retorna null, retornar una llista buida
		return content.get(column).stream().sorted(comparator).collect(Collectors.toList());
	}

	@Override
    public Map<String, List<String>> query(Query<Map<String, String>> condition) {
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
    public Map<String, List<String>> getContent() {
        return content;
    }

    //    @Override
//    public String toString() {
//        String output = "DataFrame{" +
//                "labels=" + labels +
//                ", content=[\n";
//
//        for (Map<String, String> item : this) {
//            for (String label : item.keySet()) {
//                output = output.concat("\t".concat(label).concat(": ").concat(item.get(label)));
//            }
//
//            output = output.concat("\n");
//        }
//
//        return output.concat("]}");
//    }


    @Override
    public String toString() {
        return "DataFrame{" +
            "size=" + size +
            ", labels=" + labels +
            ", content=" + content +
            '}';
    }


   /* public String toString(){
        String retu = "";
        for(String columns:labels)
           retu=retu+columns;
        retu = retu + "/n";
        for (int i =0;i<labels.size();i++){
            content.get(labels.get(i)).get()
        }
        return retu;
           }
    */

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
