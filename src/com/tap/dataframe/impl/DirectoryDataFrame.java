package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ImplResolver;
import com.tap.dataframe.StringDataFrame;
import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.query.Query;
import com.tap.dataframe.visitor.DataFrameVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.*;

public class DirectoryDataFrame extends DataFrame {

	private ArrayList<StringDataFrame> childrens = new ArrayList<>();

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

	/**
	 * Accept a visitor
	 *
	 * @param v
	 */
	public void accept(DataFrameVisitor v) {
		v.visitDirectory(this);

	}

	public ArrayList<StringDataFrame> getChildrens() {
		return childrens;
	}


	public String at(int row, String label) {
		int size;

		for (StringDataFrame children : this.getChildrens()) {
			size = children.size();
			if (row > size) row -= size;
			else return children.at(row, label);
		}

		return null;
	}


	/**
	 * Do a query in the structure
	 *
	 * @param condition Query object with  the attribute, operator and match)
	 * @return Map with item that fulfill  the condition
	 */
	@Override
	public Map<String, List<String>> query(Query<Map<String, String>> condition) {
		Map<String, List<String>> result = new HashMap<>();
		Map<String, List<String>> result2 = new HashMap<>();

		for (StringDataFrame df : this.childrens) {
			result2 = df.query(condition);
			if (result.isEmpty() && !(df instanceof DirectoryDataFrame)) {
				for (String label : df.getLabels())
					result.put(label, new ArrayList<>());
			}
			for (String label : result2.keySet()) {
				result.get(label).addAll(result2.get(label));
			}
		}
		return result;
	}

	@Override
	public List<String> getColumnContent(String column) {
		List<String> result = new ArrayList<>();

		childrens.parallelStream().forEach(dF -> result.addAll(dF.getColumnContent(column)));

		return result;
	}

	/**
	 * Size of the Composite structure
	 *
	 * @return number of items
	 */
	public int size() {
		int totalSize = 0;
		for (StringDataFrame df : childrens) {
			totalSize = totalSize + df.size();
		}
		return totalSize;
	}

}