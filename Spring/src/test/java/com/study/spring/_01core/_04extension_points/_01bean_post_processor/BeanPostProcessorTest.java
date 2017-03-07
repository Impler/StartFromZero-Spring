package com.study.spring._01core._04extension_points._01bean_post_processor;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;


public class BeanPostProcessorTest extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "config/01core/04extension-points/01bean-post-processor/beans-bean-post-processor.xml";
	}
	
	
	@Test
	public void test(){
		super.getContext();
	}

}
