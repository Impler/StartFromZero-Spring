package com.study.spring.transaction;

public class UserDaoImpl implements IUserDao {

	@Override
	public void add() {
		System.out.println("user add....");
	}

	@Override
	public void delete() {
		System.out.println("user delete....");
	}

}
