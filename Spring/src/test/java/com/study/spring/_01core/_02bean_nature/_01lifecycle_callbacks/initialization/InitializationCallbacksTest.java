package com.study.spring._01core._02bean_nature._01lifecycle_callbacks.initialization;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class InitializationCallbacksTest extends BaseTestObject {


	@Override
	protected String getConfigFileName() {
		return "config/01core/02bean-nature/01lifecycle-callbacks/beans-lifecycle-callbacks-initialization.xml";
	}
	
	@Test
	public void testInitializingBean() {
		super.getBean("initializingBean");
	}
	
	@Test
	public void testInitMethodBean() {
		super.getBean("initMethodBean");
	}
	
	@Test
	public void testAllCallbacksInOne() {
		super.getBean("allInOne");
	}

}
