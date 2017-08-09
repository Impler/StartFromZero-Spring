package com.study.spring.integration.ehcache.domain;

public class Foo {

	@Override
	public String toString() {
		return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
	}
}
