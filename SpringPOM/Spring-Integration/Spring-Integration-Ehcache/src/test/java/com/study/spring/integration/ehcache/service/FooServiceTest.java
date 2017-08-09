package com.study.spring.integration.ehcache.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.spring.integration.ehcache.domain.Foo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class FooServiceTest {
	
	@Autowired
	private FooService fooService;
	
	@Test
	public void testCurrentDate() throws InterruptedException {
		Date date = fooService.currentDate();
		System.out.println("testCurrentDate() 1-->" + date.getTime());
		date = fooService.currentDate();
		System.out.println("testCurrentDate() 2-->" + date.getTime());
		// wait for cache expired
		Thread.sleep(5000);
		date = fooService.currentDate();
		System.out.println("testCurrentDate() 3-->" + date.getTime());
	}

	@Test
	public void testCacheEvict(){
		List<Foo> foos = fooService.getFoos();
		System.out.println("testCacheEvict() 1-->" + foos);
		foos = fooService.getFoos();
		System.out.println("testCacheEvict() 2-->" + foos);
		fooService.addFoo();
		foos = fooService.getFoos();
		System.out.println("testCacheEvict() 3-->" + foos);
		
	}
	
	@Test
	public void testCachePut(){
		Date date = fooService.currentDate();
		System.out.println("testCachePut() 1-->" + date.getTime());
		date = fooService.updateCurrentDate1();
		System.out.println("testCachePut() 2-->" + date.getTime());
		// read last update cache value
		date = fooService.currentDate();
		System.out.println("testCachePut() 3-->" + date.getTime());
		System.out.println("------------------------");
		// update another key cache
		date = fooService.updateCurrentDate2();
		System.out.println("testCachePut() 4-->" + date.getTime());
		// no influence with current cache value
		date = fooService.currentDate();
		System.out.println("testCachePut() 5-->" + date.getTime());
	}

}
