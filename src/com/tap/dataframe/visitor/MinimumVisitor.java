package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;

public class MinimumVisitor extends DataFrameVisitor {

    private double min = Double.MAX_VALUE;

    /**
     * Maximum operation
     * @param actual number to operate
     */
    public void calculate(double actual) {
        if (actual < min) min = actual;
    }

    @Override
    public double getResult() {
        return min;
    }
}
