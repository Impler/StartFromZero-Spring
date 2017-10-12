package com.study.spring.core.ioc.base_config;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanBaseConfigTest {

	@Test
	public void test() {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beans-base-service.xml");
		
		BusinessService businessService = context.getBean("businessService", BusinessService.class);
		
		businessService.doService();
		
		context.close();
	}

}
