package com.tap.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Observer implements InvocationHandler {

	public static final String ANY = "*";

	private final Object target;

	private final Map<String, List<Listener>> subscriptors = new HashMap<>();

	public Observer(Object target) {
		this.target = target;
	}

	public void listenFor(String method, Class<?> listenerClass) {
		if (!subscriptors.containsKey(method)) subscriptors.put(method, new ArrayList<>());

		try {
			Class[] cArg = {Object.class};
			Listener listener = (Listener) listenerClass.getDeclaredConstructor(cArg).newInstance(target);

			subscriptors.get(method).add(listener);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		List<Listener> iterable = subscriptors.get(method.getName());
		if (iterable != null) for (Listener listener : iterable) listener.update(method.getName(), args);

		iterable = subscriptors.get(ANY);
		if (iterable != null) for (Listener listener : iterable) listener.update(method.getName(), args);

		return method.invoke(target, args);
	}

	// https://www.youtube.com/watch?v=T3VucYqdoRo&ab_channel=ChristopherOkhravi
	@SuppressWarnings("unchecked")
	public <T> T watch(Class<T> itf) {
		return (T) Proxy.newProxyInstance(
			itf.getClassLoader(),
			new Class<?>[]{itf},
			this
		);
	}
}
