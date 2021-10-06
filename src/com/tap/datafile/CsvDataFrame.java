package com.tap.datafile;

import java.util.Scanner;

public class CsvDataFrame extends DataFrame {
	// TODO ASK hem de fer-lo variable?
	final String DELIMITER = ";";

	public CsvDataFrame(Scanner file) {
		int i = 0;

		while (file.hasNextLine()) {
			String[] row = file.nextLine().split(DELIMITER);

			if (i == 0) { // read the first name (labels)
				for (String label : row) labels.put(label, i++);
			} else {
				if (row.length != i) {}// TODO raise exception

				content.add(row);
			}
		}
	}
}
