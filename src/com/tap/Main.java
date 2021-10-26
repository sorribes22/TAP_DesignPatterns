package com.tap;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.DataFrameFactory;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;
import com.tap.query.IQuery;
import com.tap.query.StringComparison;

import java.util.Map;

public class Main {

	private static DataFrame dataFrame;

	public static void main(String[] args) {
		String filename = "files/Prova.csv";

		DataFrameFactory factory = new DataFrameFactory(filename);

		try {
			dataFrame = factory.loadDataFrame();
		} catch (ItemWithIncorrectNumberOfAttributesException e) {
			e.printStackTrace();
		}

		System.out.println(dataFrame);

		IQuery<Map<String, Object>> query = new StringComparison("Nom", '=', "Àlex");
		System.out.println(dataFrame.query(query));
	}
}
