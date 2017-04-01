package com.study.spring._05aop._01declaring_type._03spring_api._01advice._05introduction;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;
import com.study.spring.test.BaseTestObject;

public class IntroductionAdviceTest extends BaseTestObject{
	
	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/01advice/05introduction-advice.xml";
	}

	@Test
	public void test() {
		Waiter waiter = (Waiter) super.getBean("introWaiter");
		waiter.serveTo("Tom");
		// 调用引介方法
		((IDoOthers)waiter).settleAccounts();
	}

}
