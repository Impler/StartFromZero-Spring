package com.study.spring.integration.ehcache.service;

import java.util.Date;
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
@ContextConfiguration("/applicationContext-anno.xml")
public class FooServiceTestWithAnno {
	
	@Autowired
	private FooService fooService;
	
	@Before
	public void before(){
		System.out.println("FooServiceTestWithAnno s");
	}
	@After
	public void after(){
		System.out.println("FooServiceTestWithAnno e");
	}
	
	@Test
	public void testCurrentDate() throws InterruptedException {
		Date date = fooService.currentDate();
		System.out.println("1 testCurrentDate() -->" + date.getTime());
		date = fooService.currentDate();
		System.out.println("2 testCurrentDate() -->" + date.getTime());
		// wait for cache expired
		Thread.sleep(5000);
		date = fooService.currentDate();
		System.out.println("3 testCurrentDate() -->" + date.getTime());
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
	
	@Test
	public void testCachePut(){
		Date date = fooService.currentDate();
		System.out.println("1 testCachePut() -->" + date.getTime());
		date = fooService.updateCurrentDate1();
		System.out.println("2 testCachePut() -->" + date.getTime());
		// read last update cache value
		date = fooService.currentDate();
		System.out.println("3 testCachePut() -->" + date.getTime());
		System.out.println("------------------------");
		// update another key cache
		date = fooService.updateCurrentDate2();
		System.out.println("4 testCachePut() -->" + date.getTime());
		// no influence with current cache value
		date = fooService.currentDate();
		System.out.println("5 testCachePut() -->" + date.getTime());
	}

}
