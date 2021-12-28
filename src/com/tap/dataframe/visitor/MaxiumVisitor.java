package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;

public class MaxiumVisitor extends DataFrameVisitor {


    private double max = Double.MIN_VALUE;


    @Override
    public void calculate(double actual) {
        if (actual > max) max = actual;
    }

    @Override
    public double getResult() {
        return max;
    }
}
