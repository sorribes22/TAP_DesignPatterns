package test.tap.datafile.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.exception.InvalidFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;

public class FakeDataFrame extends DataFrame {
	@Override
	public void loadContent(File file) throws InvalidFileFormatException, FileNotFoundException {

	}
}
