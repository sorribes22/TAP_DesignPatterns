package com.tap;

import com.tap.dataframe.exception.*;
import com.tap.dataframe.factory.*;
import com.tap.dataframe.query.*;
import com.tap.dataframe.DataFrame;
import com.tap.dataframe.StringDataFrame;
import com.tap.dataframe.impl.DirectoryDataFrame;
import com.tap.dataframe.mapreduce.MapReduce;
import com.tap.dataframe.observer.impl.PedroSearchHandler;
import com.tap.dataframe.sort.NumberAscending;
import com.tap.observer.Observer;
import com.tap.observer.impl.LoggingHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private static Scanner file;

	private static DataFrame dataFrame;

	public static void main(String[] args) {
//		String filename = "files/Prova.csv";
//
//		//DataFrameFactory factory = new DataFrameFactory(filename);
//		openFile(filename);
//
//		try {
//			dataFrame = new CsvDataFrame();
//			dataFrame.loadContent(file);
//		} catch (ItemWithIncorrectNumberOfAttributesException e) {
//			e.printStackTrace();
//		}
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

//
//		try {
//			String filename = "files/DamienLook3.txt";
//			File doc = new File(filename);
//			DataFrameFactory factory = new TxtDataFrameFactory();
//			DataFrame dataFrame = factory.makeDataFrame();
//			dataFrame.loadContent(doc);
//			System.out.println(dataFrame);

//			/*
//			DataFrameVisitor v = new AverageVisitor();
//			directoryDF.accept(v);
//			System.out.println("El result es: " + v.getResult());
//			*/


		/* ***********************************************************************
		 * Uncomment method calls in order to check how the functionalities work *
		 *************************************************************************/

		/* PART 1 */
//		dataFrame();
//
		/* PART 2 */
//		composite();

		/* PART 4 */
//		mapReduce();

		/* PART 5 */
//		visitor();

		/* PART 6 */
//		observerDynamicProxy();
	}

	private static void dataFrame() {
		DataFrameFactory factory = new CsvDataFrameFactory();
		StringDataFrame dataFrame = factory.makeDataFrame();

		try {
			dataFrame.loadContent(new File("files/DimenLookupEthnic8277.csv"));
		} catch (InvalidFileFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.printf("EXPECTED return of dataFrame.at(2, \"Description\") is \"Maori\".\nRESULT: %s\n",
			dataFrame.at(2, "Description"));
		System.out.printf("EXPECTED return of dataFrame.iat(7) is \"Maori\".\nRESULT: %s\n", dataFrame.iat(2, 1));
		System.out.printf("EXPECTED return of dataFrame.size() is 11.\nRESULT: %s\n", dataFrame.size());
		System.out.printf("EXPECTED return of dataFrame.sort(\"Code\", new NumberAscending()) is [1, 2, 3, 4, 5, 6, 9," +
			" 61, 69, 77, 9999].\nRESULT: %s\n", dataFrame.sort("Code", new NumberAscending()));
		System.out.printf("EXPECTED return of dataFrame.query(new IntComparasion(\"Code\", Operator.GREATER, \"70\"))" +
			" is ... .\nRESULT: %s\n", dataFrame.query(new IntComparasion("Code", Operator.GREATER, "70")));
	}

	private static void composite() {
		File directoryPointer = new File("files");
		DataFrameFactory factory = new DirectoryDataFrameFactory();
		DirectoryDataFrame dataFrame = (DirectoryDataFrame) factory.makeDataFrame();

		dataFrame.loadContent(directoryPointer);

		System.out.println(dataFrame.getChildren());
	}

	private static void mapReduce() {
		DataFrameFactory factory = new CsvDataFrameFactory();

		StringDataFrame dataFrame = factory.makeDataFrame();
		try {
			dataFrame.loadContent(new File("files/DimenLookupYear8277.csv"));
		} catch (InvalidFileFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}

		Object result = MapReduce.over(dataFrame,
			"Code",
			item -> Integer.parseInt((String) item) + 1,
			(first, second) -> (int) first + (int) second);

		System.out.println(result);
	}

	private static void visitor() {
		// TODO Roger
	}

	private static void observerDynamicProxy() {
		File filePointer = new File("files/DimenLookupYear8277.csv");
		DataFrameFactory factory = new CsvDataFrameFactory();

		Observer dataFrameObserver = new Observer(factory.makeDataFrame());
		dataFrameObserver.listenFor(Observer.ANY, LoggingHandler.class);
		dataFrameObserver.listenFor("query", PedroSearchHandler.class);
		StringDataFrame directoryDF = dataFrameObserver.watch(StringDataFrame.class);

		try {
			directoryDF.loadContent(filePointer);
		} catch (InvalidFileFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}

		StringComparison comparator = new StringComparison("user", Operator.EQUALS, "pedro");
		directoryDF.query(comparator);
	}
}
