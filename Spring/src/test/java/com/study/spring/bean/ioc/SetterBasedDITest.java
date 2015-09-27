package com.study.spring.bean.ioc;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class SetterBasedDITest extends BaseTestObject{
	@Test
	public void test() {
		SetterBasedDI di = (SetterBasedDI) super.getBean("setterBasedDI");
		System.out.print(di.getU());
	}

}
