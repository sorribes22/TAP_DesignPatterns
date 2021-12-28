package com.tap.dataframe.visitor;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.impl.DirectoryDataFrame;

public abstract class DataFrameVisitor {

    protected String labelToApply = "Code";

    public void visit(DataFrame df) {
        if (df.getContent().containsKey(labelToApply)) {
            for (String value : df.getContent().get(labelToApply)) {
                try {
                    calculate(Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    System.out.println("NO NUMBER FORMAT: "+value);

                }
            }
        } else {
            System.out.println("LABEL " + labelToApply + " NOT FOUND IN "+df.getContent().keySet());

        }

    }

    public void visitDirectory(DirectoryDataFrame dirDf) {
        // System.out.println("Entrem a carpeta");
        for (DataFrame elem : dirDf.getChildrens()) {
            elem.accept(this);
        }
    }

    public abstract void calculate(double actual);

    public abstract double getResult();

}
