package com.tap.dataframe.exception;

public class ItemWithIncorrectNumberOfAttributesException extends InvalidFileFormatException {

	public ItemWithIncorrectNumberOfAttributesException(int item, int expectedAttributes, int realAttributes) {
		super("Item #%d expected to be %d attributes long. Found %d".formatted(item, expectedAttributes, realAttributes));
	}
}
