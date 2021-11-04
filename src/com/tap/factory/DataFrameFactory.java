package com.tap.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;

public interface DataFrameFactory {

    DataFrame makeDataFrame () throws ItemWithIncorrectNumberOfAttributesException;


}
