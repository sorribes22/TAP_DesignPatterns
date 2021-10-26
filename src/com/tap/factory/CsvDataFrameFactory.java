package com.tap.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.impl.CsvDataFrame;

public class CsvDataFrameFactory implements DataFrameFactory {
    public DataFrame makeDataFrame() {
        return new CsvDataFrame();
    }
}
