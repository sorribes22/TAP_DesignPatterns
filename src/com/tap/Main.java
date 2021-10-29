package com.tap;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.impl.CsvDataFrame;
import com.tap.query.IQuery;
import com.tap.query.Operator;
import com.tap.query.StringComparison;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

	private static Scanner file;

	private static DataFrame dataFrame;

	public static void main(String[] args) {
		String filename = "files/Prova.csv";

		//DataFrameFactory factory = new DataFrameFactory(filename);
		openFile(filename);

		try {
			dataFrame = new CsvDataFrame();
			dataFrame.loadContent(file);
		} catch (ItemWithIncorrectNumberOfAttributesException e) {
			e.printStackTrace();
		}

		System.out.println(dataFrame);

		IQuery<Map<String, String>> query = new StringComparison("Nom", Operator.EQUALS, "Àlex");
		System.out.println(dataFrame.query(query));
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
	 *
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
