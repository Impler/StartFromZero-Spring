package com.study.spring.config.annotation.resource;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.study.spring.config.annotation.IUserDao;
import com.study.spring.config.annotation.IUserService;

@Component
public class Foo {
	
	@Resource
	private IUserDao userDao;
	
	@Resource(name="anotherUserDao")
	private IUserDao antDao;
	
	private IUserService ser;

	public IUserService getSer() {
		return ser;
	}

	@Resource
	public void setUserService(IUserService service) {
		this.ser = service;
	}
	
	public void showMsg(){
		System.out.println(userDao + " , " + antDao + " , " + ser);
	}
}