package com.tap.dataframe;

public class ItemWithIncorrectNumberOfAttributesException extends Exception {

	public ItemWithIncorrectNumberOfAttributesException(int item, int expectedAttributes, int realAttributes) {
		super("Item #%d expected to be %d attributes long. Found %d".formatted(item, expectedAttributes, realAttributes));
	}
}
