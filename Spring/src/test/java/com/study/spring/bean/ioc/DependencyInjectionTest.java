package com.study.spring.bean.ioc;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class DependencyInjectionTest extends BaseTestObject{
	@Test
	public void test() {
		DependencyInjection di = (DependencyInjection) super.getBean("DIBean");
		System.out.print(di.getSetterBasedUser());
	}

}
