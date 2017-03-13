package com.study.spring._01core._05anno_config._03JSR250;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class LifecycleInitializationTest extends BaseTestObject{


	@Override
	protected String getConfigFileName() {
		return "config/01core/05anno_config/03JSR250/02@PostConstruct/beans-anno-post-construct-config.xml";
	}

	/**
	 * 生命周期方法，以及其执行顺序
	 */
	@Test
	public void test() {
	}
}
