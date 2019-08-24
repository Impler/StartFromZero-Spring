package com.study.spring.integration.redis.domain;

import java.io.Serializable;

public class Foo implements Serializable{

	private static final long serialVersionUID = 648735947269690308L;
	
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
