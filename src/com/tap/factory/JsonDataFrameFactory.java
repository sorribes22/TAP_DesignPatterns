package com.tap.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.impl.JsonDataFrame;

public class JsonDataFrameFactory implements DataFrameFactory {
    public DataFrame makeDataFrame() throws ItemWithIncorrectNumberOfAttributesException {
        return new JsonDataFrame();
    }
}
