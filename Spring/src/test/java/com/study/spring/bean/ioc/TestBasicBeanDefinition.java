package com.study.spring.bean.ioc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.study.spring.base.domain.User;
import com.study.spring.test.BaseTestObject;

public class TestBasicBeanDefinition extends BaseTestObject{
	
	@Override
	protected String getConfigFileName() {
		return "beans-base.xml";
	}

	@Before
	public void init(){
		System.out.println("****test DI start****");
	}
	
	@After
	public void end(){
		System.out.println("****test DI end****");
	}
	
	@Test
	//setter injection
	public void testSetterInjection() {
		DependencyInjection di = (DependencyInjection) super.getBean("setterDIBean");
		System.out.print(di.getSetterBasedUser());
	}
	
	//constructor injection s
	@Test
	//injection by argument type
	public void testConstInjection1(){
		DependencyInjection di = (DependencyInjection) super.getBean("constDIBean1");
		System.out.println(di.getConstBasedUser());
	}
	
	@Test
	//arguments with the same type
	public void testConstInjection2(){
		DependencyInjection di = (DependencyInjection) super.getBean("constDIBean2");
		System.out.println(di.getConstBasedUser());
	}
	@Test
	//arguments with the same type, use index to avoid ambiguity 
	public void testConstInjection3(){
		DependencyInjection di = (DependencyInjection) super.getBean("constDIBean3");
		System.out.println(di.getConstBasedUser());
	}
	@Test
	//more complication, use index and type together 
	public void testConstInjection4(){
		DependencyInjection di = (DependencyInjection) super.getBean("constDIBean4");
		System.out.println(di.getConstBasedUser());
	}
	
	@Test
	//use constructor parameter name
	public void testConstInjection5(){
		DependencyInjection di = (DependencyInjection) super.getBean("constDIBean5");
		System.out.println(di.getConstBasedUser());
	}
	//constructor injection e
	//factory method injection s
	@Test
	//non static factory method injection
	public void testNonStaticFactoryInjection(){
		User u = (User) super.getBean("factoryDIBean");
		System.out.print(u);
	}
	
	@Test
	//static factory method injection
	public void testStaticFactoryInjection(){
		User u = (User) super.getBean("staticFactoryDIBean");
		System.out.print(u);
	}
	//factory method injection e
	
	//method injection s
	@Test
	public void testInjectPB2SBBaseOnSpring(){
		InjectPB2SBBaseOnSpring bean = (InjectPB2SBBaseOnSpring) super.getBean("singletonBean1");
		System.out.print(bean.getPrototypeUser() == bean.getPrototypeUser());
	}
	
	@Test
	public void testInjectPB2SBBaseOnDynamicProxy(){
		InjectPB2SBBaseOnDynamicProxy bean = (InjectPB2SBBaseOnDynamicProxy) super.getBean("singletonBean2");
		System.out.print(bean.getUser() == bean.getUser());
	}
	//method injection e
	@Test
	public void testIdref(){
		User u = (User) super.getBean("idrefBean");
		System.out.println(u);
	}
	
	@Test
	public void testInnerBean(){
		DependencyInjection di = (DependencyInjection) super.getBean("innerBean");
		System.out.println(di.getSetterBasedUser());
	}

	@Test
	public void testDependsOn(){
		super.getBean("dependsOnBean");
	}

}