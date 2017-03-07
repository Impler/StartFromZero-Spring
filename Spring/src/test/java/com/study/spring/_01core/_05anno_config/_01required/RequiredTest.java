package com.study.spring._01core._05anno_config._01required;

import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;

import com.study.spring.test.BaseTestObject;

public class RequiredTest extends BaseTestObject{


	@Override
	protected String getConfigFileName() {
		return "config/01core/05anno_config/01@Required/beans-anno-required.xml";
	}

	@Test
	public void testRequired() {
		super.getContext();
	}
	
	/**
	 * 没有为@Required属性赋值，将会抛出BeanCreationException异常
	 */
	@Test(expected=BeanCreationException.class)
	public void testNorRequired(){
		super.getContext().getBean("badFoo");
	}
}
