package com.tap.datafile;

import java.util.Scanner;

public class CsvDataFrame extends DataFrame {
	// TODO ASK hem de fer-lo variable?
	final String DELIMITER = ";";

	public CsvDataFrame(Scanner file) throws ItemWithIncorrectNumberOfAttributesException {
		int labelIndex = 0, items = 0;

		while (file.hasNextLine()) {
			// Remove all double quotes and split the row by DELIMITER
			String[] row = file.nextLine().replace("\"", "").split(DELIMITER);

			if (labelIndex == 0) { // read the first name (labels)
				for (String label : row) labels.put(label, labelIndex++);
			} else {
				items++;

				if (row.length != labelIndex)
					throw new ItemWithIncorrectNumberOfAttributesException(items, labelIndex, row.length);

				content.add(row);
			}
		}
	}
}
