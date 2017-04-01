package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._02dynamic;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring.test.BaseTestObject;

public class DynamicAdvisorTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/02advisor/02dynamic/01dynamic_advisor.xml";
	}

	@Test
	public void test() {
		Waiter w = (Waiter) super.getBean("dynamicWaiter");
		//matched parameter value
		w.greetTo("TOM");
		//unmatched parameter value
		w.greetTo("Kitty");
	}

}
