package com.tap;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.exception.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.factory.CsvDataFrameFactory;
import com.tap.dataframe.factory.DataFrameFactory;
import com.tap.dataframe.factory.DirectoryDataFrameFactory;
import com.tap.dataframe.impl.DirectoryDataFrame;
import com.tap.dataframe.query.Operator;
import com.tap.dataframe.query.Query;
import com.tap.dataframe.query.StringComparison;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static Scanner file;

	private static DataFrame dataFrame;

	public static void main(String[] args) {
		String filename = "files/Prova.csv";
		DataFrameFactory factory = new CsvDataFrameFactory();
		File file = new File(filename);
		try {
			dataFrame = factory.makeDataFrame();
			dataFrame.loadContent(file);
			Query<Map<String, String>> query = new StringComparison("Altura", Operator.GREATER, "1.87");
			System.out.println(dataFrame.query(query));
		} catch (ItemWithIncorrectNumberOfAttributesException e) {
			e.printStackTrace();
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
//
//		System.out.println(dataFrame);
//
//		IQuery<Map<String, String>> query = new StringComparison("Nom", Operator.EQUALS, "Àlex");
//		System.out.println(dataFrame.query(query));
//
//		Comparator<String> comparator = new NumberAscending();
//		System.out.println(dataFrame.sort("Altura", comparator));
//
//		Comparator<String> comparator2 = new NumberDescending();
//		System.out.println(dataFrame.sort("Altura", comparator2));

//		String filename = "files/Prova.json";
//
//		//DataFrameFactory factory = new DataFrameFactory(filename);
//		openFile(filename);
//
//		try {
//			dataFrame = new JsonDataFrame();
//			dataFrame.loadContent(file);
//		} catch (InvalidFileFormatException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println(dataFrame);
//		System.out.println(dataFrame.getContent().get("Nom").get(1));

//		IQuery<Map<String, String>> query = new StringComparison("Nom", Operator.EQUALS, "Àlex");
//		System.out.println(dataFrame.query(query));
//
//		Comparator<String> comparator = new NumberAscending();
//		System.out.println(dataFrame.sort("Altura", comparator));
//
//		Comparator<String> comparator2 = new NumberDescending();
//		System.out.println(dataFrame.sort("Altura", comparator2));

	}

	private void loadDirectory() {
		String directory = "./files";
		File directoryPointer = new File(directory);
		DataFrameFactory factory = new DirectoryDataFrameFactory();

		try {
			DataFrame directoryDF = new DirectoryDataFrame();
			directoryDF.loadContent(directoryPointer);

			System.out.println(directoryDF.size());

			System.out.println(directoryDF.at(10, "Description"));

		} catch (ItemWithIncorrectNumberOfAttributesException e) {
			e.printStackTrace();
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
