package com.study.spring.config.annotation.autowired;

import org.springframework.stereotype.Component;

@Component
public class OracleDriver implements IJDBCDriver {

	@Override
	public void insert() {
		System.out.println("Oracle insert...");
	}

	@Override
	public String toString() {
		return "OracleDriver";
	}

}
