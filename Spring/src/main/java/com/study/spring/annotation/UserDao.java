package com.study.spring.annotation;

import org.springframework.stereotype.Repository;

import com.study.spring.base.domain.User;
/**
 * DAO component
 * @author Impler
 * @date 2015年11月23日
 */

@Repository
public class UserDao implements IUserDao {

	@Override
	public void addUser(User user){
		System.out.println("---add User: " + user);
	}
	
	@Override
	public void deleteUser(User user){
		System.out.println("---delete User: " + user);
	}

	@Override
	public String toString() {
		return "UserDao";
	}
	
	
}
