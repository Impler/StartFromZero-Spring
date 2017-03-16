package com.study.spring._05aop._00dynamic_proxy._02cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


public class BarServiceMethodInterceptor implements MethodInterceptor{

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("before service");
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("after service");
		return result;
	}
	
}
