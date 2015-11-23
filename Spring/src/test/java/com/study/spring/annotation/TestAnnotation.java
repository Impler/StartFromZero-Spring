package com.study.spring.annotation;


import org.junit.Test;

import com.study.spring.base.domain.User;
import com.study.spring.test.BaseTestObject;

public class TestAnnotation extends BaseTestObject{

	@Test
	public void testAnnotationWithXMLContext() {
		UserManager manager = (UserManager) super.getAnnoBean("userManager");
		User u = new User("kitty", 22, 'F', "NanJing");
		manager.register(u);
	}
	
}
