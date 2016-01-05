package com.study.spring.transaction;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.study.spring.test.BaseTestObject;

public class TestTransaction extends BaseTestObject{
	
	@Override
	protected String getConfigFileName() {
		return "transaction.xml";
	}
	
	@Test
	public void test() throws SQLException {
		DataSource ds = (DataSource) super.getBean("dataSource");
		System.out.println(ds.getConnection().isClosed());;
	}

}
