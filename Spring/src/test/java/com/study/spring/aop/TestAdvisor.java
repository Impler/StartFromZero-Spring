package com.study.spring.aop;

import org.junit.Test;

import com.study.spring.aop.advice.Waiter;
import com.study.spring.aop.advisor.Seller;
import com.study.spring.test.BaseTestObject;

public class TestAdvisor  extends BaseTestObject{
	
	@Test
	public void testStaticMMAdvisor(){
		Waiter w = (Waiter) super.getBean("staticMMWaiter");
		Seller s = (Seller) super.getBean("staticMMSeller");
		w.greetTo("TOM");
		s.greetTo("Kitty");
	}

}
