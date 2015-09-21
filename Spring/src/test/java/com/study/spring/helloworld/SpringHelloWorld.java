package com.study.spring.helloworld;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.helloworld.dao.IUserDao;

public class SpringHelloWorld {

	@Test
	public void testHello() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserDao userDao = (IUserDao) ctx.getBean("userDao");
		userDao.save();
	}

}
