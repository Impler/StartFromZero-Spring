package com.study.spring._01core._01ioc._02bean_naming;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring._01core._01ioc._01base_config.BusinessService;

public class BeanNamingAliasTest {

	@Test
	public void test() {
		String[] configFiles = {"config/01core/01ioc/02bean-naming/beans-base-alias.xml"
				, "config/01core/01ioc/02bean-naming/beans-base-alias-module-a.xml"
				, "config/01core/01ioc/02bean-naming/beans-base-alias-module-b.xml"
		};
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(configFiles);
		
		// get all alias of bean, includes name attribute, and alias element defines
		String[] alias = context.getAliases("businessDao");
		System.out.println("******businessDao all aliases are below:");
		for(String a : alias){
			System.out.println("alias:" + a);
		}
		
		// check is referenced the same bean by alias
		BusinessService serviceA = (BusinessService) context.getBean("businessServiceA");
		BusinessService serviceB = (BusinessService) context.getBean("businessServiceB");
		System.out.println("******is refed the same bean by alias: ");
		System.out.println(serviceA.getBusinessDao() == serviceB.getBusinessDao());
		
		context.close();
	}

}
