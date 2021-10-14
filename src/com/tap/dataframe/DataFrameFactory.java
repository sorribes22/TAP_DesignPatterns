package com.tap.dataframe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class DataFrameFactory {
	private static Scanner file;

	private String filename;

	public DataFrameFactory(String filename) {
		this.filename = filename;
	}

	public DataFrame loadDataFrame() throws ItemWithIncorrectNumberOfAttributesException {
		openFile(filename);


		// TODO ASK Al implementar Composite hem de seguir utilitzant la mateixa factory?
		switch (fileExtension(filename)) {
			case "csv":
				return new CsvDataFrame(file);
			default:
				return null;
		}
	}


	/**
	 * @param filename
	 * @author https://www.w3schools.com/java/java_files_read.asp
	 */
	private static void openFile(String filename) {
		File pointer = new File(filename);
		try {
			file = new Scanner(pointer);
		} catch (FileNotFoundException e) {
			// TODO
			e.printStackTrace();
		}
		// TODO raise exception if .exists() or .canRead() returns false

	}

	/**
	 * // TODO ASK s'hauria de comprovar el mimetype?
	 *
	 * @param filename Name of the file
	 * @return File extension
	 * @author https://frontbackend.com/java/how-to-get-extension-of-a-file-in-java
	 */
	private static String fileExtension(String filename) {
		return Optional.of(filename)
			.filter(f -> f.contains("."))
			.map(f -> f.substring(filename.lastIndexOf(".") + 1))
			.orElse("");
	}
}
