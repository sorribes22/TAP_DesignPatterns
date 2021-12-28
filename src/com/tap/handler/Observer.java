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

	/**
	 * Adds a listener for a method.
	 *
	 * @param method        method name to listen for
	 * @param listenerClass Listener class to run when the method is invoked
	 */
	public void listenFor(String method, Class<?> listenerClass) {
		if (!subscriptors.containsKey(method)) subscriptors.put(method, new ArrayList<>());

		try {
			Class[] cArg = {Object.class};
			Listener listener = (Listener) listenerClass.getDeclaredConstructor(cArg).newInstance(target);

			subscriptors.get(method).add(listener);
		} catch (ReflectiveOperationException ignored) {
		}
	}

	/**
	 * Processes a method invocation on a proxy instance and returns the result.
	 *
	 * @param proxy  the proxy instance that the method was invoked on
	 * @param method Method instance has been called
	 * @param args   method arguments
	 * @return target method invocation return
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		List<Listener> iterable = subscriptors.get(method.getName());
		if (iterable != null) for (Listener listener : iterable) listener.update(method.getName(), args);

		iterable = subscriptors.get(ANY);
		if (iterable != null) for (Listener listener : iterable) listener.update(method.getName(), args);

		return method.invoke(target, args);
	}

	/**
	 * Creates a proxy instance for the target.
	 *
	 * @param itf inteface of the method invocations to handle
	 */
	@SuppressWarnings("unchecked")
	public <T> T watch(Class<T> itf) {
		return (T) Proxy.newProxyInstance(
			itf.getClassLoader(),
			new Class<?>[]{itf},
			this
		);
	}
}
