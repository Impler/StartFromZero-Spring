package com.study.spring.core.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.study.spring.test.BaseTestObject;

public class TestJDBC extends BaseTestObject{

	@Override
	protected String getConfigFileName() {
		return "jdbc.xml";
	}
	
	@Test
	/**
	 * test execute()
	 * 可用于执行DDL语句
	 */
	public void testJDBCTmpByHardcode() {
		DriverManagerDataSource ds = (DriverManagerDataSource) super.getBean("dataSource");
		JdbcTemplate tmp = new JdbcTemplate(ds);
		String sql = "create table t_user(userid int primary key, username varchar(20))";
		tmp.execute(sql);
	}


	@Test
	/**
	 * test update()
	 */
	public void testUpdate(){
		IBlogArticleDao blogDao = (IBlogArticleDao) super.getBean("blogDao");
		BlogArticle ba = new BlogArticle();
		ba.setTitle("spring jdbc");
		ba.setAuthor("张三");
		ba.setContent("spring study study spring");
		blogDao.add(ba);
	}
	
	@Test
	/**
	 * test batchUpdate()
	 */
	public void testBatchUpdate(){
		IBlogArticleDao blogDao = (IBlogArticleDao) super.getBean("blogDao");
		List<BlogArticle> bas = new ArrayList<BlogArticle>();
		bas.add(new BlogArticle(3, "Spring JDBC1", "JACK", "Spring JDBC1Spring JDBC1Spring JDBC1Spring JDBC1Spring JDBC1"));
		bas.add(new BlogArticle(4, "Spring JDBC2", "TOM", "Spring JDBC2Spring JDBC2Spring JDBC2Spring JDBC2Spring JDBC2Spring JDBC2"));
		bas.add(new BlogArticle(6, "Spring JDBC3", "LIDA", "Spring333SpringSpringSpringSpring33333"));
		bas.add(new BlogArticle(7, "Spring JDBC4", "LISA", "Spring JDBC4Spring JDBC4Spring JDBC4Spring JDBC4Spring JDBC4"));
		blogDao.batchUpdate(bas);
	}
	
	@Test
	/**
	 * test query()
	 * by RowCallbackHandler
	 * by RowMap
	 * RowCallbackHandler与RowMap功能类似，但RowCallbackHandler将每一行result转换成对象，RowMap批量化处理结果集返回List
	 */
	public void testQuery(){
		IBlogArticleDao blogDao = (IBlogArticleDao) super.getBean("blogDao");
		BlogArticle blog = blogDao.getBlogById(1);
		System.out.println(" by id: " + blog);
		List<BlogArticle> blogs = blogDao.getBlogsByAuthor("张");
		for (BlogArticle b : blogs) {
			System.out.println(b);
		}
		int count = blogDao.getBlogCount();
		System.out.println("count: " + count);
	}
	
	@Test
	/**
	 * test NamedParameterJdbcTemplate
	 */
	public void testNameParaTmp(){
		IBlogArticleDao blogDao = (IBlogArticleDao) super.getBean("blogDao");
		BlogArticle ba = new BlogArticle();
		ba.setAuthor("赵六");
		ba.setId(3);
		blogDao.update(ba);
	}
}
