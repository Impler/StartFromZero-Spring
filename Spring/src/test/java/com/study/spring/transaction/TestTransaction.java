package com.study.spring.transaction;

import java.sql.SQLException;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class TestTransaction extends BaseTestObject{
	
	@Override
	protected String getConfigFileName() {
		return "transaction.xml";
	}
	
	@Test
	public void testTransByTransactionProxyFactoryBean(){
		UserService service = (UserService) super.getBean("userService");
		try {
			service.regist();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAopTransaction(){
		UserService service = (UserService)super.getBean("userServiceTarget");
		try {
			service.regist();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
