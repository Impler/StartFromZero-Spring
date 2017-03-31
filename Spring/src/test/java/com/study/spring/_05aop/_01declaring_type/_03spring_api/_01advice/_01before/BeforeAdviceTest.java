package com.study.spring._05aop._01declaring_type._03spring_api._01advice._01before;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring._05aop._01declaring_type._03spring_api.WaiterA;
import com.study.spring.test.BaseTestObject;

public class BeforeAdviceTest extends BaseTestObject {

	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/01advice/01before-advice.xml";
	}

	@Test
	public void testBeforeByHardCode() {
		Waiter target = new WaiterA();
		BeforeAdvice advice = new GreetingBeforeAdvice();

		ProxyFactory pf = new ProxyFactory();

		pf.setTarget(target);
		pf.addAdvice(advice);

		Waiter proxy = (Waiter) pf.getProxy();

		proxy.greetTo("Tom");
	}

	@Test
	public void test() {
		Waiter waiter = (Waiter) super.getBean("beforeWaiter");
		waiter.greetTo("Tom");
	}
}
