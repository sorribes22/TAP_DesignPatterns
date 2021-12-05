package com.tap.dataframe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImplResolver {
	static final String filePath = "impl_resolver.json";

	private Map<String, String> map = new HashMap<>();

	private Scanner scanner;

	public ImplResolver() {
		this.reload();
	}

	public void reload() {
		openFile();

		read();

		scanner.close();
	}

	public String factoryFromExtension(String extension) {
		return map.get(extension);
	}

	private void openFile() {
		File pointer = new File(filePath);

		try {
			scanner = new Scanner(pointer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void read() {
		StringBuilder content = new StringBuilder();

		while (scanner.hasNextLine()) {
			content.append(scanner.nextLine());
		}

		JsonObject root = JsonParser.parseString(content.toString()).getAsJsonObject();
		JsonObject relations = root.getAsJsonObject("extension_factories");

		for (String key : relations.keySet()) {
			map.put(key, relations.get(key).getAsString());
		}
	}
}
