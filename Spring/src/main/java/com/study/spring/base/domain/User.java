package com.study.spring.base.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * a user POJO
 * @author Impler
 * @date 2015年9月27日
 */
public class User {
	private String name;
	private int age;
	private char sex;
	private boolean isMarried;
	private String location;
	public User(){
		super();
		System.out.println(this.getClass().getName() + " is created at: " + System.currentTimeMillis());
	}

	public User(String name, int age, char sex, String location) {
		this();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.location = location;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public boolean isMarried() {
		return isMarried;
	}
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return System.currentTimeMillis() + "User [name=" + name + ", age=" + age + ", sex=" + sex
				+ ", isMarried=" + isMarried + ", location=" + location + "]";
	}
	
	//lifestyle method s
	public void init(){
		System.out.println("user --> init");
	}
	public void initMethod(){
		System.out.println("user --> initMethod");
	}
	public void destroy(){
		System.out.println("user --> destroy");
	}
	public void destroyMethod(){
		System.out.println("user --> destroyMethod");
	}
	@PostConstruct
	public void initAnno(){
		System.out.println("user --> initAnno");
	}
	@PreDestroy
	public void destroyAnno(){
		System.out.println("user --> destroyAnno");
	}
	//lifestyle method e
}
