package com.tap.dataframe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImplResolver {
	static final String FILE_PATH = "impl_resolver.json";

	private static final String EXTENSION_FACTORY_SECTION = "extension_factories";

	private Map<String, String> map = new HashMap<>();

	private Scanner scanner;

	public ImplResolver() {
		this.reload();
	}

	/**
	 * Refresh map implementation from FILE_PATH file.
	 */
	public void reload() {
		openFile();

		read();

		scanner.close();
	}

	/**
	 * Resolve map implementation based on file extension.
	 * @param extension file extension
	 * @return namespace from factory implementation
	 */
	public String factoryFromExtension(String extension) {
		return map.get(extension);
	}

	/**
	 * Initializes scanner to FILE_PATH file.
	 */
	private void openFile() {
		File pointer = new File(FILE_PATH);

		try {
			scanner = new Scanner(pointer);
		} catch (FileNotFoundException e) {
			System.out.format("File %s not found.\n", FILE_PATH);
		}
	}

	/**
	 *
	 */
	private void read() {
		StringBuilder content = new StringBuilder();

		while (scanner.hasNextLine()) {
			content.append(scanner.nextLine());
		}

		JsonObject root = JsonParser.parseString(content.toString()).getAsJsonObject();
		JsonObject relations = root.getAsJsonObject(EXTENSION_FACTORY_SECTION);

		for (String key : relations.keySet()) {
			map.put(key, relations.get(key).getAsString());
		}
	}
}
