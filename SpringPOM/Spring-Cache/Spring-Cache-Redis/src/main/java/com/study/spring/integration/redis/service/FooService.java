package com.study.spring.integration.redis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.study.spring.integration.redis.domain.Foo;


@Service("fooService")
public class FooService {

	@Cacheable(value="fooService-1", key="methodName")
	public Date currentDate(){
		long tm = System.currentTimeMillis();
		System.out.println("call method currentDate()-->" + tm);
		return new Date(tm);
	}
	
	
	@Cacheable(value="fooService-2", key="methodName")
	public List<Foo> getFoos(){
		
		List<Foo> foos = new ArrayList<Foo>();
		
		Random r = new Random();
		int size = r.nextInt(5);
		for(int i=0; i<size; i++){
			foos.add(new Foo(r.nextInt(20)));
		}
		System.out.println("call method getFoos()-->" + foos);
		return foos ;
	}
	
	/**
	 * 此处需要指定需要移除缓存的key，key使用EL表达式，这里直接使用字符串形式
	 * 如果不指定key的话，则需要将allEntries值设为false，这样会移除该缓存下多有的值
	 */
	@CacheEvict(value="fooService-2", key="'getFoos'",allEntries=false)
	public void addFoo(){
		System.out.println("call method addFoo()");
	}
	
	@CachePut(value="fooService-1", key="methodName")
	public Date updateCurrentDate1(){
		System.out.println("call method updateCurrentDate1()");
		return currentDate();
	}
	
	@CachePut(value="fooService-1", key="targetClass")
	public Date updateCurrentDate2(){
		System.out.println("call method updateCurrentDate2()");
		return currentDate();
	}

}
