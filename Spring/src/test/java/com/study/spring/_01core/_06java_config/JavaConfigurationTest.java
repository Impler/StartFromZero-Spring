package com.study.spring._01core._06java_config;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.spring._01core._06java_config._01lookup.BarConfiguration;
import com.study.spring._01core._06java_config._01lookup.Foo;

public class JavaConfigurationTest{

	private ApplicationContext context;
	
	@Test
	public void testJavaConfiguration() {
		context = new AnnotationConfigApplicationContext(FooConfiguration.class);
		// @Configuration 也将作为spring bean(通过CGLIB实现的代理类)
		System.out.println(context.getBean("fooConfiguration"));
		System.out.println(context.getBean(Bar.class));
		System.out.println(context.getBean(Baz.class));
	}
	
	@Test
	public void testJavaConfigurationLookUp(){
		
		context = new AnnotationConfigApplicationContext(BarConfiguration.class);
		Foo foo = context.getBean(Foo.class);
		System.out.println(context.getBean(Foo.class));
		System.out.println(foo);
		System.out.println(foo.getBar());
		System.out.println(foo.getBar());
	}
}
