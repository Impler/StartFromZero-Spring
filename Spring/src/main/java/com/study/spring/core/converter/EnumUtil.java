package com.study.spring.core.converter;

/**
 * @author Impler
 * @date 2016-05-17
 */
public class EnumUtil {
	/**
	 * 根据枚举常量的int值，取得对应的枚举对象
	 * @param type 枚举类class
	 * @param key int值
	 * @return
	 */
	public static <E extends IGenericEnum> E getGenericEnumConstant(Class<E> type, int key){
		E[] constants = type.getEnumConstants();
		for(E c : constants){
			if(c.getIntValue() == key){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * 根据枚举常量的int值，取得对应的枚举对象
	 * @param type
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <E extends Enum> Enum getEnumConstant(Class<E> type, int key){
		E[] constants = type.getEnumConstants();
		if(IGenericEnum.class.isAssignableFrom(type)){
			for(E c : constants){
				if(((IGenericEnum)c).getIntValue() == key){
					return c;
				}
			}
		}
		return null;
	}
}
