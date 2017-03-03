package com.study.spring._01core._01ioc._03bean_instantiation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring._01core._01ioc._03bean_instantiation.Foo;
import com.study.spring._01core._01ioc._03bean_instantiation.Foo.Bar;

public class BeanInstantiationTest {
	
	private ClassPathXmlApplicationContext context;

	@Before
	public void before(){
		context = new ClassPathXmlApplicationContext(
				"config/01core/01ioc/03bean-instantiation/beans-base-instantiation.xml");
	}

	@After
	public void after(){
		context.close();
	}
	
	// instantiation by default constructor
	@Test
	public void testByDefaultConstructor() {
		Foo foo = context.getBean("foo", Foo.class);
		foo.test();
	}
	
	// instantiation by constructor with parameters
	@Test
	public void testByConstructorWithParameters(){
		Foo foo = context.getBean("fooWithParamConstructor", Foo.class);
		foo.test();
	}
	
	// instantiation by static factory method
	@Test
	public void testByStaticFactoryMethod(){
		Foo foo = context.getBean("fooByStaticFactoryMethod", Foo.class);
		foo.test();
	}
	
	// instantiation by factory method
	@Test
	public void testByFactoryMethod(){
		Foo foo = context.getBean("fooByFactoryMethod", Foo.class);
		foo.test();
	}
	// instantiation inner static class
	@Test
	public void testInnerStaticClassBean(){
		Bar bar = context.getBean("innerStaticClassBean", Bar.class);
		bar.test();
	}

}
