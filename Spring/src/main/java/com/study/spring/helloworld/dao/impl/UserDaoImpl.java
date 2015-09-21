package com.study.spring.helloworld.dao.impl;

import com.study.spring.helloworld.dao.IUserDao;

public class UserDaoImpl implements IUserDao {

	@Override
	public void save() {
		System.out.println("user save...");
	}
}
