package com.tap.dataframe.factory;


import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ImplResolver;
import com.tap.dataframe.exception.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.impl.CsvDataFrame;
import com.tap.dataframe.impl.DirectoryDataFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Scanner;

public class DirectoryDataFrameFactory implements DataFrameFactory {
	private File filePointer;

	private ImplResolver implResolver = new ImplResolver();

	/**
	 *
	 * @param filePointer Directory pointer
	 */
	public DirectoryDataFrameFactory(File filePointer) {
		this.filePointer = filePointer;
	}

	public DataFrame makeDataFrame() {
		return loadDirectory(filePointer, new DirectoryDataFrame());
	}

	/**
	 * Expected to be called on a directory, not on a file
	 * @param directory
	 * @return
	 */
	public DataFrame loadDirectory(File pointer, DirectoryDataFrame root) {
		DataFrame directory = new DirectoryDataFrame();

		for (File item : pointer.listFiles()) {
			if (pointer.isDirectory()) {
				root.add(loadDirectory(item, root));
			} else {
				String extension = fileExtension(item.getName());
				try {
					Class<?> factoryImpl = Class.forName(implResolver.factoryFromExtension(extension));
					Method method = factoryImpl.getDeclaredMethod("makeDataFrame");
					Object object = method.invoke(factoryImpl.newInstance());
					DataFrame dataFrame = (DataFrame) object;

					root.add(dataFrame.loadContent(item));
				} catch (ClassNotFoundException e) {
					System.out.format("Could not resolve file extension: '%s'\n", extension);
				} catch (ItemWithIncorrectNumberOfAttributesException e) {
					e.printStackTrace();
				}
			}
		}

		return directory;
	}

	/**
	 * @param filename
	 * @author https://www.w3schools.com/java/java_files_read.asp
	 */
	private static Scanner openFile(String filename) {
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
