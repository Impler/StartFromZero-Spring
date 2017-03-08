package com.study.spring._01core._05anno_config._02autowired;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class AutowiredTest extends BaseTestObject{


	@Override
	protected String getConfigFileName() {
		return "config/01core/05anno_config/02@Autowired/beans-anno-autowired.xml";
	}

	/**
	 * 自动注入依赖
	 */
	@Test
	public void testAutowired() {
		Foo foo = (Foo) super.getContext().getBean("foo");
		System.out.println(foo);
	}
	
	/**
	 * 自动注入集合类型依赖
	 */
	@Test
	public void testAutowiredCollections(){
		Foos foos = (Foos) super.getContext().getBean("foos");
		System.out.println(foos);
	}
	
	/**
	 * 自动注入Map类型依赖
	 */
	@Test
	public void testAutowiredMap(){
		FooMap fooMap = (FooMap) super.getContext().getBean("fooMap");
		
		Map<String, Bar> map = fooMap.getBars();
		
		Iterator<String> it = map.keySet().iterator();
		
		while(it.hasNext()){
			String key = it.next();
			Bar bar = map.get(key);
			System.out.println(key + ":" + bar);
		}
	}
}
