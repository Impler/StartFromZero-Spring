package com.study.spring._01core._01ioc._01baseconfig;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanBaseConfigTest {

	@Test
	public void test() {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("config/01core/01ioc/01baseconfig/beans-base-service.xml");
		
		BusinessService businessService = context.getBean("businessService", BusinessService.class);
		
		businessService.doService();
		
		context.close();
	}

}
