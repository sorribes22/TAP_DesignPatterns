package com.tap.dataframe.handler.impl;

import com.tap.dataframe.query.Query;
import com.tap.dataframe.query.Operator;
import com.tap.dataframe.query.StringComparison;
import com.tap.handler.Listener;

import java.util.Map;

public class PedroSearchHandler extends Listener {

	public PedroSearchHandler(Object target) {
		super(target);
	}

	@SuppressWarnings("unchecked")
	public void update(String method, Object[] args) {
		Query<Map<String, String>> query = (Query<Map<String, String>>) args[0];
		Query<Map<String, String>> expected = new StringComparison("user", Operator.EQUALS, "pedro");

		if (query.equals(expected)) {
			System.out.println("Somebody is searching for Pedro");
		}
	}
}
