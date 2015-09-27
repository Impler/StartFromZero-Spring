package com.study.spring.bean.ioc;
/**
 * Setter based dependency injection
 * @author Impler
 * @date 2015年9月27日
 */
public class SetterBasedDI {
	private User u;

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
}
