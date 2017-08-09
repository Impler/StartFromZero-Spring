package com.study.spring.integration.ehcache.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.spring.integration.ehcache.domain.Foo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-xml.xml")
public class FooServiceTestWithXML {

	@Autowired
	private FooService fooService;

	@Before
	public void before(){
		System.out.println("FooServiceTestWithXML s");
	}
	@After
	public void after(){
		System.out.println("FooServiceTestWithXML e");
	}
	
	@Test
	public void testCacheEvict(){
		List<Foo> foos = fooService.getFoos();
		System.out.println("1 testCacheEvict() -->" + foos);
		foos = fooService.getFoos();
		System.out.println("2 testCacheEvict() -->" + foos);
		fooService.addFoo();
		foos = fooService.getFoos();
		System.out.println("3 testCacheEvict() -->" + foos);
	}

}
