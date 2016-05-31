package com.study.spring.core.converter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.ConversionService;

import com.study.spring.core.converter.domain.Course;
import com.study.spring.core.converter.domain.RoleEnum;
import com.study.spring.core.converter.domain.StatusEnum;
import com.study.spring.core.converter.domain.User;
import com.study.spring.test.BaseTestObject;

public class TestConverter extends BaseTestObject{

	private ConversionService convService;
	
	@Override
	protected String getConfigFileName() {
		return "core-converter.xml";
	}
	
	@Before
	public void before(){
		convService = (ConversionService) super.getBean("conversionService");
	}
	
	@Test
	/**
	 * test interface Converter 
	 */
	public void testStringToDateConverter() {
		String date = "2000-12-20 12:12:12";
		Date result = convService.convert(date, Date.class);
		System.out.println(result);
	}
	
	@Test
	/**
	 * test interface ConverterFactory
	 */
	public void testStringToEnumConverter(){
		String role = "USER";
		String status = "1";
		Assert.assertEquals(RoleEnum.USER, convService.convert(role, RoleEnum.class));
		Assert.assertEquals(StatusEnum.TURNON, convService.convert(status, StatusEnum.class));
	}
	
	@Test
	/**
	 * test interface GenericConverter
	 */
	public void testObjectToStringConverter(){
		User u = new User(12, "张三", "test@qq.com");
		Course c = new Course(23, "数学", "理工科", "王教授");
		
		System.out.println(convService.convert(u, String.class));
		System.out.println(convService.convert(c, String.class));
	}
}
