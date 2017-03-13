package com.study.spring._01core._05anno_config._04components._01inner_beans;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class ComponentInnerBeanTest extends BaseTestObject{


	@Override
	protected String getConfigFileName() {
		return "config/01core/05anno_config/04components/01@Bean/beans-anno-components-config.xml";
	}

	/**
	 * 在@Component中使用@Bean
	 */
	@Test
	public void test() {
		Foo foo = (Foo) super.getBean("foo");
		// 直接方法调用
		System.out.println(foo.getBar());
		
		// 调用spring bean（单例）
		System.out.println(super.getBean("bar"));
		System.out.println(super.getBean("bar"));
	}
}
