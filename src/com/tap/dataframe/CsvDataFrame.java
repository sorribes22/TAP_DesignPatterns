package com.tap.dataframe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CsvDataFrame extends DataFrame {

	private String delimiter;

	public CsvDataFrame(Scanner file) throws ItemWithIncorrectNumberOfAttributesException {
		this(file, ";");
	}

	public CsvDataFrame(Scanner file, String delimiter) throws ItemWithIncorrectNumberOfAttributesException {
		int labelIndex = 0, items = 0;

		this.delimiter = delimiter;

		while (file.hasNextLine()) {
			// Remove all double quotes and split the row by DELIMITER
			String[] row = file.nextLine().replace("\"", "").split(this.delimiter);

			if (labelIndex == 0) { // read the first name (labels)
				for (String label : row) {
					labels.add(label);
					labelIndex++;
				}
			} else {
				items++;

				if (row.length != labelIndex)
					throw new ItemWithIncorrectNumberOfAttributesException(items, labelIndex, row.length);

				Map<String, Object> newRow = new HashMap<>();

				for (int i = 0; i < row.length; i++) {
					newRow.put(labels.get(i), row[i]);
				}

				content.add(newRow);
			}
		}
	}
}
