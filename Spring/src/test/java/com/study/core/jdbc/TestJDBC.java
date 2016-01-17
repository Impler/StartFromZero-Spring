package com.study.core.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.study.spring.test.BaseTestObject;

public class TestJDBC extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "jdbc.xml";
	}
	
	@Test
	public void testJDBCTmpByHardcode() {
		DriverManagerDataSource ds = (DriverManagerDataSource) super.getBean("dataSource");
		JdbcTemplate tmp = new JdbcTemplate(ds);
		String sql = "create table t_user(userid int primary key, username varchar(20))";
		tmp.execute(sql);
	}


}
