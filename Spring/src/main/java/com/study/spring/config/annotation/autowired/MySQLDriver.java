package com.study.spring.config.annotation.autowired;

import org.springframework.stereotype.Component;

@Component
public class MySQLDriver implements IJDBCDriver {

	@Override
	public void insert() {
		System.out.println("MySQL insert...");
	}

	@Override
	public String toString() {
		return "MySQLDriver";
	}
	
}
