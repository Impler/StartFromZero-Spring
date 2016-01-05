package com.study.spring.core.spel;

public class Student {
	public static final String IDENTITY = "student";	//身份
	private int id;
	private String name;
	private char sex;
	
	public Student(int id, String name, char sex) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public static String getIdentity(){
		return IDENTITY;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}
}
