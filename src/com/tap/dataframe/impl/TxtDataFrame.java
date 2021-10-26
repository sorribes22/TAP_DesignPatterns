package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TxtDataFrame extends DataFrame {

	public TxtDataFrame(Scanner scanner) throws ItemWithIncorrectNumberOfAttributesException {
		this.loadContent(scanner);
	}
}
