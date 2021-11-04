package com.tap.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.impl.TxtDataFrame;

public class TxtDataFrameFactory implements DataFrameFactory{

    public DataFrame makeDataFrame() throws ItemWithIncorrectNumberOfAttributesException {
        return new TxtDataFrame();
    }
}
