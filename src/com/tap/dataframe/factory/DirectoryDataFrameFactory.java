package com.tap.dataframe.factory;


import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ImplResolver;
import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.impl.DirectoryDataFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Scanner;

public class DirectoryDataFrameFactory implements DataFrameFactory {
	private File filePointer;

	private ImplResolver implResolver = new ImplResolver();

	/**
	 * @param filePointer Directory pointer
	 */
	public DirectoryDataFrameFactory(File filePointer) {
		this.filePointer = filePointer;
	}

	public DataFrame makeDataFrame() {
		DirectoryDataFrame root = new DirectoryDataFrame();

		loadDirectory(filePointer, root);

		return root;
	}

	/**
	 * Expected to be called on a directory, not on a file
	 *
	 * @return
	 */
	public void loadDirectory(File pointer, DirectoryDataFrame root) {
		for (File item : pointer.listFiles()) {
			if (item.isDirectory()) {
				DirectoryDataFrame directory = new DirectoryDataFrame();
				loadDirectory(item, directory);
				root.add(directory);
			} else {
				String extension = fileExtension(item.getName());
				try {
					String factoryNamespace = implResolver.factoryFromExtension(extension);
					if (factoryNamespace == null) continue;

					Class<?> factoryImpl = Class.forName(factoryNamespace);
					Method method = factoryImpl.getDeclaredMethod("makeDataFrame");
					Object object = method.invoke(factoryImpl.getDeclaredConstructor().newInstance());
					DataFrame dataFrame = (DataFrame) object;

					Scanner scanner = new Scanner(item);
					dataFrame.loadContent(scanner);

					root.add(dataFrame);
				} catch (ClassNotFoundException e) {
					System.out.format("Could not resolve file extension: '%s'\n", extension);
				} catch (InvalidFileFormatException e) {
					System.out.println(e.getMessage());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
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
