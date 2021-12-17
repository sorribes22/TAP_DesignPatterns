package com.tap.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public abstract class Handler implements InvocationHandler {
	protected final Object target;

	public Handler(Object target) {
		this.target = target;
	}

	static <T> T listen(T target, Class<T> itf) {
		return (T) Proxy.newProxyInstance(
			itf.getClassLoader(),
			new Class<?>[] {itf},
			new LoggingHandler(target)
		);
	}

	abstract <T> T createProxy(T target, Class<T> itf);
}
