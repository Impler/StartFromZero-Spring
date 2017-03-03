package com.study.spring._01core._01ioc._04dependency_injection;

import junit.framework.Assert;

import org.junit.Test;

import com.study.spring._01core._01ioc._04dependency_injection.domain.Foo;
import com.study.spring._01core._01ioc._04dependency_injection.domain.User;
import com.study.spring.test.BaseTestObject;

public class DependencyInjectionTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/01core/01ioc/04dependency-injection/beans-dependency-injection.xml";
	}
	
	// constructor-based - different constructor argument types
	@Test
	public void testConstructorByDifferentArgTypes() {
		Foo foo = (Foo) super.getBean("foo");
		Assert.assertNotNull(foo.getBar());
		Assert.assertNotNull(foo.getBaz());
	}
	
	// constructor-based - different simple types of constructor argument types
	@Test
	public void testConstructorBySimpleType(){
		User user = (User) super.getBean("user");
		System.out.println(user);
	}


	// constructor-based - more complex case, use 'index' attribute
	@Test
	public void testConstructorByIndexAttribute(){
		User user = (User) super.getBean("userComplex");
		System.out.println(user);
	}
	
	// Setter-based
	@Test
	public void testSetter(){
		Foo foo = (Foo) super.getBean("fooSetter");
		Assert.assertNotNull(foo.getBar());
		Assert.assertNotNull(foo.getBaz());
	}
}
