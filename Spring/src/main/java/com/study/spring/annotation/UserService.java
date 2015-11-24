package com.study.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.spring.base.domain.User;

/**
 * service component
 * @author Impler
 * @date 2015年11月23日
 */

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	
	public IUserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public void register(User user){
		System.out.println("--register user:");
		userDao.addUser(user);
		System.out.println("--register user successfully");
	}


	@Override
	public String toString() {
		return "UserService";
	}
	
}
