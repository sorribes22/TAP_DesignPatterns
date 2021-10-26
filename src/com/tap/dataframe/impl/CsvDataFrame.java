package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;

import java.util.ArrayList;
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
		int labelIndex = 0;

		while (scanner.hasNextLine()) {
			// Remove all double quotes and split the row by DELIMITER
			String[] row = scanner.nextLine().replace("\"", "").split(this.delimiter);

			if (labelIndex == 0) { // read the first name (labels)
				for (String label : row) {
					labels.add(label);
					content.put(label, new ArrayList<>());
					labelIndex++;
				}
			} else {
				if (row.length != labelIndex)
					throw new ItemWithIncorrectNumberOfAttributesException(size + 1, labelIndex, row.length);

				for (int i = 0; i < labelIndex; i++) {
					content.get(labels.get(i)).add(row[i]);
				}

				size++;
			}
		}
	}
}
