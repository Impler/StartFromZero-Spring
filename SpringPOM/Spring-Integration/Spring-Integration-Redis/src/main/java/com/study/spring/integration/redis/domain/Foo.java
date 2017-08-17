package com.study.spring.integration.redis.domain;

import java.io.Serializable;

public class Foo implements Serializable{

	private static final long serialVersionUID = 648735947269690308L;

	@Override
	public String toString() {
		return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
	}
}
