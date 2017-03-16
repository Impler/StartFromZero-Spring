package com.study.spring._05aop._00dynamic_proxy._02cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


public class BarServiceMethodInterceptor implements MethodInterceptor{

	private Enhancer enhancer = new Enhancer();
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> clazz){
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("before service");
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("after service");
		return result;
	}
	
}
