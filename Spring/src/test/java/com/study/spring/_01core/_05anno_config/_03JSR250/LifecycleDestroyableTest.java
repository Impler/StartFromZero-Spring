package com.study.spring._01core._05anno_config._03JSR250;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class LifecycleDestroyableTest extends BaseTestObject{


	@Override
	protected String getConfigFileName() {
		return "config/01core/05anno_config/03JSR250/03@PreDestroy/beans-anno-pre-destroy-config.xml";
	}

	/**
	 * 生命周期方法，以及其执行顺序
	 */
	@Test
	public void test() {
		
	}
}
