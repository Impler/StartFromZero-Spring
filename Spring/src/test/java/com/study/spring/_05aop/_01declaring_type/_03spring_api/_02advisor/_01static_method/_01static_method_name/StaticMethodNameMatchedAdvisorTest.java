package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._01static_method._01static_method_name;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Seller;
import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring.test.BaseTestObject;

public class StaticMethodNameMatchedAdvisorTest extends BaseTestObject {

	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/02advisor/01static_method/01static_method_name_advisor.xml";
	}

	@Test
	public void test() {
		Waiter w = (Waiter) super.getBean("staticMMWaiter");
		Seller s = (Seller) super.getBean("staticMMSeller");
		//matched class and matched method
		w.greetTo("TOM");
		//matched class but unmatched method
		w.serveTo("TOM");
		//unmatched class
		s.greetTo("Kitty");
	}

}
