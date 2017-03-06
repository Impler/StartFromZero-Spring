package com.study.spring._01core._01ioc._04dependency_injection;

import org.junit.Test;

import com.study.spring._01core._01ioc._04dependency_injection.method_injection.FooApplicationContextAware;
import com.study.spring._01core._01ioc._04dependency_injection.method_injection.FooLookup;
import com.study.spring.test.BaseTestObject;

public class MethodInjectionTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/01core/01ioc/04dependency-injection/beans-dependency-injection-method-injection.xml";
	}

	@Test
	public void testMethodInjectionByApplicationContextAware(){
		FooApplicationContextAware foo = (FooApplicationContextAware) super.getBean("fooAware");
		// compare the bar object reference at every invoke
		foo.call();
		foo.call();
	}
	
	@Test
	public void testMethodInjectionByLookUp(){
		FooLookup foo = (FooLookup) super.getBean("fooLookUp");
		// compare the bar object reference at every invoke
		foo.call();
		foo.call();
	}
}
