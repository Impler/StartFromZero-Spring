package com.study.spring.bean.core;


import org.junit.Test;

import com.study.spring.base.domain.User;
import com.study.spring.test.BaseTestObject;

public class TestBeanCoreAPI extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "beans-core.xml";
	}

	@Test
	public void testBeanPostProcessor(){
		super.getBean("simpleUser");
	}
	
	@Test
	public void testBeanFactoryPostProcessor(){
		User user = (User) super.getBean("simpleUser");
		System.out.println(user);
	}
}
