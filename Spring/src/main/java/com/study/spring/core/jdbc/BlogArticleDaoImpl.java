package com.study.spring.core.jdbc;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;

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
		//重载方法1
		jt.update(sql, ps, new int[]{Types.VARCHAR, Types.VARCHAR, Types.BLOB});
		//重载方法2
		jt.update(sql, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, ba.getTitle());
				ps.setString(2, ba.getAuthor());
				ps.setBlob(3, new ByteArrayInputStream(ba.getContent().getBytes()));
			}
			
		});
		//重载方法3
		jt.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, ba.getTitle());
				ps.setString(2, ba.getAuthor());
				ps.setBlob(3, new ByteArrayInputStream(ba.getContent().getBytes()));
				return ps;
			}
			
		});
		
		//重载方法4
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS, ResultSet.CONCUR_UPDATABLE);
				ps.setString(1, ba.getTitle());
				ps.setString(2, ba.getAuthor());
				ps.setBlob(3, new ByteArrayInputStream(ba.getContent().getBytes()));
				return ps;
			}
			
		}, keyHolder);
		System.out.println("last blog id is: " + keyHolder.getKey());
	}

}
