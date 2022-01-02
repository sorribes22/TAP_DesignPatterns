package com.tap.observer;

public abstract class Listener {
	/**
	 * Object to listen for.
	 */
	protected final Object target;

	public Listener(Object target) {
		this.target = target;
	}

	/**
	 * Called every time observed method of target is invoked.
	 *
	 * @param method method called
	 * @param args   arguments of the method called
	 */
	public abstract void update(String method, Object[] args);
}
