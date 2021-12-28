package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;

public class MiniumVisitor extends DataFrameVisitor {

    private double min = Double.MAX_VALUE;

    public void calculate(double actual) {
        if (actual < min) min = actual;
    }

    @Override
    public double getResult() {
        return min;
    }
}
