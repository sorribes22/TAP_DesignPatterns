package com.tap.dataframe;

public class ItemWithIncorrectAttributeException extends Exception {

	public ItemWithIncorrectAttributeException(int item, String expectedAttribute) {
		super("Item #%d not expected to have \"%s\" attribute.".formatted(item, expectedAttribute));
	}
}
