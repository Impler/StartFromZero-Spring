package com.study.spring._05aop._01declaring_type._03spring_api._01advice._03around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("advice msg: waiter walk to guest...");
		Object returns = invocation.proceed();
		System.out.println("advice msg: waiter walk away...");
		return returns;
	}

}
