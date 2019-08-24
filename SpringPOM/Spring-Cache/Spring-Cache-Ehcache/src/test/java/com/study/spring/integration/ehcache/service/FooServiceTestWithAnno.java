package com.study.spring.integration.ehcache.service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.spring.integration.ehcache.domain.Foo;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-anno.xml")
public class FooServiceTestWithAnno {
	
	@Autowired
	private FooService fooService;
	
	@Autowired
	private CacheManager cacheManager;
	
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
		String cacheName = "fooService-1";
		String cacheKey = "currentDate";
		Date date1 = fooService.currentDate();
		System.out.println("1 testCurrentDate() -->" + date1.getTime());
		
		// read from cache by spring cache api
		Cache cache = cacheManager.getCache(cacheName);
		Date springCacheDate = cache.get(cacheKey, Date.class);
		// read from cache by native cache api
		net.sf.ehcache.Cache c = (net.sf.ehcache.Cache) cache.getNativeCache();
		Date nativeCacheDate = (Date) c.get(cacheKey).getObjectValue();
		Assert.assertEquals(true, springCacheDate == nativeCacheDate);
		
		Date date2 = fooService.currentDate();
		System.out.println("2 testCurrentDate() -->" + date2.getTime());
		Assert.assertEquals(true, date1 == date2);
		
		// wait for cache expired
		Thread.sleep(5000);
		Date date3 = fooService.currentDate();
		System.out.println("3 testCurrentDate() -->" + date3.getTime());
		Assert.assertEquals(true, date1 != date3);
	}

	@Test
	public void testCacheEvict(){
		List<Foo> foos = fooService.getFoos();
		System.out.println("1 testCacheEvict() -->" + foos);
		List<Foo> foos1 = fooService.getFoos();
		System.out.println("2 testCacheEvict() -->" + foos1);
		Assert.assertEquals(true, foos == foos1);
		
		fooService.addFoo();
		
		List<Foo> foos2 = fooService.getFoos();
		System.out.println("3 testCacheEvict() -->" + foos2);
		Assert.assertEquals(false, foos == foos2);
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
