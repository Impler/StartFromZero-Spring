package com.study.spring.annotation;

import com.study.spring.base.domain.User;

public interface IUserDao {

	void addUser(User user);

	void deleteUser(User user);

}