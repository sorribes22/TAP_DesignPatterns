package test.tap.datafile.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.exception.InvalidFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FakeDataFrame extends DataFrame {
	@Override
	public void loadContent(File file) throws InvalidFileFormatException, FileNotFoundException {

	}

	public void fillTestContent(Map<String, List<String>> content) {
		this.content = content;
		labels = new ArrayList<>(content.keySet());
		if (labels.size() > 0) size = content.get(labels.get(0)).size();
	}
}
