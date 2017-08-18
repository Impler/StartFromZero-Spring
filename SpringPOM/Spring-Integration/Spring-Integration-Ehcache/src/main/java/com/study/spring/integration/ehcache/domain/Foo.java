package com.study.spring.integration.ehcache.domain;

import java.io.Serializable;

public class Foo implements Serializable{
	
	private static final long serialVersionUID = -2993276609592998296L;
	
	private int id;

	public Foo(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Foo [id=" + id + "]";
	}
}
