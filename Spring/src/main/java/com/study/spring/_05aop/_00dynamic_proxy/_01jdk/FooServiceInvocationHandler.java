package com.study.spring._05aop._00dynamic_proxy._01jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FooServiceInvocationHandler implements InvocationHandler {

	private Object target;


	public FooServiceInvocationHandler(Object target) {
		this.target = target;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before doService");
		Object result = method.invoke(target, args);
		System.out.println("after doService");
		return result;
	}
}
