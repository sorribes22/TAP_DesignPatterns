package com.tap.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingHandler implements InvocationHandler {
	protected final Object target;

	public LoggingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();

		System.out.format("%s@%s.%s(%s)\n", target.getClass(), Integer.toHexString(target.hashCode()), name, Arrays.toString(args));

		return method.invoke(target, args);
	}
}
