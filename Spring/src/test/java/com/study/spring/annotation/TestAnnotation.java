package com.study.spring.annotation;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.study.spring.base.domain.User;
import com.study.spring.config.annotation.UserManager;
import com.study.spring.config.annotation.autowired.DBManager;
import com.study.spring.config.annotation.required.OperationLog;
import com.study.spring.config.annotation.resource.Foo;
import com.study.spring.test.BaseTestObject;

@ComponentScan("com.study.spring.config.annotation")
public class TestAnnotation extends BaseTestObject{
	//只用于纯注解配置的测试案例
	private AnnotationConfigApplicationContext annoCtx;
	@Before
	public void init(){
		annoCtx = new AnnotationConfigApplicationContext(this.getClass());
	}
	
	@After
	public void end(){
		annoCtx.close();
	}

	@Test
	public void testAnnotationWithXMLContext() {
		UserManager manager = (UserManager) super.getAnnoBean("userManager");
		User u = new User("kitty", 22, 'F', "NanJing");
		manager.register(u);
	}
	
	@Test
	public void testAnnotationWithAnnoContext(){
		UserManager manager = (UserManager) this.annoCtx.getBean("userManager");
		User u = new User("Tom", 24, 'M', "BeiJing");
		manager.register(u);
	}
	
	@Test
	public void testRequired(){
		OperationLog log = (OperationLog) super.getAnnoBean("operationLog");
		System.out.println(log);
	}
	
	@Test
	public void testAutoWired(){
		DBManager manager = (DBManager) this.annoCtx.getBean("DBManager");
		manager.addRecord();
		manager.showOtherDriverInfo();
	}
	
	@Test
	public void testResource(){
		Foo f = (Foo) this.annoCtx.getBean("foo");
		f.showMsg();
	}
}
