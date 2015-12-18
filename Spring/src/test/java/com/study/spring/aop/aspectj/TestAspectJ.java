package com.study.spring.aop.aspectj;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import com.study.spring.aop.Waiter;
import com.study.spring.aop.WaiterA;
import com.study.spring.test.BaseTestObject;

public class TestAspectJ extends BaseTestObject{
	
	@Test
	public void testAspectByCode(){
		Waiter waiter = new WaiterA();
		
		AspectJProxyFactory factory = new AspectJProxyFactory();
		
		//设置目标对象
		factory.setTarget(waiter);
		
		//添加切面
		factory.addAspect(BeforeGreetingAspect.class);
		
		//生成代理对象
		Waiter proxy = factory.getProxy();
		
		proxy.greetTo("TOM");
		proxy.serveTo("TOM");
	}
	
	@Test
	public void testAspectByConfig(){
		Waiter waiter = (Waiter) super.getBean("waiter");
		waiter.greetTo("TOM");
		waiter.serveTo("TOM");
	}
	
	/**
	 * AOP schema
	 */
	@Test
	public void testAspectBySchemaConfig(){
		Waiter waiter = (Waiter) super.getBean("waiter");
		waiter.greetTo("TOM");
		waiter.serveTo("TOM");
	}
}
