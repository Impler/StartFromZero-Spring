package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._03control_flow;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Seller;
import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring.test.BaseTestObject;

public class ControlFlowPointcutTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/02advisor/03control_flow/01control_flow_advisor.xml";
	}

	@Test
	public void test() {
		Waiter w = (Waiter) super.getBean("controlFWaiter");
		Seller s = (Seller) super.getBean("controlFSeller");
		WaiterDelegate delegate = new WaiterDelegate();
		delegate.setWaiter(w);
		delegate.setSeller(s);
		delegate.service("TOM");
	}


}
