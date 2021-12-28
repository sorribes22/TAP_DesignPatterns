package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ImplResolver;
import com.tap.dataframe.exception.InvalidFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Optional;

public class DirectoryDataFrame extends DataFrame {

	private ArrayList<DataFrame> childrens = new ArrayList<>();

	private ImplResolver implResolver = new ImplResolver();

	public DirectoryDataFrame() {

	}

	public void loadContent(File file) {
		loadDirectory(file, this);
	}

	/**
	 * Expected to be called on a directory, not on a file
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

					dataFrame.loadContent(item);

					root.add(dataFrame);
				} catch (ClassNotFoundException e) {
					System.out.format("Could not resolve file extension: '%s'\n", extension);
				} catch (InvalidFileFormatException e) {
					System.out.println(e.getMessage());
				} catch (FileNotFoundException e) {
					// todo enhancement
					e.printStackTrace();
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void add(DataFrame children) {
		childrens.add(children);
	}

	public ArrayList<DataFrame> childrens() {
		return this.childrens;
	}

	// TODO Override DataFrame methods


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
