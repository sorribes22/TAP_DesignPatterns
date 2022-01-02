package com.tap.observer.impl;

import com.tap.observer.Listener;

import java.util.Arrays;

public class LoggingHandler extends Listener {

	public LoggingHandler(Object target) {
		super(target);
	}

	public void update(String method, Object[] args) {
		System.out.format("%s@%s.%s(%s)\n", target.getClass(), Integer.toHexString(target.hashCode()), method, Arrays.toString(args));
	}
}
