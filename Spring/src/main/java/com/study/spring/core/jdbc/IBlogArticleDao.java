package com.study.spring.core.jdbc;

import java.util.List;

public interface IBlogArticleDao {

	public void add(BlogArticle ba);
	public void batchUpdate(List<BlogArticle> bas);
	public void update(BlogArticle ba);
	public BlogArticle getBlogById(int id);
	public List<BlogArticle> getBlogsByAuthor(String author);
	public int getBlogCount();
}
