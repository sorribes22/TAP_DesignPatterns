package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;

public class SumVisitor extends DataFrameVisitor {

    private double sum = 0;


    /**
     * Summatory operation
     * @param actual number to operate
     */
    @Override
    public void calculate(double actual) {
        sum = sum + actual;
    }

    @Override
    public double getResult() {
        return sum;
    }
}

