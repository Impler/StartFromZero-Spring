package com.study.spring._01core._02bean_nature._01lifecycle_callbacks.initialization;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class DestructionCallbacksTest extends BaseTestObject {

	@Override
	protected String getConfigFileName() {
		return "config/01core/02bean-nature/01lifecycle-callbacks/beans-lifecycle-callbacks-destruction.xml";
	}

	@Test
	public void testDisposableBean() {
		super.getContext().destroy();
	}
	
	@Test
	public void testDestroyMethodBean(){
		super.getContext().destroy();
	}
	
	@Test
	public void testAllCallbacksInOne(){
		super.getContext().destroy();
	}
}
