package com.study.spring._01core._05anno_config._01required;

import org.springframework.beans.factory.annotation.Required;

public class Foo {

	private Bar bar;

	public Bar getBar() {
		return bar;
	}
	
	/**
	 * @Request 注解只能标识在setter方法上
	 * 表示当前属性是非空的，在一开始就应该被赋值，不论是通过自动注入的方式，还是通过xml配置
	 * @param bar
	 */
	@Required
	public void setBar(Bar bar) {
		this.bar = bar;
	}

}
