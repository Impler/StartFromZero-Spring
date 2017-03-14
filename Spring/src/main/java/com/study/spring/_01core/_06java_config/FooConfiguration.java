package com.study.spring._01core._06java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooConfiguration {

	@Bean
	public Bar getBar(){
		return new Bar();
	}
	
	@Bean
	public Baz getBaz(){
		return new Baz();
	}
}
