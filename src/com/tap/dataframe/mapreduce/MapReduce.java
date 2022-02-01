package com.tap.dataframe.mapreduce;

import com.tap.dataframe.StringDataFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class MapReduce {
	public static Object over(
		List<StringDataFrame> dataFrames,
		String label,
		Function<Object, Object> map,
		BinaryOperator<Object> reduce) {

		List<Object> result = new ArrayList<>();

		dataFrames
			.parallelStream()
			.forEach(dF -> result.addAll(dF.getColumnContent(label)));

		return result.parallelStream().map(map).reduce(reduce);
	}

	public static Object over(
		StringDataFrame dataFrame,
		String label,
		Function<Object, Object> map,
		BinaryOperator<Object> reduce) {

		return dataFrame.getColumnContent(label).parallelStream().map(map).reduce(reduce);
	}
}
