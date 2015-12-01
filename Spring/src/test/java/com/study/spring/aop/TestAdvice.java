package com.study.spring.aop;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring.aop.advice.Waiter;
import com.study.spring.aop.advice.WaiterA;
import com.study.spring.aop.advice.before.GreetingBeforeAdvice;
import com.study.spring.test.BaseTestObject;

public class TestAdvice extends BaseTestObject{

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
		Waiter waiter = (Waiter) super.getBean("beforeWaiter");
		waiter.greetTo("Kitty");
		waiter.serveTo("Kitty");
	}
	
	@Test
	public void testAfter(){
		Waiter waiter = (Waiter) super.getBean("afterWaiter");
		waiter.serveTo("Tom");
	}
}
