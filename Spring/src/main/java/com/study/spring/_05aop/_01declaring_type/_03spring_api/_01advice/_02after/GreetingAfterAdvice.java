package com.study.spring._05aop._01declaring_type._03spring_api._01advice._02after;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class GreetingAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("advice msg: Please enjoy yourself!!");
	}

}
