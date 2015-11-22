package com.study.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.spring.base.domain.User;

public class UserService {

	@Autowired
	private IUserDao userDao;

	public void register(User user){
		System.out.println("register user:");
		userDao.addUser(user);
	}
}
