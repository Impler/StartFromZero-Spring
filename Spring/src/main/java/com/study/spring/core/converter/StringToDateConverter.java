package com.study.spring.core.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 简单特定对象的转换
 */
public class StringToDateConverter implements Converter<String, Date>{

	private String dateFormat;

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public Date convert(String source) {
		if(null == this.dateFormat || this.dateFormat.trim().length() <= 0){
			this.dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(this.dateFormat);
		try {
			return format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
