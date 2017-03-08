package com.study.spring._01core._02bean_nature._01lifecycle_callbacks._02destruction;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class DestructionCallbacksTest extends BaseTestObject {

	@Override
	protected String getConfigFileName() {
		return "config/01core/02bean-nature/01lifecycle-callbacks/beans-lifecycle-callbacks-destruction.xml";
	}

	/**
	 * 实现DisposableBean接口
	 */
	@Test
	public void testDisposableBean() {
		super.getContext().destroy();
	}
	
	/**
	 * 配置destroy-method
	 */
	@Test
	public void testDestroyMethodBean(){
		super.getContext().destroy();
	}
	
	/**
	 * 既实现DisposableBean接口又配置destroy-method
	 */
	@Test
	public void testAllCallbacksInOne(){
		super.getContext().destroy();
	}
}
