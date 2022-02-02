package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;

public class MaximumVisitor extends DataFrameVisitor {


	private double max = Double.MIN_VALUE;


	/**
	 * Maximum operation
	 *
	 * @param actual number to operate
	 */
	@Override
	public void calculate(double actual) {
		if (actual > max) max = actual;
	}

	@Override
	public double getResult() {
		return max;
	}
}
