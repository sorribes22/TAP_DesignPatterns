package com.tap.dataframe.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tap.dataframe.DataFrame;
import com.tap.dataframe.exception.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonDataFrame extends DataFrame {

	public JsonDataFrame() {

	}

	@Override
	public void loadContent(File file) throws ItemWithIncorrectNumberOfAttributesException, ItemWithIncorrectAttributeException, FileNotFoundException {
		StringBuilder content = new StringBuilder();
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			content.append(scanner.nextLine());
		}

		JsonArray jsonArray = JsonParser.parseString(content.toString()).getAsJsonArray();
		JsonObject item;
		boolean firstRow = true;

		for (JsonElement i : jsonArray) {
			item = i.getAsJsonObject();

			if (firstRow) {
				for (String label : item.keySet()) {
					labels.add(label);

					this.content.put(label, new ArrayList<>());

					firstRow = false;
				}
			} else if (labels.size() != item.size())
				throw new ItemWithIncorrectNumberOfAttributesException(this.size + 1, labels.size(), item.size());

			for (String label : item.keySet()) {
				try {
					this.content.get(label).add(String.valueOf(item.get(label)));
				} catch (NullPointerException e) {
					throw new ItemWithIncorrectAttributeException(this.size + 1, label);
				}
			}

			this.size++;
		}
	}
}

