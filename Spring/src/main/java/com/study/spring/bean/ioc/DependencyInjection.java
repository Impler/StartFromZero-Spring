package com.study.spring.bean.ioc;
/**
 * Dependency injection
 * @author Impler
 * @date 2015年9月27日
 */
public class DependencyInjection {
	// Setter based dependency injection
	private User setterBasedUser;

	public User getSetterBasedUser() {
		return setterBasedUser;
	}

	public void setSetterBasedUser(User setterBasedUser) {
		this.setterBasedUser = setterBasedUser;
	}
	
}
