package com.study.spring.core.converter.domain;

public class Course {

	private int id;
	private String name;
	private String type;
	private String teacher;

	public Course() {
		super();
	}

	public Course(int id, String name, String type, String teacher) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.teacher = teacher;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", type=" + type
				+ ", teacher=" + teacher + "]";
	}

}
