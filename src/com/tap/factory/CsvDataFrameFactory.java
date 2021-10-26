package com.tap.factory;

import com.tap.dataframe.CsvDataFrame;
import com.tap.dataframe.DataFrame;

public class CsvDataFrameFactory implements DataFrameFactory {


    public DataFrame makeDataFrame() {
        return new CsvDataFrame().loadContent();
    }
}
