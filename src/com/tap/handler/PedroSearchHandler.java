package com.tap.handler;

import com.tap.dataframe.query.IQuery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class PedroSearchHandler implements InvocationHandler {
	protected final Object target;

	public PedroSearchHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();

		if (name.equals("query")) {
			IQuery<Map<String, String>>
		}
		System.out.format("%s@%s.%s(%s)\n", target.getClass(), Integer.toHexString(target.hashCode()), name, Arrays.toString(args));

		return method.invoke(target, args);
	}
}
