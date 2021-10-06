package com.tap;

import com.tap.datafile.CsvDataFrame;
import com.tap.datafile.DataFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class Main {

	private static Scanner file;

	private static DataFrame dataFrame;

	public static void main(String[] args) {
		String filename = "files/Prova.csv";

		openFile(filename);

		switch (fileExtension(filename)) {
			case "csv":
				// TODO abstract factory
				dataFrame = new CsvDataFrame(file);
				break;
		}

		System.out.println(dataFrame);
	}

	/**
	 * @author https://www.w3schools.com/java/java_files_read.asp
	 * @param filename
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
