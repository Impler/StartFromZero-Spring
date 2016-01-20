package com.study.spring.core.jdbc;

public class BlogArticle {
	private int id;
	private String title;
	private String content;
	private String author;

	
	public BlogArticle() {
		super();
	}
	
	
	public BlogArticle(int id, String title, String author, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
