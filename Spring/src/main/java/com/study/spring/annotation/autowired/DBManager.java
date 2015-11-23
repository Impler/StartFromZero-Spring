package com.study.spring.annotation.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class DBManager {
	
	@Autowired
	@Qualifier("oracleDriver")
	private IJDBCDriver driver;
	
	private IJDBCDriver otherDriver;
	
	public void addRecord(){
		System.out.println("add a record...");
		driver.insert();
	}
	
	@Autowired
	public void setOtherDriver(@Qualifier("mySQLDriver")IJDBCDriver driver){
		this.otherDriver = driver;
	}

	public void showOtherDriverInfo() {
		System.out.println("Driver info: " + this.otherDriver.toString());
	}
}
