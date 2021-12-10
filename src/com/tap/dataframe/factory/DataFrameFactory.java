package com.tap.dataframe.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.exception.ItemWithIncorrectNumberOfAttributesException;

public interface DataFrameFactory {

	DataFrame makeDataFrame() throws ItemWithIncorrectNumberOfAttributesException;


}
