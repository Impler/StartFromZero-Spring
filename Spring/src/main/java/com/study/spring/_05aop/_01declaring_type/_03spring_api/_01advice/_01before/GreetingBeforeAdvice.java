package com.study.spring._05aop._01declaring_type._03spring_api._01advice._01before;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		String clientName = (String) arg1[0];
		System.out.println("advice msg: How are you " + clientName);
	}

}
