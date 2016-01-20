package com.study.spring.core.jdbc;

import java.util.List;

public interface IBlogArticleDao {

	public void add(BlogArticle ba);
	public void batchUpdate(List<BlogArticle> bas);
}
