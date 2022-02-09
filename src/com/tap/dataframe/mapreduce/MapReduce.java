package com.tap.dataframe.mapreduce;

import com.tap.dataframe.StringDataFrame;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class MapReduce {
	/**
	 * Applies custom map & reduce methods over a column of dataframe.
	 * @param dataFrame Target
	 * @param label Column
	 * @param map Map logic
	 * @param reduce Reduce logic
	 * @return Result of map & reduce
	 */
	public static Object over(
		StringDataFrame dataFrame,
		String label,
		Function<Object, Object> map,
		BinaryOperator<Object> reduce) {

		return dataFrame.getColumnContent(label).parallelStream().map(map).reduce(reduce);
	}
}
