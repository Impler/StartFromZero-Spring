package com.study.spring.config.annotation.resource;

import org.springframework.stereotype.Component;

import com.study.spring.base.domain.User;
import com.study.spring.config.annotation.IUserDao;

@Component
public class AnotherUserDao implements IUserDao {

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "AnotherUserDao";
	}
}