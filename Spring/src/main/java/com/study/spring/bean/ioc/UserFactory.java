package com.study.spring.bean.ioc;

import com.study.spring.base.domain.User;

/**
 * User Factory
 * @author Impler
 * @date 2015年9月28日
 */
public class UserFactory {

	public User createUser(){
		return new User("TOM", 21, 'M', "BeiJing");
	}
	
	public static User staticCreateUser(){
		return new User("KITTY", 20, 'F', "NanJing");
	}

}
