package com.study.spring.core.converter.domain;
/**
 * 一般枚举类型
 */
public enum RoleEnum{
	ADMIN("管理员"), USER("普通用户");
	
	private String value;
	
	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}