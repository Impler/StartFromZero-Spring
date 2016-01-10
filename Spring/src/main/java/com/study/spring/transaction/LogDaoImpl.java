package com.study.spring.transaction;

public class LogDaoImpl implements ILogDao {

	@Override
	public void log() {
		System.out.println("start log....");
	}

}
