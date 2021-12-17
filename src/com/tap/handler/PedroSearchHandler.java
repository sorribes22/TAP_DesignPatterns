package com.tap.handler;

import com.tap.dataframe.query.Query;
import com.tap.dataframe.query.Operator;
import com.tap.dataframe.query.StringComparison;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class PedroSearchHandler implements InvocationHandler {
	protected final Object target;

	public PedroSearchHandler(Object target) {
		this.target = target;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();

		if (name.equals("query")) {
			Query<Map<String, String>> query = (Query<Map<String, String>>) args[0];
			Query<Map<String, String>> expected = new StringComparison("user", Operator.EQUALS, "pedro");

			if (query.equals(expected)) {
				System.out.println("Somebody is searching for Pedro");
			}
		}

		return method.invoke(target, args);
	}
}
