package com.tap.dataframe.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.impl.JsonDataFrame;

public class JsonDataFrameFactory implements DataFrameFactory{
    public DataFrame makeDataFrame() {
        return new JsonDataFrame();
    }
}
