package com.study.spring._01core._02bean_nature._01lifecycle_callbacks._01initialization;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class InitializationCallbacksTest extends BaseTestObject {


	@Override
	protected String getConfigFileName() {
		return "config/01core/02bean-nature/01lifecycle-callbacks/beans-lifecycle-callbacks-initialization.xml";
	}
	
	/**
	 * 实现InitializingBean
	 */
	@Test
	public void testInitializingBean() {
		super.getBean("initializingBean");
	}
	
	/**
	 * 配置init-method方法
	 */
	@Test
	public void testInitMethodBean() {
		super.getBean("initMethodBean");
	}
	
	/**
	 * 既实现InitializingBean又配置init-method方法
	 */
	@Test
	public void testAllCallbacksInOne() {
		super.getBean("allInOne");
	}

}
