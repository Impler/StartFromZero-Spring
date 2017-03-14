package com.study.spring._01core._06java_config._01lookup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.study.spring._01core._06java_config.Bar;

@Configuration
public class BarConfiguration {

	/**
	 * 返回单例Foo， 并且每次调用getBar()方法，返回一个prototype bar
	 * @return
	 */
	@Bean
	public Foo getFoo() {
		
		return new Foo() {
			@Override
			public Bar getBar() {
				return newBar();
			}
		};
	}

	@Bean
	@Scope("prototype")
	public Bar newBar() {
		return new Bar();
	}
}
