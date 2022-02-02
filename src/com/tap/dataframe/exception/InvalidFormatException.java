package com.tap.dataframe.exception;

public class InvalidFormatException extends InvalidFileFormatException {
	public InvalidFormatException(int item) {
		super("Item #%d has invalid format".formatted(item));
	}

}
