package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;

import java.util.ArrayList;
import java.util.Scanner;

public class DirectoryDataFrame extends DataFrame {

	private ArrayList<DataFrame> childrens = new ArrayList<>();

	public DirectoryDataFrame() {
	}

	public void loadContent(Scanner scanner) {
	}

	public void add(DataFrame children) {
		childrens.add(children);
	}

	public ArrayList<DataFrame> childrens() {
		return this.childrens;
	}
}
