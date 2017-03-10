package com.study.spring._01core._05anno_config._02autowired._01qualifiers;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class QualifierTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/01core/05anno_config/02@Autowired/01@Qualifier/beans-anno-autowired-qualifier.xml";
	}
	
	@Test
	public void testQualifier() {
		Foo foo = (Foo) super.getBean("foo");
		System.out.println(foo);
	}


}
