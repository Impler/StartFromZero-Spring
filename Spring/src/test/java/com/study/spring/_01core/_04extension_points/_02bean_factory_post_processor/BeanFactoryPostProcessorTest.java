package com.study.spring._01core._04extension_points._02bean_factory_post_processor;

import org.junit.Test;

import com.study.spring._01core._04extension_points.bean_factory_post_processor.Foo;
import com.study.spring.test.BaseTestObject;


public class BeanFactoryPostProcessorTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/01core/04extension-points/02bean-factory-post-processor/beans-bean-factory-post-processor.xml";
	}
	
	
	@Test
	public void test(){
		Foo foo = (Foo) super.getBean("foo");
		System.out.println(foo);
	}

}
