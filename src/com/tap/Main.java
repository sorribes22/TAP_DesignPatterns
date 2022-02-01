package com.tap;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.StringDataFrame;
import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.exception.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.factory.DataFrameFactory;
import com.tap.dataframe.factory.DirectoryDataFrameFactory;
import com.tap.dataframe.factory.TxtDataFrameFactory;
import com.tap.dataframe.impl.TxtDataFrame;
import com.tap.dataframe.observer.impl.PedroSearchHandler;
import com.tap.dataframe.impl.DirectoryDataFrame;
import com.tap.dataframe.query.IntComparasion;
import com.tap.dataframe.query.Operator;
import com.tap.dataframe.query.StringComparison;
import com.tap.dataframe.visitor.AverageVisitor;
import com.tap.dataframe.visitor.DataFrameVisitor;
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

		String directory = "./files";
		File directoryPointer = new File(directory);

		try {
			String filename = "files/DamienLook3.txt";
			File doc = new File(filename);
			DataFrameFactory factory = new TxtDataFrameFactory();
			DataFrame dataFrame = factory.makeDataFrame();
			dataFrame.loadContent(doc);
			System.out.println(dataFrame);




//			StringDataFrame directoryDF = withLogging(factory.makeDataFrame(), StringDataFrame.class);
//			StringDataFrame directoryDF = whosSearchingForPedro(factory.makeDataFrame(), StringDataFrame.class);
			//Observer dataFrameObserver = new Observer(factory.makeDataFrame());
			//dataFrameObserver.listenFor(Observer.ANY, LoggingHandler.class);
			//dataFrameObserver.listenFor("query", PedroSearchHandler.class);
			DataFrame directoryDF = new DirectoryDataFrame();
//			directoryDF.listenFor("query", new PedroSearchHandler(target));
			directoryDF.loadContent(directoryPointer);
			//System.out.println(directoryDF.size());
			System.out.println(directoryDF.query(new IntComparasion("Code", Operator.GREATER_OR_EQUAL, "2000")));
			//System.out.println("hola");
			System.out.println(directoryDF);
			/*
			DataFrameVisitor v = new AverageVisitor();
			directoryDF.accept(v);
			System.out.println("El result es: " + v.getResult());
			*/


		} catch (ItemWithIncorrectNumberOfAttributesException e) {
			e.printStackTrace();
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}
}

	/*
	// https://www.youtube.com/watch?v=T3VucYqdoRo&ab_channel=ChristopherOkhravi
	@SuppressWarnings("unchecked")
	private static <T> T initObserver(T target, Class<T> itf) {
		return (T) Proxy.newProxyInstance(
			itf.getClassLoader(),
			new Class<?>[] {itf},
			new Observer(target)
		);
	}
*/
//	// https://www.youtube.com/watch?v=T3VucYqdoRo&ab_channel=ChristopherOkhravi
//	@SuppressWarnings("unchecked")
//	private static <T> T withLogging(T target, Class<T> itf) {
//		return (T) Proxy.newProxyInstance(
//			itf.getClassLoader(),
//			new Class<?>[] {itf},
//			new LoggingHandler(target)
//		);
//	}
//
//	// https://www.youtube.com/watch?v=T3VucYqdoRo&ab_channel=ChristopherOkhravi
//	@SuppressWarnings("unchecked")
//	private static <T> T whosSearchingForPedro(T target, Class<T> itf) {
//		return (T) Proxy.newProxyInstance(
//			itf.getClassLoader(),
//			new Class<?>[] {itf},
//			new PedroSearchHandler(target)
//		);
//	}

