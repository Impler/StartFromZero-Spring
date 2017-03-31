package com.study.spring._05aop._01declaring_type._03spring_api._01advice._04throwexp;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring.test.BaseTestObject;

public class ThrowsAdviceTest extends BaseTestObject{


	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/01advice/04throwexp-advice.xml";
	}

	@Test
	public void test() {
		Waiter waiter = (Waiter) super.getBean("expWaiter");
		try {
			waiter.cleanTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
