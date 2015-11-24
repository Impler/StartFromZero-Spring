package com.study.spring.aop;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring.aop.before.GreetingBeforeAdvice;
import com.study.spring.aop.before.Waiter;
import com.study.spring.aop.before.WaiterA;
import com.study.spring.test.BaseTestObject;

public class TestBeforeAdvice extends BaseTestObject{

	@Test
	public void testBeforeByHardCode() {
		Waiter target = new WaiterA();
		BeforeAdvice advice = new GreetingBeforeAdvice();

		ProxyFactory pf = new ProxyFactory();

		pf.setTarget(target);
		pf.addAdvice(advice);

		Waiter proxy = (Waiter) pf.getProxy();

		proxy.greetTo("Tom");
		proxy.serveTo("Tom");
	}
	
	@Test
	public void testBeforeByConfigure(){
		Waiter waiter = (Waiter) super.getBean("waiter");
		waiter.greetTo("Kitty");
		waiter.serveTo("Kitty");
	}
}
