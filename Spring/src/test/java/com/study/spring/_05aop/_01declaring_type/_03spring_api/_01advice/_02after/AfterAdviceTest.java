package com.study.spring._05aop._01declaring_type._03spring_api._01advice._02after;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api._01advice.Waiter;
import com.study.spring.test.BaseTestObject;

public class AfterAdviceTest extends BaseTestObject{

	
	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/01advice/02after-advice.xml";
	}
	
	@Test
	public void test() {
		Waiter waiter = (Waiter) super.getBean("afterWaiter");
		waiter.greetTo("Tom");
	}

}
