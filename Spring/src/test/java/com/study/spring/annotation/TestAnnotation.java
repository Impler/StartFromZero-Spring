package com.study.spring.annotation;


import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.study.spring.base.domain.User;
import com.study.spring.test.BaseTestObject;

@ComponentScan("com.study.spring.annotation")
public class TestAnnotation extends BaseTestObject{

	@Test
	public void testAnnotationWithXMLContext() {
		UserManager manager = (UserManager) super.getAnnoBean("userManager");
		User u = new User("kitty", 22, 'F', "NanJing");
		manager.register(u);
	}
	
	@Test
	public void testAnnotationWithAnnoContext(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(this.getClass());
		UserManager manager = (UserManager) ctx.getBean("userManager");
		User u = new User("Tom", 24, 'M', "BeiJing");
		manager.register(u);
		ctx.close();
	}
}
