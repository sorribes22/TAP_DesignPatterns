package com.tap.handler;

import java.lang.reflect.InvocationHandler;

public abstract class Listener {
	protected final Object target;

	public Listener(Object target) {
		this.target = target;
	}

	public abstract void update(Object[] args);
}
