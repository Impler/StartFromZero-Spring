package com.study.spring.annotation;

import com.study.spring.base.domain.User;

public class UserDao implements IUserDao {
	/* (non-Javadoc)
	 * @see com.study.spring.annotation.IUserDao#addUser(com.study.spring.base.domain.User)
	 */
	@Override
	public void addUser(User user){
		System.out.println("add User: " + user);
	}
	
	/* (non-Javadoc)
	 * @see com.study.spring.annotation.IUserDao#deleteUser(com.study.spring.base.domain.User)
	 */
	@Override
	public void deleteUser(User user){
		System.out.println("delete User: " + user);
	}
}
