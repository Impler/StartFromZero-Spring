package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._01static_method._02regex_method_name;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Seller;
import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring.test.BaseTestObject;

public class RegexMethodNameMatchedAdvisorTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/02advisor/01static_method/02regex_method_name_advisor.xml";
	}

	@Test
	public void test() {
		Waiter w = (Waiter) super.getBean("regexWaiter");
		Seller s = (Seller) super.getBean("regexSeller");
		//matched method
		w.greetTo("TOM");
		//unmatched method
		w.serveTo("TOM");
		//matched method
		s.greetTo("Kitty");
	}

}
