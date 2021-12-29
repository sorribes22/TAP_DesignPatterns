package com.tap.dataframe.factory;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.impl.TxtDataFrame;

public class TxtDataFrameFactory implements DataFrameFactory {
	public DataFrame makeDataFrame() {
		return new TxtDataFrame();
	}
}
