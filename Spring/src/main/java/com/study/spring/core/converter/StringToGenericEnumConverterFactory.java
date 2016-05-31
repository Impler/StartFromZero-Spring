package com.study.spring.core.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;


/**
 * 将一个类型的对象转换为另一个对象及其子对象
 * 自定义转化器工厂, String -> IGenericEnum
 * @see org.springframework.core.convert.support.StringToEnumConverterFactory
 */
@SuppressWarnings("rawtypes")
public class StringToGenericEnumConverterFactory implements ConverterFactory<String, Enum> {
	
	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnum<T>(targetType);
	}

	private class StringToEnum<T extends Enum> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		@SuppressWarnings("unchecked")
		public T convert(String source) {
			if (source.length() == 0) {
				return null;
			}
			if(IGenericEnum.class.isAssignableFrom(enumType)){
				return (T) EnumUtil.getEnumConstant(enumType, Integer.valueOf(source));
			}else{
				return (T) Enum.valueOf(this.enumType, source.trim());
			}
		}
	}
}
