package com.study.spring.bean.ioc;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class DependencyInjectionTest extends BaseTestObject{
	@Test
	//setter injection
	public void testSetterInjection() {
		DependencyInjection di = (DependencyInjection) super.getBean("SetterDIBean");
		System.out.print(di.getSetterBasedUser());
	}
	
	@Test
	//injection by argument type
	public void testConstInjection1(){
		DependencyInjection di = (DependencyInjection) super.getBean("ConstDIBean1");
		System.out.print(di.getConstBasedUser());
	}
	
	@Test
	//arguments with the same type
	public void testConstInjection2(){
		DependencyInjection di = (DependencyInjection) super.getBean("ConstDIBean2");
		System.out.print(di.getConstBasedUser());
	}
	@Test
	//arguments with the same type, use index to avoid ambiguity 
	public void testConstInjection3(){
		DependencyInjection di = (DependencyInjection) super.getBean("ConstDIBean3");
		System.out.print(di.getConstBasedUser());
	}
	@Test
	//more complication, use index and type together 
	public void testConstInjection4(){
		DependencyInjection di = (DependencyInjection) super.getBean("ConstDIBean4");
		System.out.print(di.getConstBasedUser());
	}
	
	@Test
	//non static factory method injection
	public void testNonStaticFactoryInjection(){
		User u = (User) super.getBean("FactoryDIBean");
		System.out.print(u);
	}
	
	@Test
	//static factory method injection
	public void testStaticFactoryInjection(){
		User u = (User) super.getBean("StaticFactoryDIBean");
		System.out.print(u);
	}
}
