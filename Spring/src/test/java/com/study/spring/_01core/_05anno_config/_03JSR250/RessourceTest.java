package com.study.spring._01core._05anno_config._03JSR250;

import org.junit.Test;

import com.study.spring._01core._05anno_config._03JSR250._01resource.Foo;
import com.study.spring.test.BaseTestObject;

public class RessourceTest extends BaseTestObject{


	@Override
	protected String getConfigFileName() {
		return "config/01core/05anno_config/03JSR250/01@Resource/beans-anno-resource-config.xml";
	}

	@Test
	public void testResource() {
		Foo foo = (Foo) super.getBean("foo");
		System.out.println(foo);
	}
}
