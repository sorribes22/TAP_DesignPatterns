package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CsvDataFrame extends DataFrame {

	private String delimiter;

	public CsvDataFrame() {
		this(";");
	}

	public CsvDataFrame(String delimiter) {
		this.delimiter = delimiter;
	}

	public void loadContent(Scanner scanner) throws ItemWithIncorrectNumberOfAttributesException {
		int labelIndex = 0, items = 0;

		while (scanner.hasNextLine()) {
			// Remove all double quotes and split the row by DELIMITER
			String[] row = scanner.nextLine().replace("\"", "").split(this.delimiter);

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
