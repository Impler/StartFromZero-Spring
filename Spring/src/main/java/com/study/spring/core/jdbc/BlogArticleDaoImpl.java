package com.study.spring.core.jdbc;

import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;

public class BlogArticleDaoImpl implements IBlogArticleDao {

	private JdbcTemplate jt;
	
	public JdbcTemplate getJt() {
		return jt;
	}

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	@Override
	public void add(BlogArticle ba) {
		String sql = "insert into t_blog(title, author, content) values(?,?,?)";
		Object[] ps = new Object[]{ba.getTitle(), ba.getAuthor(), ba.getContent()};
		jt.update(sql, ps);
		//重载方法
		jt.update(sql, ps, new int[]{Types.VARCHAR, Types.VARCHAR, Types.BLOB});
	}

}
