package com.study.spring.aop.advice.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("waiter walk to guest...");
		Object returns = invocation.proceed();
		System.out.println("waiter walk away...");
		return returns;
	}

}
