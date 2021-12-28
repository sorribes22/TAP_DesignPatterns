package com.tap.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggingHandler extends Listener {

	public LoggingHandler(Object target) {
		super(target);
	}

	public void update(Object[] args) {
//		System.out.format("%s@%s.%s(%s)\n", target.getClass(), Integer.toHexString(target.hashCode()), name, Arrays.toString(args));
	}
}
