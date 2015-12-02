package com.study.spring.aop;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring.aop.advice.Waiter;
import com.study.spring.aop.advice.WaiterA;
import com.study.spring.aop.advice.before.GreetingBeforeAdvice;
import com.study.spring.aop.advice.introduction.IDoOthers;
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
	
	/**
	 * 前置增强
	 */
	@Test
	public void testBeforeByConfigure(){
		Waiter waiter = (Waiter) super.getBean("beforeWaiter");
		waiter.greetTo("Kitty");
		waiter.serveTo("Kitty");
	}
	
	/**
	 * 后置增强
	 */
	@Test
	public void testAfter(){
		Waiter waiter = (Waiter) super.getBean("afterWaiter");
		waiter.serveTo("Tom");
	}
	
	/**
	 * 环绕增强
	 */
	@Test
	public void testAround(){
		Waiter waiter = (Waiter) super.getBean("aroundWaiter");
		waiter.serveTo("Tom");
	}
	
	/**
	 * 异常抛出增强
	 */
	@Test
	public void testThrowException(){
		Waiter waiter = (Waiter) super.getBean("expWaiter");
		try {
			waiter.cleanTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 异常抛出增强
	 */
	@Test
	public void testIntroduction(){
		Waiter waiter = (Waiter) super.getBean("introWaiter");
		waiter.serveTo("Tom");
		((IDoOthers)waiter).settleAccounts();
	}
}
