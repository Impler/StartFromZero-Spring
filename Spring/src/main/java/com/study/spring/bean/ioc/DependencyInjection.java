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
		System.out.println(this.getClass().getName() + " is created at: " + System.currentTimeMillis());
	}
	// Constructor based dependency injection s
	// ① injection by argument type
	public DependencyInjection(String name, int age){
		this.constBasedUser = new User();
		this.constBasedUser.setName(name);
		this.constBasedUser.setAge(age);
		System.out.println("constDIBean1");
	}
	// ② injection by argument type and index
	public DependencyInjection(String location, String name){
		this.constBasedUser = new User();
		this.constBasedUser.setName(name);
		this.constBasedUser.setLocation(location);
		System.out.println("constDIBean2");
	}
	
	// ④ injection by argument type and index
	public DependencyInjection(String name, char sex, String location){
		this.constBasedUser = new User();
		this.constBasedUser.setName(name);
		this.constBasedUser.setLocation(location);
		this.constBasedUser.setSex(sex);
		System.out.println("constDIBean4");
	}
	
	// ⑤ injection by constructor parameter name
	public DependencyInjection(char se, String nam, String locatio){
		this.constBasedUser = new User();
		this.constBasedUser.setName(nam);
		this.constBasedUser.setLocation(locatio);
		this.constBasedUser.setSex(se);
		System.out.println("constDIBean5:" + se + " " + nam + " " + locatio);
	}
	// Constructor based dependency injection e
	// Setter based dependency injection s
	public User getSetterBasedUser() {
		return setterBasedUser;
	}

	public void setSetterBasedUser(User setterBasedUser) {
		System.out.println("setSetterBasedUser is called");
		this.setterBasedUser = setterBasedUser;
	}
	
	// Setter based dependency injection e
	
	public User getConstBasedUser() {
		return constBasedUser;
	}
	
}
