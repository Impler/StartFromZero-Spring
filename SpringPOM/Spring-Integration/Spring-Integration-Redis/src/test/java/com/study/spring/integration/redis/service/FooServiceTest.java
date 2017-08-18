package com.study.spring.integration.redis.service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.spring.integration.redis.domain.Foo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class FooServiceTest {

	@Autowired
	private FooService fooService;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Before
	public void before(){
		System.out.println("FooServiceTest s");
	}
	
	@After
	public void after(){
		System.out.println("FooServiceTest e");
	}
	
	@Test
	public void testCurrentDate() {
		String cacheName = "fooService-1";
		String cacheKey = "currentDate";
		
		Date date = fooService.currentDate();
		System.out.println("1 testCurrentDate() -->" + date.getTime());
		Date date2 = fooService.currentDate();
		System.out.println("2 testCurrentDate() -->" + date2.getTime());
		
		// redis 中存储于获取需要进行序列化和反序列化，所以此处的对象不是同一个对象
//		Assert.assertEquals(true, date == date2);
		
		// use spring cache api
		Cache cache = cacheManager.getCache(cacheName);
		Date date3 = cache.get(cacheKey, Date.class);
		System.out.println("3 testCurrentDate() -->" + date3.getTime());
		
		// use native -- redis cache api
		RedisCache redisCache = (RedisCache) cache.getNativeCache();
		Date date4 = redisCache.get(cacheKey, Date.class);
		System.out.println("4 testCurrentDate() -->" + date4.getTime());
	}
	
	@Test
	public void testCacheEvict() {
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
