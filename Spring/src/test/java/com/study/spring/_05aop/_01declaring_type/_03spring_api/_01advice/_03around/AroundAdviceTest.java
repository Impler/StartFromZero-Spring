package com.study.spring._05aop._01declaring_type._03spring_api._01advice._03around;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring.test.BaseTestObject;

public class AroundAdviceTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/01advice/03around-advice.xml";
	}

	@Test
	public void test() {
		Waiter waiter = (Waiter) super.getBean("aroundWaiter");
		waiter.greetTo("Tom");
	}
}
