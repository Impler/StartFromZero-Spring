package com.study.spring._01core._05anno_config._02autowired;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class FooMap {

	@Autowired
	private Map<String, Bar> bars;

	
	public Map<String, Bar> getBars() {
		return bars;
	}


	@Override
	public String toString() {
		return "FooMap [bars=" + bars + "]";
	}

}
