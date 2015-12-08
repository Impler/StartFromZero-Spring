package com.study.spring.bean.ioc;

import com.study.spring.base.domain.User;

/**
 * Dependency injection
 * @author Impler
 * @date 2015年9月27日
 */
public class DependencyInjection {
	private User setterBasedUser;
	private User constBasedUser;
	public DependencyInjection(){
		super();
		System.out.println(System.currentTimeMillis());
	}
	// Constructor based dependency injection
	// ① injection by argument type
	public DependencyInjection(String name, int age){
		this.constBasedUser = new User();
		this.constBasedUser.setName(name);
		this.constBasedUser.setAge(age);
	}
	// ② injection by argument type 
	public DependencyInjection(String location, String name){
		this.constBasedUser = new User();
		this.constBasedUser.setName(name);
		this.constBasedUser.setLocation(location);
	}
	
	// ③ injection by argument type and index
	public DependencyInjection(String location, String name, char sex){
		this.constBasedUser = new User();
		this.constBasedUser.setName(name);
		this.constBasedUser.setLocation(location);
		this.constBasedUser.setSex(sex);
	}
	// ④ injection by argument type and index
	public DependencyInjection(String name, char sex, String location){
		this(location, name, sex);
	}
	// Setter based dependency injection
	public User getSetterBasedUser() {
		return setterBasedUser;
	}

	public void setSetterBasedUser(User setterBasedUser) {
		this.setterBasedUser = setterBasedUser;
	}
	public User getConstBasedUser() {
		return constBasedUser;
	}
	
}
