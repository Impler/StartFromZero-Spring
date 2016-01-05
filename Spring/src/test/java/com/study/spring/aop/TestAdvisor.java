package com.study.spring.aop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.study.spring.aop.Waiter;
import com.study.spring.aop.advisor.Seller;
import com.study.spring.aop.advisor.controlflow.WaiterDelegate;
import com.study.spring.test.BaseTestObject;

public class TestAdvisor  extends BaseTestObject{
	
	@Override
	protected String getConfigFileName() {
		return "aop.xml";
	}
	
	@Before
	public void init(){
		System.out.println("****test advisor start****");
	}
	
	@After
	public void end(){
		System.out.println("****test advisor end****");
	}
	
	/**
	 * static method matched advisor
	 */
	@Test
	public void testStaticMMAdvisor(){
		Waiter w = (Waiter) super.getBean("staticMMWaiter");
		Seller s = (Seller) super.getBean("staticMMSeller");
		w.greetTo("TOM");
		s.greetTo("Kitty");
	}
	
	
	/**
	 * regex method meatched advisor
	 */
	@Test
	public void testRegexMMAdvisor(){
		Waiter w = (Waiter) super.getBean("regexWaiter");
		w.greetTo("TOM");
		w.serveTo("TOM");
	}
	
	/**
	 * dynamic advisor
	 */
	@Test
	public void testDynamicAdvisor(){
		Waiter w = (Waiter) super.getBean("dynamicWaiter");
		w.greetTo("Kitty");
		w.greetTo("TOM");
	}
	
	/**
	 * control flow advisor
	 */
	@Test
	public void testControlFlowAdvisor(){
		Waiter w = (Waiter) super.getBean("controlFWaiter");
		WaiterDelegate delegate = new WaiterDelegate();
		delegate.setWaiter(w);
		delegate.service("TOM");
	}
	
	/**
	 * composable advisor
	 */
	@Test
	public void testComposableAdvisor(){
		Waiter w = (Waiter) super.getBean("composableWaiter");
		WaiterDelegate delegate = new WaiterDelegate();
		delegate.setWaiter(w);
		delegate.service("TOM");
	}
	
}
