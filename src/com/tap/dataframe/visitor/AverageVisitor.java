package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.impl.DirectoryDataFrame;

public class AverageVisitor extends DataFrameVisitor {

    private double sum = 0;
    private int count = 0;

    @Override
    public void calculate(double actual) {
        sum = sum + actual;
        count++;

    }

    @Override
    public double getResult() {
        return count == 0 ? count : (sum / count);
    }
}
