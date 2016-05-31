package com.study.spring.core.converter.domain;

import com.study.spring.core.converter.IGenericEnum;

/**
 * IGeneric枚举
 */
public enum StatusEnum implements IGenericEnum{

	TURNON(1, "打开"), TRUNOFF(2,"关闭");
	
	private StatusEnum(int intValue, String stringValue) {
		this.intValue = intValue;
		this.stringValue = stringValue;
	}

	private int intValue;
	private String stringValue;
	
	@Override
	public int getIntValue() {
		return this.intValue;
	}

	@Override
	public String getStringValue() {
		return this.stringValue;
	}
	
}
