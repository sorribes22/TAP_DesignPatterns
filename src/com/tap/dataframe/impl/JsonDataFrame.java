package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;

import java.util.Scanner;

public class JsonDataFrame extends DataFrame {

	public JsonDataFrame(Scanner scanner) throws ItemWithIncorrectNumberOfAttributesException {
		this.loadContent(scanner);
	}
}
