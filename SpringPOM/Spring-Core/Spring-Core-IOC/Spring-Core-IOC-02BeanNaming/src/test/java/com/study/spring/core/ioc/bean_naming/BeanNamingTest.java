package com.study.spring.core.ioc.bean_naming;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.ioc.bean_naming.module.ModuleA;
import com.study.spring.core.ioc.bean_naming.module.ModuleB;

public class BeanNamingTest {

	@Test
	public void test() {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beans-naming.xml");
		
		DataSource ds = (DataSource) context.getBean("dataSource");
		DataSource mysqlDS = (DataSource) context.getBean("mysqlDS");
		
		Assert.assertEquals(ds, mysqlDS);
		
		ModuleA mA = (ModuleA) context.getBean("moduleA");
		ModuleB mB = (ModuleB) context.getBean("moduleB");
	
		DataSource mADS = mA.getDataSource(); 
		DataSource mBDS = mB.getDataSource();
		
		Assert.assertEquals(mADS, mBDS);
		Assert.assertEquals(mADS, ds);
		
		context.close();
	}

}
