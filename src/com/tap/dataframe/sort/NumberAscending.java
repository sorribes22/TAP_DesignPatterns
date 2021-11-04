package com.tap.dataframe.sort;

import java.util.Comparator;

public class NumberAscending implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return Double.valueOf(o1).compareTo(Double.valueOf(o2));
	}
}
