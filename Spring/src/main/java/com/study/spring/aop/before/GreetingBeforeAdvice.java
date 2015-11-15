package com.study.spring.aop.before;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		String clientName = (String) arg1[0];
		System.out.println("How are you " + clientName);
	}

}
