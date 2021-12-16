//package com.tap.handler;
//
//import java.lang.reflect.InvocationHandler;
//
//public abstract class Handler implements InvocationHandler {
//	protected final Object target;
//
//	public Handler(Object target) {
//		this.target = target;
//	}
//
//	static <T> T listen(T target, Class<T> itf) {
//		return (T) static.createProxy();
//	}
//
//	abstract <T> T createProxy(T target, Class<T> itf);
//}
