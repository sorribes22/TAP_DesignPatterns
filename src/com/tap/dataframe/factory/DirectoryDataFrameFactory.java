package com.tap.dataframe.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.impl.DirectoryDataFrame;

public class DirectoryDataFrameFactory implements DataFrameFactory {
	public DataFrame makeDataFrame() {
		return new DirectoryDataFrame();
	}
}
