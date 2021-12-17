package com.tap.dataframe;

import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.query.Query;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface StringDataFrame extends Iterable<Map<String, String>> {
	void loadContent(File file) throws InvalidFileFormatException, FileNotFoundException;

	Object at(int row, String label);

	Object iat(int row, int column);

	int columns();

	int size();

	List<String> sort(String column, Comparator<String> comparator);

	Map<String, List<String>> query(Query<Map<String, String>> condition);

	Map<String, List<String>> getContent();
}
