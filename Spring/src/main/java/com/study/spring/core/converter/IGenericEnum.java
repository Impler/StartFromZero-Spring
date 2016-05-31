package com.study.spring.core.converter;

/**
 * 一般形如(1,'desc')枚举通用接口
 * @author xiazhaoxu
 *
 */
public interface IGenericEnum {

	/**
	 * 返回数值
	 * @return
	 */
	public int getIntValue();
	
	/**
	 * 返回描述
	 * @return
	 */
	public String getStringValue();
}
