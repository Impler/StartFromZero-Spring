package com.study.spring._01core._05anno_config._04components._01inner_beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Foo {

	@Bean(name="bar")
	public Bar getBar(){
		return new Bar();
	}
}
