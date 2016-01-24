package com.study.spring.core.jdbc;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
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
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, ba.getTitle());
				ps.setString(2, ba.getAuthor());
				ps.setBlob(3, new ByteArrayInputStream(ba.getContent().getBytes()));
				return ps;
			}
			
		}, keyHolder);
		System.out.println("last blog id is: " + keyHolder.getKey());
	}

	@Override
	public void batchUpdate(List<BlogArticle> bas) {
		String[] sqls = {"update t_blog set author='李四' where id=1", 
							"delete from t_blog where id=5"};
		int[] result = jt.batchUpdate(sqls);
		System.out.println("batchUpdate(sqls) result: " + Arrays.toString(result));
		
		//重载方法1
		String sql = "update t_blog set author = ? where id = ?";
		result = jt.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BlogArticle ba = bas.get(i);
				ps.setString(1, ba.getAuthor());
				ps.setInt(2, ba.getId());
			}

			@Override
			public int getBatchSize() {
				return bas.size();
			}
			
		});
		
		System.out.println("batchUpdate(sql, pssetter) result: " + Arrays.toString(result));
	}

	@Override
	public BlogArticle getBlogById(int id) {
		BlogArticle blog = new BlogArticle();
		String sql = "select * from t_blog where id = ?";
		jt.query(sql, new Object[]{id}, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				blog.setId(rs.getInt("id"));
				blog.setAuthor(rs.getString("author"));
				blog.setTitle(rs.getString("title"));
				blog.setContent(rs.getClob("content").toString());
			}
			
		});
		return blog;
	}

	@Override
	public List<BlogArticle> getBlogsByAuthor(String title) {
		List<BlogArticle> blogs = null;
		String sql = "select * from t_blog where author like ?";
		blogs = jt.query(sql, new Object[]{"%" + title + "%"}, new RowMapper<BlogArticle>(){

			@Override
			public BlogArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println("row mapper row num: " + rowNum);
				BlogArticle blog = new BlogArticle();
				blog.setId(rs.getInt("id"));
				blog.setAuthor(rs.getString("author"));
				blog.setTitle(rs.getString("title"));
				blog.setContent(rs.getClob("content").toString());
				return blog;
			}
			
		});
		return blogs;
	}

	@Override
	public int getBlogCount() {
		String sql = "select count(*) from t_blog";
		// queryForInt/queryForLong is deprecated, all use queryForObject
		return jt.queryForObject(sql, Integer.class);
	}

	
}
