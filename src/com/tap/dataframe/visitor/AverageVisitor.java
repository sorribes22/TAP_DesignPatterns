package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.impl.DirectoryDataFrame;

public class AverageVisitor extends DataFrameVisitor {

	private double sum = 0;
	private int count = 0;

	/**
	 * Summatory operation
	 *
	 * @param actual number to operate
	 */
	@Override
	public void calculate(double actual) {
		sum = sum + actual;
		count++;

	}

	/**
	 * Calculate average of the data
	 *
	 * @return Average or 0 if de count is 0
	 */
	@Override
	public double getResult() {
		return count == 0 ? count : (sum / count);
	}
}
