package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._04composable;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Seller;
import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring._05aop._01declaring_type._03spring_api._02advisor._03control_flow.WaiterDelegate;
import com.study.spring.test.BaseTestObject;

public class ComposablePointcutTest extends BaseTestObject {

	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/02advisor/04composable/01composable_advisor.xml";
	}

	@Test
	public void test() {
		Waiter w = (Waiter) super.getBean("composableWaiter");
		Seller s = (Seller) super.getBean("composableSeller");
		WaiterDelegate delegate = new WaiterDelegate();
		delegate.setWaiter(w);
		delegate.setSeller(s);
		delegate.service("TOM");
	}

}
