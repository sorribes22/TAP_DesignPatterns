package com.tap.dataframe.exception;

public class ItemWithIncorrectAttributeException extends InvalidFileFormatException {

	public ItemWithIncorrectAttributeException(int item, String expectedAttribute) {
		super("Item #%d not expected to have \"%s\" attribute.".formatted(item, expectedAttribute));
	}
}
