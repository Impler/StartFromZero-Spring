package com.study.spring.helloworld;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.helloworld.dao.IUserDao;

public class SpringHelloWorld {
	
	@Before
	public void init(){
		System.out.println("****test hello world start****");
	}
	
	@After
	public void end(){
		System.out.println("****test hello world end****");
	}
	
	@Test
	public void testHello() {
		ClassPathXmlApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserDao userDao = (IUserDao) ctx.getBean("userDao");
		userDao.save();
		ctx.close();
	}

}
