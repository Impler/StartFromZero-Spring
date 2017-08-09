package com.study.spring.integration.ehcache.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.study.spring.integration.ehcache.domain.Foo;

@Service("fooService")
public class FooService {

	@Cacheable(value="currentDate", key="methodName")
	public Date currentDate(){
		long tm = System.currentTimeMillis();
		System.out.println("currentDate()-->" + tm);
		return new Date(tm);
	}
	
	
	@Cacheable("foos")
	public List<Foo> getFoos(){
		
		List<Foo> foos = new ArrayList<Foo>();
		
		Random r = new Random();
		int size = r.nextInt(5);
		for(int i=0; i<size; i++){
			foos.add(new Foo());
		}
		System.out.println("getFoos()-->" + foos);
		return foos ;
	}
	
	@CacheEvict(value="foos", allEntries=false)
	public void addFoo(){
		System.out.println("addFoo()");
	}
	
	@CachePut(value="currentDate", key="methodName")
	public Date updateCurrentDate1(){
		System.out.println("updateCurrentDate1()");
		return currentDate();
	}
	
	@CachePut(value="currentDate", key="targetClass")
	public Date updateCurrentDate2(){
		System.out.println("updateCurrentDate2()");
		return currentDate();
	}

}
