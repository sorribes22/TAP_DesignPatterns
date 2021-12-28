package com.tap.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Observer implements InvocationHandler {
	private Map<String, List<Listener>> subscriptors;

	private Object target;

	public Observer(Object target) {
		this.target = target;
	}


	public void listenFor(String method, Listener listener) {
		if (!subscriptors.containsKey(method)) subscriptors.put(method, new ArrayList<>());

		subscriptors.get(method).add(listener);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		for (Listener listener : subscriptors.get(method.getName())) {
			listener.update(args);
		}

		return method.invoke(target, args);
	}

	// https://www.youtube.com/watch?v=T3VucYqdoRo&ab_channel=ChristopherOkhravi
	@SuppressWarnings("unchecked")
	public static <T> T watch(T target, Class<T> itf) {
		return (T) Proxy.newProxyInstance(
			itf.getClassLoader(),
			new Class<?>[] {itf},
			new Observer(target)
		);
	}
}
